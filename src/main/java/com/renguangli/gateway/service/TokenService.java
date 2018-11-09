package com.renguangli.gateway.service;

import com.renguangli.gateway.pojo.Token;
import com.renguangli.gateway.service.impl.TokenServiceImpl;

/**
 * TokenService
 *
 * @author renguangli 2018/10/25 14:21
 * @since JDK 1.8
 */
public interface TokenService {

    TokenService tokenService = new TokenServiceImpl();

    int saveToken(Token tokenVO);

    Token getToken(String accessToken);

    Token getTokenByClientId(String clientId);

    void updateToken(String clientId);
}
