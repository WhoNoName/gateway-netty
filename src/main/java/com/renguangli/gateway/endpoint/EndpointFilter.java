package com.renguangli.gateway.endpoint;

import com.renguangli.gateway.Filter;
import com.renguangli.gateway.Gateway;
import com.renguangli.gateway.pojo.Api;
import com.renguangli.gateway.service.ApiService;
import io.netty.handler.codec.http.DefaultHttpHeaders;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
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

import java.util.List;
import java.util.Map;

/**
 * EndpointFilter
 *
 * @author renguangli 2018/11/16 13:03
 * @since JDK 1.8
 */
public class EndpointFilter implements Filter {

    private ApiService apiService = Gateway.applicationContext.getBean(ApiService.class);

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
    public int order() {
        return 0;
    }

    @Override
    public boolean filter(FullHttpRequest request, FullHttpResponse response) {
        // 从数据库中获取 API 信息
        Api api = apiService.getApiByContext(request.uri());
        if (api == null) { // 资源不存在
            ResponseUtils.buildNotFoundResponse(request, response);
            return false;
        }

        String url = api.getEndpoint() + api.getUri();
        EndpointResponse endpointResponse;
        if (HttpMethod.GET.name().equalsIgnoreCase(api.getMethod())) {
            endpointResponse = doGet(url);
        } else if (HttpMethod.GET.name().equalsIgnoreCase(api.getMethod())) {
            endpointResponse = doPost(request, url);
        } else if (HttpMethod.POST.name().equalsIgnoreCase(api.getMethod())) {
            endpointResponse = doPost(request, url);
        } else if (HttpMethod.PUT.name().equalsIgnoreCase(api.getMethod())) {
            endpointResponse = doPost(request, url);
        } else if (HttpMethod.DELETE.name().equalsIgnoreCase(api.getMethod())) {
            endpointResponse = doPost(request, url);
        } else if (HttpMethod.HEAD.name().equalsIgnoreCase(api.getMethod())) {
            endpointResponse = doPost(request, url);
        } else if (HttpMethod.OPTIONS.name().equalsIgnoreCase(api.getMethod())) {
            endpointResponse = doPost(request, url);
        } else if (HttpMethod.CONNECT.name().equalsIgnoreCase(api.getMethod())) {
            endpointResponse = doPost(request, url);
        } else if (HttpMethod.TRACE.name().equalsIgnoreCase(api.getMethod())) {
            endpointResponse = doPost(request, url);
        } else {
            ResponseUtils.buildMethodNotSupportResponse(request, response);
            return false;
        }
        ResponseUtils.buildSuccessResponse(endpointResponse, response);
        return true;
    }


    private EndpointResponse doGet(String url) {
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
