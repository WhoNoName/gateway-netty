package com.renguangli.gateway.cache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * MapCacheManager
 *
 * @author renguangli 2018/11/8 21:27
 * @since JDK 1.8
 */
public class MapCacheManager extends CacheManager {

    private static Map<String, Object> map = new ConcurrentHashMap<>();

    @Override
    public void put(String key, Object value) {
        map.put(key, value);
    }

    @Override
    public Object get(String key) {
        return map.get(key);
    }
}
