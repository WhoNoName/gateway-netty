package com.renguangli.gateway.service.impl;

import com.renguangli.gateway.mapper.ClientDetailsMapper;
import com.renguangli.gateway.MybatisSqlSessionFactory;
import com.renguangli.gateway.pojo.ClientDetails;
import com.renguangli.gateway.service.ClientDetailsService;
import org.apache.ibatis.session.SqlSession;

/**
 * ClientDetailsServiceImpl
 *
 * @author renguangli 2018/10/25 15:03
 * @since JDK 1.8
 */
public class ClientDetailsServiceImpl implements ClientDetailsService {

    @Override
    public ClientDetails getClientDetails(String clientId) {
        SqlSession sqlSession = MybatisSqlSessionFactory.openSession();
        ClientDetailsMapper clientDetailsMapper = sqlSession.getMapper(ClientDetailsMapper.class);
        ClientDetails clientDetails = clientDetailsMapper.getClientDetails(clientId);
        sqlSession.close();
        return clientDetails;
    }

    @Override
    public int saveClientDetails(ClientDetails clientDetails) {
        return 0;
    }
}
