package com.renguangli.gateway.oauth;

import io.netty.handler.codec.http.HttpRequest;

/**
 * TokenDecoder
 *
 * @author renguangli 2018/11/7 17:39
 * @since JDK 1.8
 */
public interface TokenDecoder {

    String getAccessToken(HttpRequest request);

}
