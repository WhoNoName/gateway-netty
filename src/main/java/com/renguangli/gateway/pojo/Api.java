package com.renguangli.gateway.pojo;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Api
 *
 * @author renguangli 2018/11/8 20:15
 * @since JDK 1.8
 */
public class Api implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer apiId;

    private String apiName;

    private String apiVersion;

    private String apiContext;

    private String context;

    private String description;

    private String icon;

    private String endpoint;

    private String uri;

    private String method;

    private String protocol;

    private String contentType;

    private String params;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private String apiProvider;

    private String apiConsumer;
    public Api(){}

    public Integer getApiId() {
        return apiId;
    }

    public void setApiId(Integer apiId) {
        this.apiId = apiId;
    }

    public String getApiName() {
        return apiName;
    }

    public void setApiName(String apiName) {
        this.apiName = apiName;
    }

    public String getApiVersion() {
        return apiVersion;
    }

    public void setApiVersion(String apiVersion) {
        this.apiVersion = apiVersion;
    }

    public String getApiContext() {
        return apiContext;
    }

    public void setApiContext(String apiContext) {
        this.apiContext = apiContext;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    public String getApiProvider() {
        return apiProvider;
    }

    public void setApiProvider(String apiProvider) {
        this.apiProvider = apiProvider;
    }

    public String getApiConsumer() {
        return apiConsumer;
    }

    public void setApiConsumer(String apiConsumer) {
        this.apiConsumer = apiConsumer;
    }

    @Override
    public String toString() {
        return "Api{" +
                "apiId=" + apiId +
                ", apiName=" + apiName +
                ", apiVersion=" + apiVersion +
                ", apiContext=" + apiContext +
                ", context=" + context +
                ", description=" + description +
                ", icon=" + icon +
                ", endpoint=" + endpoint +
                ", uri=" + uri +
                ", method=" + method +
                ", protocol=" + protocol +
                ", contentType=" + contentType +
                ", params=" + params +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", apiProvider=" + apiProvider +
                ", apiConsumer=" + apiConsumer +
                "}";
    }
}
