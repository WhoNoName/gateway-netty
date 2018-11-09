package com.renguangli.gateway.filter;

import io.netty.handler.codec.http.FullHttpRequest;

/**
 * GatewayFilter
 *
 * @author renguangli 2018/11/7 18:48
 * @since JDK 1.8
 */
public interface GatewayFilter {

    int order();

    boolean filter(FullHttpRequest request);
}
