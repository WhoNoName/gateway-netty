package com.renguangli.gateway.mapper;

import com.renguangli.gateway.pojo.Api;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * ApiMapper
 *
 * @author renguangli 2018/10/29 14:07
 * @since JDK 1.8
 */
public interface ApiMapper {

    Api getApiByContext(@Param("context") String context);

    List<Api> listApis();

}
