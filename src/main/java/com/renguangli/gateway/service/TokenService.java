package com.renguangli.gateway.service;

import com.renguangli.gateway.pojo.Token;

/**
 * TokenService
 *
 * @author renguangli 2018/10/25 14:21
 * @since JDK 1.8
 */
public interface TokenService {

    int saveToken(Token tokenVO);

    Token getToken(String accessToken);

    Token getTokenByClientId(String clientId);

    void updateToken(String clientId);
}
