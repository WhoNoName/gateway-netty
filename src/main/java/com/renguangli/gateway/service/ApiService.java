package com.renguangli.gateway.service;

import com.renguangli.gateway.pojo.Api;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * ApiService
 *
 * @author renguangli 2018/11/8 21:56
 * @since JDK 1.8
 */
public interface ApiService {

    Api getApiByContext(String context);

    List<Api> listApis();
}
