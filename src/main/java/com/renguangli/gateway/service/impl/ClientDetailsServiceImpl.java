package com.renguangli.gateway.service.impl;

import com.renguangli.gateway.mapper.ClientDetailsMapper;
import com.renguangli.gateway.pojo.ClientDetails;
import com.renguangli.gateway.service.ClientDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * ClientDetailsServiceImpl
 *
 * @author renguangli 2018/10/25 15:03
 * @since JDK 1.8
 */
@Service("clientDetailsService")
public class ClientDetailsServiceImpl implements ClientDetailsService {

    private final ClientDetailsMapper clientDetailsMapper;

    @Autowired
    public ClientDetailsServiceImpl(ClientDetailsMapper clientDetailsMapper) {
        this.clientDetailsMapper = clientDetailsMapper;
    }

    @Override
    public ClientDetails getClientDetails(String clientId) {
        return clientDetailsMapper.getClientDetails(clientId);
    }

    @Override
    public int saveClientDetails(ClientDetails clientDetails) {
        return 0;
    }
}
