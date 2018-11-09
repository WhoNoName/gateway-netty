package com.renguangli.gateway.endpoint;

import com.alibaba.fastjson.JSON;
import com.renguangli.gateway.cache.CacheManager;
import com.renguangli.gateway.filter.GatewayFilter;
import com.renguangli.gateway.pojo.Api;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.DefaultHttpHeaders;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpHeaderNames;
import io.netty.handler.codec.http.HttpHeaderValues;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpMethod;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.util.CharsetUtil;
import org.apache.http.Header;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.client.StandardHttpRequestRetryHandler;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * HttpServerHandler
 *
 * @author renguangli 2018/11/7 11:52
 * @since JDK 1.8
 */
@ChannelHandler.Sharable
public class HttpServerHandler extends SimpleChannelInboundHandler<FullHttpRequest> {

    private static final Logger LOGGER = LoggerFactory.getLogger(HttpServerHandler.class);

    private List<GatewayFilter> filters = new ArrayList<>();


    private static CloseableHttpClient httpClient;

    /**
     * http 请求设置
     */
    private static RequestConfig requestConfig = RequestConfig.custom()
            .setConnectionRequestTimeout(10000) // 从连接池获取连接的超时时间
            .setConnectTimeout(10000) // http 连接超时时间
            .setSocketTimeout(10000) // 读取数据的超时时间
            .build();

    /**
     * httpClient 重试次数
     */
    private static final int RETRY_COUNT = 3;

    static {
        PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
        connectionManager.setDefaultMaxPerRoute(200);
        connectionManager.setMaxTotal(200);
        httpClient = HttpClients.custom()
                .setConnectionManager(connectionManager)
                .evictExpiredConnections() // 定时清除过期的连接
                .setRetryHandler(new StandardHttpRequestRetryHandler(RETRY_COUNT, true))
                .build();
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, FullHttpRequest request) throws Exception {
        // 执行过滤器链
        for (GatewayFilter filter : filters) {
            filter.filter(request);
        }

        String uri = request.uri();
        FullHttpResponse response = new DefaultFullHttpResponse(request.protocolVersion(), HttpResponseStatus.OK);
        // 从缓存中获取 API 信息
        CacheManager cacheManager = CacheManager.getCacheManager();
        Object value = cacheManager.get(uri);

        if (value == null) { // 资源不存在
            // 构建 NOT_FOUND
            response.setStatus(HttpResponseStatus.NOT_FOUND);
            response.headers().set(HttpHeaderNames.CONTENT_TYPE, HttpHeaderValues.APPLICATION_JSON);
            String message = "Resource " + uri + " is not exists.";
            ErrorResult errorResult = new ErrorResult(HttpResponseStatus.NOT_FOUND.reasonPhrase(), message);
            response.content().writeBytes(Unpooled.copiedBuffer(JSON.toJSONBytes(errorResult)));
            ctx.writeAndFlush(response);
            return;
        }

        Api api = (Api) value;
        String url = api.getEndpoint() + api.getUri();
        HttpMethod method = request.method();
        EndpointResponse endpointResponse = null;
        if ("GET".equalsIgnoreCase(api.getMethod())) {
            endpointResponse = doGet(url);
        } else if ("POST".equalsIgnoreCase(api.getMethod())) {
            endpointResponse = doPost(request, url);
        } else if ("PUT".equalsIgnoreCase(api.getMethod())) {
            endpointResponse = doPost(request, url);
        } else if ("DELETE".equalsIgnoreCase(api.getMethod())) {
            endpointResponse = doPost(request, url);
        } else if ("HEAD".equalsIgnoreCase(api.getMethod())) {
            endpointResponse = doPost(request, url);
        } else if ("CONNECT".equalsIgnoreCase(api.getMethod())) {
            endpointResponse = doPost(request, url);
        } else if ("PATCH".equalsIgnoreCase(api.getMethod())) {
            endpointResponse = doPost(request, url);
        } else if ("OPTIONS".equalsIgnoreCase(api.getMethod())) {
            endpointResponse = doPost(request, url);
        } else if ("TRACE".equalsIgnoreCase(api.getMethod())) {
            endpointResponse = doPost(request, url);
        } else {
            // 不支持的请求方法
            response.setStatus(HttpResponseStatus.METHOD_NOT_ALLOWED);
            String message = "Request method '" + method.name() + "' not supported";
            ErrorResult errorResult = new ErrorResult(HttpResponseStatus.METHOD_NOT_ALLOWED.reasonPhrase(), message);
            response.content().writeBytes(Unpooled.copiedBuffer(JSON.toJSONBytes(errorResult)));
            response.headers().set(HttpHeaderNames.CONTENT_LENGTH,response.content().readableBytes());
            response.headers().set(HttpHeaderNames.CONTENT_TYPE, HttpHeaderValues.APPLICATION_JSON);
            ctx.writeAndFlush(response);
            return;
        }

        // 调用成功
        response.content().writeBytes(endpointResponse.getBody().getBytes(CharsetUtil.UTF_8));
        response.headers().set(endpointResponse.getHeaders());
        response.headers().set(HttpHeaderNames.CONTENT_LENGTH,response.content().readableBytes());
        response.headers().set(HttpHeaderNames.CONTENT_TYPE, api.getContentType());
        ctx.writeAndFlush(response);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.writeAndFlush(Unpooled.EMPTY_BUFFER);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }

    private EndpointResponse doGet(String url) throws IOException {
        HttpGet httpGet = new HttpGet(url);
        httpGet.setConfig(requestConfig);
        return execute(httpGet);
    }

    private EndpointResponse execute(HttpRequestBase httpRequestBase) {
        HttpHeaders httpHeaders = new DefaultHttpHeaders();
        try {
            CloseableHttpResponse response = httpClient.execute(httpRequestBase);
            Header[] allHeaders = response.getAllHeaders();
            for (Header header : allHeaders) {
                httpHeaders.set(header.getName(), header.getValue());
            }
            return new EndpointResponse(response.getStatusLine().getStatusCode(), httpHeaders, EntityUtils.toString(response.getEntity()));
        } catch (Exception e) {
            if (e instanceof ConnectTimeoutException) {
                return new EndpointResponse(HttpResponseStatus.GATEWAY_TIMEOUT.code(), httpHeaders, HttpResponseStatus.GATEWAY_TIMEOUT.reasonPhrase());
            }
            return new EndpointResponse(HttpResponseStatus.INTERNAL_SERVER_ERROR.code(), httpHeaders, e.getMessage());
        }
    }

    private EndpointResponse doPost(FullHttpRequest request, String url) {
        HttpPost httpPost = new HttpPost(url);
        httpPost.setConfig(requestConfig);
        httpPost.setHeaders(this.getHeaders(request));
        String stringContent = request.content().toString(CharsetUtil.UTF_8);
        httpPost.setEntity(new StringEntity(stringContent, CharsetUtil.UTF_8));
        return execute(httpPost);
    }

    private Header[] getHeaders(FullHttpRequest request) {
        Header[] headers = new Header[request.headers().size()];
        List<Map.Entry<String, String>> entries = request.headers().entries();
        for (int i = 0; i < entries.size(); i++) {
            Map.Entry<String, String> entry = entries.get(i);
            headers[i] = new BasicHeader(entry.getKey(), entry.getValue());
        }
        return headers;
    }
}
