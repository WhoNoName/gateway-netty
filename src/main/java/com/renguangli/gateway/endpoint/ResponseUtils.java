package com.renguangli.gateway.endpoint;

import com.alibaba.fastjson.JSON;
import io.netty.buffer.Unpooled;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpHeaderNames;
import io.netty.handler.codec.http.HttpHeaderValues;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.util.CharsetUtil;

/**
 * ResponseUtils
 *
 * @author renguangli 2018/11/16 14:24
 * @since JDK 1.8
 */
public final class ResponseUtils {

    private ResponseUtils(){}

    public static void buildSuccessResponse(EndpointResponse endpointResponse, FullHttpResponse response) {
        response.content().writeBytes(endpointResponse.getBody().getBytes(CharsetUtil.UTF_8));
        response.headers().set(endpointResponse.getHeaders());
        response.headers().set(HttpHeaderNames.CONTENT_LENGTH,response.content().readableBytes());
    }

    public static void buildNotFoundResponse(FullHttpRequest request, FullHttpResponse response) {
        // 构建 NOT_FOUND
        response.setStatus(HttpResponseStatus.NOT_FOUND);
        response.headers().set(HttpHeaderNames.CONTENT_TYPE, HttpHeaderValues.APPLICATION_JSON);
        String message = "Resource " + request.uri() + " is not exists.";
        ErrorResult errorResult = new ErrorResult(HttpResponseStatus.NOT_FOUND.reasonPhrase(), message);
        response.content().writeBytes(Unpooled.copiedBuffer(JSON.toJSONBytes(errorResult)));
    }

    public static void buildMethodNotSupportResponse(FullHttpRequest request, FullHttpResponse response) {
        // 不支持的请求方法
        response.setStatus(HttpResponseStatus.METHOD_NOT_ALLOWED);
        String message = "Request method '" + request.method().name() + "' not supported";
        ErrorResult errorResult = new ErrorResult(HttpResponseStatus.METHOD_NOT_ALLOWED.reasonPhrase(), message);
        buildErrorResponse(response, errorResult);
    }

    public static void buildErrorResponse(FullHttpResponse response, ErrorResult errorResult) {
        response.content().writeBytes(Unpooled.copiedBuffer(JSON.toJSONBytes(errorResult)));
        response.headers().set(HttpHeaderNames.CONTENT_LENGTH,response.content().readableBytes());
        response.headers().set(HttpHeaderNames.CONTENT_TYPE, HttpHeaderValues.APPLICATION_JSON);
    }


}
