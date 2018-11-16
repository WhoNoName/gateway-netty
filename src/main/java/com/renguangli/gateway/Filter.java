package com.renguangli.gateway;

import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;

/**
 * Filter
 *
 * @author renguangli 2018/11/7 18:48
 * @since JDK 1.8
 */
public interface Filter {

    int order();

    boolean filter(FullHttpRequest request, FullHttpResponse response);
}
