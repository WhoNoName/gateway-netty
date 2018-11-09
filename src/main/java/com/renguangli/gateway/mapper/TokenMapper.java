package com.renguangli.gateway.mapper;


import com.renguangli.gateway.pojo.Token;

/**
 * TokenMapper
 *
 * @author renguangli 2018/10/29 14:06
 * @since JDK 1.8
 */
public interface TokenMapper {

    int saveToken(Token token);

    Token getToken(String token);

    Token getTokenByClientId(String client);

    int updateToken(String clientId);
}
