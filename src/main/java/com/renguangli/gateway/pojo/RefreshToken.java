package com.renguangli.gateway.pojo;

/**
 * RefreshToken
 *
 * @author renguangli 2018/10/25 13:22
 * @since JDK 1.8
 */
public class RefreshToken {

    private Integer tokenId;

    private String token;

    private String authentication;

    public Integer getTokenId() {
        return tokenId;
    }

    public void setTokenId(Integer tokenId) {
        this.tokenId = tokenId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getAuthentication() {
        return authentication;
    }

    public void setAuthentication(String authentication) {
        this.authentication = authentication;
    }

    @Override
    public String toString() {
        return "RefreshToken{" +
                "tokenId=" + tokenId +
                ", token=" + token +
                ", authentication=" + authentication +
                "}";
    }
}
