package com.renguangli.gateway.service.impl;

import com.renguangli.gateway.MybatisSqlSessionFactory;
import com.renguangli.gateway.mapper.ApiMapper;
import com.renguangli.gateway.pojo.Api;
import com.renguangli.gateway.service.ApiService;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

/**
 * ApiServiceImpl
 *
 * @author renguangli 2018/11/8 21:56
 * @since JDK 1.8
 */
public class ApiServiceImpl implements ApiService {

    @Override
    public Api getApiByContext(String context) {
        SqlSession sqlSession = MybatisSqlSessionFactory.openSession();
        ApiMapper apiMapper = sqlSession.getMapper(ApiMapper.class);
        Api api = apiMapper.getApiByContext(context);
        sqlSession.close();
        return api;
    }

    @Override
    public List<Api> listApis() {
        SqlSession sqlSession = MybatisSqlSessionFactory.openSession();
        ApiMapper apiMapper = sqlSession.getMapper(ApiMapper.class);
        List<Api> apis = apiMapper.listApis();
        sqlSession.close();
        return apis;
    }

}
