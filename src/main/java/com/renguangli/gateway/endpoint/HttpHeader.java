package com.renguangli.gateway.endpoint;

/**
 * HttpHeader
 *
 * @author renguangli 2018/11/8 14:53
 * @since JDK 1.8
 */
public class HttpHeader {

    private String name;

    private String value;

    public HttpHeader() {}

    public HttpHeader(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Header{" +
                "name=" + name +
                ", value=" + value +
                "}";
    }
}
