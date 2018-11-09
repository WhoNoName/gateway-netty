package com.renguangli.gateway.endpoint;

import io.netty.handler.codec.http.HttpHeaders;

/**
 * CustomRequest
 *
 * @author renguangli 2018/11/8 14:42
 * @since JDK 1.8
 */
public class EndpointResponse {

    private int code;

    private HttpHeaders httpHeaders;

    private String body;

    public EndpointResponse(){}

    public EndpointResponse(int code, HttpHeaders httpHeaders, String body) {
        this.code = code;
        this.httpHeaders = httpHeaders;
        this.body = body;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public HttpHeaders getHeaders() {
        return httpHeaders;
    }

    public void setHeaders(HttpHeaders headers) {
        this.httpHeaders = headers;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return "EndpointResponse{" +
                "code=" + code +
                ", headers=" + httpHeaders +
                ", body=" + body +
                "}";
    }
}
