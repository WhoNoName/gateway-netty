package com.renguangli.gateway.pojo;

/**
 * Token
 *
 * @author renguangli 2018/10/25 13:14
 * @since JDK 1.8
 */
public class Token {

    private Integer tokenId;

    private String token;

    private Integer authenticationId;

    private String username;

    private String clientId;

    private String authentication;

    private String refreshToken;

    /**
     * 0 有效 1无效
     */
    private Integer valid;

    private Long expiredTimestamp;

    private Long createTimestamp;

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

    public Integer getAuthenticationId() {
        return authenticationId;
    }

    public void setAuthenticationId(Integer authenticationId) {
        this.authenticationId = authenticationId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getAuthentication() {
        return authentication;
    }

    public void setAuthentication(String authentication) {
        this.authentication = authentication;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public Integer getValid() {
        return valid;
    }

    public void setValid(Integer valid) {
        this.valid = valid;
    }

    public Long getExpiredTimestamp() {
        return expiredTimestamp;
    }

    public void setExpiredTimestamp(Long expiredTimestamp) {
        this.expiredTimestamp = expiredTimestamp;
    }

    public Long getCreateTimestamp() {
        return createTimestamp;
    }

    public void setCreateTimestamp(Long createTimestamp) {
        this.createTimestamp = createTimestamp;
    }

    @Override
    public String toString() {
        return "Token{" +
                "tokenId=" + tokenId +
                ", token=" + token +
                ", authenticationId=" + authenticationId +
                ", username=" + username +
                ", clientId=" + clientId +
                ", authentication=" + authentication +
                ", refreshToken=" + refreshToken +
                ", valid=" + valid +
                ", expiredTimestamp=" + expiredTimestamp +
                ", createTimestamp=" + createTimestamp +
                "}";
    }
}
