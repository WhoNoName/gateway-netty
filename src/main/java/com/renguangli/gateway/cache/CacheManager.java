package com.renguangli.gateway.cache;

/**
 * CacheManager
 *
 * @author renguangli 2018/11/8 21:25
 * @since JDK 1.8
 */
public abstract class CacheManager {

    private static CacheManager cacheManager;

    public static CacheManager getCacheManager() {
        return CacheManager.cacheManager;
    }

    public static void setCacheManager(CacheManager cacheManager) {
        CacheManager.cacheManager = cacheManager;
    }

    public abstract void put(String key, Object value);

    public abstract Object get(String key);
}
