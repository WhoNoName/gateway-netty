package com.renguangli.gateway.service;


import com.renguangli.gateway.pojo.ClientDetails;

/**
 * ClientDetailsService
 *
 * @author renguangli 2018/10/25 15:03
 * @since JDK 1.8
 */
public interface ClientDetailsService {

    int saveClientDetails(ClientDetails clientDetails);

    ClientDetails getClientDetails(String clientId);

}
