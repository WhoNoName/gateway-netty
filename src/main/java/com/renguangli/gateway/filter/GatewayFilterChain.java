package com.renguangli.gateway.filter;

import java.util.ArrayList;
import java.util.List;

/**
 * GatewayFilterChain
 *
 * @author renguangli 2018/11/7 18:47
 * @since JDK 1.8
 */
public class GatewayFilterChain {

    private List<GatewayFilter> filters = new ArrayList<>();

    public void addFilter(GatewayFilter gatewayFilter) {
        filters.add(gatewayFilter);
    }


}
