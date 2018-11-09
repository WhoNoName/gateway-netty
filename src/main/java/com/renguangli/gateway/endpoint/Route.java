package com.renguangli.gateway.endpoint;

/**
 * Route
 *
 * @author renguangli 2018/11/8 10:34
 * @since JDK 1.8
 */
public class Route {

    private String uri;

    private String proxy;

    public Route(){}

    public Route(String uri, String proxy) {
        this.uri = uri;
        this.proxy = proxy;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getProxy() {
        return proxy;
    }

    public void setProxy(String proxy) {
        this.proxy = proxy;
    }

    @Override
    public String toString() {
        return "Route{" +
                "uri=" + uri +
                ", proxy=" + proxy +
                "}";
    }
}
