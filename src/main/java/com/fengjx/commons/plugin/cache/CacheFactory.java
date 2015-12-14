
package com.fengjx.commons.plugin.cache;

import com.fengjx.commons.plugin.cache.memory.MemoryCache;

import java.util.HashMap;
import java.util.Map;

/**
 * 缓存工厂
 *
 * @author fengjx.
 * @date：2015/6/9 0009
 */
public class CacheFactory {

    private static class CacheFactoryHolder {
        private static Map<CacheName, SimpleCache> cacheMaps;

        static {
            cacheMaps = new HashMap<>();
            cacheMaps.put(CacheName.CACHE_NAME_MEMORY, MemoryCache.getInstance());
        }
    }

    /**
     * 默认缓存实现
     *
     * @return
     */
    public static SimpleCache getDefaultSimpleCache() {
        return CacheFactoryHolder.cacheMaps.get(CacheName.CACHE_NAME_MEMORY);
    }

    public static SimpleCache getSimpleCache(CacheName cacheName) {
        return CacheFactoryHolder.cacheMaps.get(cacheName);
    }

}
