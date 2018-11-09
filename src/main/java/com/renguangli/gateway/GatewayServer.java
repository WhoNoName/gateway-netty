package com.renguangli.gateway;

import com.renguangli.gateway.cache.CacheManager;
import com.renguangli.gateway.cache.CacheType;
import com.renguangli.gateway.cache.MapCacheManager;
import com.renguangli.gateway.cache.RedisCacheManager;
import com.renguangli.gateway.pojo.Api;
import com.renguangli.gateway.service.ApiService;
import com.renguangli.gateway.service.impl.ApiServiceImpl;
import org.apache.commons.configuration.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * GatewayServerStartup
 *
 * @author renguangli 2018/11/7 14:35
 * @since JDK 1.8
 */
public class GatewayServer {

    private static final Logger LOGGER = LoggerFactory.getLogger(GatewayServer.class);

    private static final int port = Configurations.getConfiguration().getInt(ConfigConstants.GATEWAY_SERVER_PORT, 8088);

    public void start() throws InterruptedException {
        this.initCacheManager();
        this.initApis();
        new GatewayServerStartup().start(port);
    }

    private void initCacheManager() {
        Configuration configuration = Configurations.getConfiguration();
        String cacheType = configuration.getString(ConfigConstants.GATEWAY_CACHE_TYPE, "local");
        LOGGER.info("init CacheManager:{}", cacheType);
        if (CacheType.LOCAL.name().equalsIgnoreCase(cacheType)) {
            CacheManager.setCacheManager(new MapCacheManager());
        } else if (CacheType.REDIS.name().equalsIgnoreCase(cacheType)) {
            CacheManager.setCacheManager(new RedisCacheManager());
        }
    }

    private void initApis() {
        ApiService apiService = new ApiServiceImpl();
        CacheManager cacheManager = CacheManager.getCacheManager();
        List<Api> apis = apiService.listApis();
        for (Api api : apis) {
            cacheManager.put(api.getContext(), api);
            LOGGER.info("init api:{}", api.getApiName());
        }
    }

}
