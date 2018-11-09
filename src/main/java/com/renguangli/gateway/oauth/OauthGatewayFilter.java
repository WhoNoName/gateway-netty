package com.renguangli.gateway.oauth;

import com.renguangli.gateway.filter.GatewayFilter;
import io.netty.buffer.Unpooled;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpHeaderNames;
import io.netty.handler.codec.http.HttpHeaderValues;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.util.CharsetUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * OauthGatewayFilter
 *
 * @author renguangli 2018/11/7 18:49
 * @since JDK 1.8
 */
public class OauthGatewayFilter implements GatewayFilter {

    private static final Logger LOGGER = LoggerFactory.getLogger(OauthGatewayFilter.class);

    private static final String ACCESS_TOKEN = "aaa";

    @Override
    public int order() {
        return 0;
    }

    @Override
    public boolean filter(FullHttpRequest request) {
        BearerHeaderTokenDecoder bearerHeaderTokenDecoder = new BearerHeaderTokenDecoder();
        String accessToken = bearerHeaderTokenDecoder.getAccessToken(request);
        if (!ACCESS_TOKEN.equals(accessToken)) {
            LOGGER.debug("auth failed,invalid accessToken:{}", accessToken);
            //构建HttpResponse
            FullHttpResponse response = new DefaultFullHttpResponse(request.protocolVersion(), HttpResponseStatus.UNAUTHORIZED);
            //添加响应正文
            //language=JSON
            String responseBody = "{\"error\":\"Invalid Request\"}";
            response.content().writeBytes(Unpooled.wrappedBuffer(responseBody.getBytes(CharsetUtil.UTF_8)));
            //修改响应头
            response.headers().set(HttpHeaderNames.CONTENT_TYPE, HttpHeaderValues.APPLICATION_JSON + ";charset=utf-8;");
            response.headers().set(HttpHeaderNames.CONTENT_LENGTH,response.content().readableBytes());
        }
        return false;
    }
}
