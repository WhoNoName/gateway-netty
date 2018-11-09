package com.renguangli.gateway.service.impl;

import com.renguangli.gateway.mapper.TokenMapper;
import com.renguangli.gateway.MybatisSqlSessionFactory;
import com.renguangli.gateway.pojo.Token;
import com.renguangli.gateway.service.TokenService;
import org.apache.ibatis.session.SqlSession;

/**
 * TokenServiceImpl
 *
 * @author renguangli 2018/10/25 14:23
 * @since JDK 1.8
 */
public class TokenServiceImpl implements TokenService {

    @Override
    public int saveToken(Token token) {
        token.setAuthenticationId(0);
        token.setValid(0);
        SqlSession sqlSession = MybatisSqlSessionFactory.openSession();
        TokenMapper tokenMapper = sqlSession.getMapper(TokenMapper.class);
        int i = tokenMapper.saveToken(token);
        sqlSession.commit();
        sqlSession.close();
        return i;
    }

    @Override
    public Token getToken(String accessToken) {
        SqlSession sqlSession = MybatisSqlSessionFactory.openSession();
        TokenMapper tokenMapper = sqlSession.getMapper(TokenMapper.class);
        Token token = tokenMapper.getToken(accessToken);
        sqlSession.close();
        return token;
    }

    @Override
    public Token getTokenByClientId(String clientId) {
        SqlSession sqlSession = MybatisSqlSessionFactory.openSession();
        TokenMapper tokenMapper = sqlSession.getMapper(TokenMapper.class);
        Token token = tokenMapper.getTokenByClientId(clientId);
        sqlSession.close();
        return token;
    }

    @Override
    public void updateToken(String clientId) {
        SqlSession sqlSession = MybatisSqlSessionFactory.openSession();
        TokenMapper tokenMapper = sqlSession.getMapper(TokenMapper.class);
        tokenMapper.updateToken(clientId);
        sqlSession.commit();
        sqlSession.close();
    }

}
