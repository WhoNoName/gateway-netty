package com.renguangli.gateway.service.impl;

import com.renguangli.gateway.mapper.ApiMapper;
import com.renguangli.gateway.pojo.Api;
import com.renguangli.gateway.service.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ApiServiceImpl
 *
 * @author renguangli 2018/11/8 21:56
 * @since JDK 1.8
 */
@Service
public class ApiServiceImpl implements ApiService {

    private final ApiMapper apiMapper;

    @Autowired
    public ApiServiceImpl(ApiMapper apiMapper) {
        this.apiMapper = apiMapper;
    }

    @Override
    public Api getApiByContext(String context) {
        return apiMapper.getApiByContext(context);
    }

    @Override
    public List<Api> listApis() {
        return apiMapper.listApis();
    }

}
