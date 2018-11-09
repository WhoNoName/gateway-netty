package com.renguangli.gateway.mapper;


import com.renguangli.gateway.pojo.ClientDetails;

/**
 * ClientDetailsMapper
 *
 * @author renguangli 2018/10/29 14:06
 * @since JDK 1.8
 */
public interface ClientDetailsMapper {

    ClientDetails getClientDetails(String clientId);

}
