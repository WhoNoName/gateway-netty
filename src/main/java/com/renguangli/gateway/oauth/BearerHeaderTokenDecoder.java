package com.renguangli.gateway.oauth;

import io.netty.handler.codec.http.HttpHeaderNames;
import io.netty.handler.codec.http.HttpRequest;

/**
 * BearerHeaderTokenDecoder
 *
 * @author renguangli 2018/11/7 17:40
 * @since JDK 1.8
 */
public class BearerHeaderTokenDecoder implements TokenDecoder {

    @Override
    public String getAccessToken(HttpRequest request) {
        String authzHeader = request.headers().get(HttpHeaderNames.AUTHORIZATION);
        return OAuthUtils.getAuthHeaderField(authzHeader);
    }

}
