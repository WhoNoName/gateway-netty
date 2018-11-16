package com.renguangli.gateway.service.impl;

import com.renguangli.gateway.mapper.TokenMapper;
import com.renguangli.gateway.pojo.Token;
import com.renguangli.gateway.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * TokenServiceImpl
 *
 * @author renguangli 2018/10/25 14:23
 * @since JDK 1.8
 */
@Service("tokenService")
public class TokenServiceImpl implements TokenService {

    private final TokenMapper tokenMapper;

    @Autowired
    public TokenServiceImpl(TokenMapper tokenMapper) {
        this.tokenMapper = tokenMapper;
    }

    @Override
    public int saveToken(Token token) {
        return tokenMapper.saveToken(token);
    }

    @Override
    public Token getToken(String accessToken) {
        return tokenMapper.getToken(accessToken);
    }

    @Override
    public Token getTokenByClientId(String clientId) {
        return tokenMapper.getTokenByClientId(clientId);
    }

    @Override
    public void updateToken(String clientId) {
        tokenMapper.updateToken(clientId);
    }

}
