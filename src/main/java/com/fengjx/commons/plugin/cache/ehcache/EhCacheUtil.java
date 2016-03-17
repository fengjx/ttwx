
package com.fengjx.commons.plugin.cache.ehcache;

import com.fengjx.commons.plugin.cache.IDataLoader;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * 封装ehcahce
 *
 * @author fengjx.
 * @date：2015/6/3 0003
 */
public class EhCacheUtil {

    private static volatile CacheManager cacheManager;

    private static final Logger LOG = LoggerFactory.getLogger(EhCacheUtil.class);

    static void init(CacheManager cacheManager) {
        EhCacheUtil.cacheManager = cacheManager;
    }

    public static CacheManager getCacheManager() {
        return cacheManager;
    }

    static Cache getOrAddCache(String cacheName) {
        Cache cache = cacheManager.getCache(cacheName);
        if (cache == null) {
            synchronized (cacheManager) {
                cache = cacheManager.getCache(cacheName);
                if (cache == null) {
                    LOG.warn("Could not find cache config [" + cacheName + "], using default.");
                    cacheManager.addCacheIfAbsent(cacheName);
                    cache = cacheManager.getCache(cacheName);
                    LOG.debug("Cache [" + cacheName + "] started.");
                }
            }
        }
        return cache;
    }

    public static void put(String cacheName, Object key, Object value) {
        getOrAddCache(cacheName).put(new Element(key, value));
    }

    @SuppressWarnings("unchecked")
    public static <T> T get(String cacheName, Object key) {
        Element element = getOrAddCache(cacheName).get(key);
        return element != null ? (T) element.getObjectValue() : null;
    }

    @SuppressWarnings("rawtypes")
    public static List getKeys(String cacheName) {
        return getOrAddCache(cacheName).getKeys();
    }

    public static void remove(String cacheName, Object key) {
        getOrAddCache(cacheName).remove(key);
    }

    public static void removeAll(String cacheName) {
        getOrAddCache(cacheName).removeAll();
    }

    @SuppressWarnings("unchecked")
    public static <T> T get(String cacheName, Object key, IDataLoader<T> dataLoader) {
        T data = get(cacheName, key);
        if (data == null) {
            data = dataLoader.load();
            put(cacheName, key, data);
        }
        return data;
    }

    @SuppressWarnings("unchecked")
    public static <T> T get(String cacheName, Object key,
            Class<? extends IDataLoader<T>> dataLoaderClass) {
        T data = get(cacheName, key);
        if (data == null) {
            try {
                IDataLoader<T> dataLoader = dataLoaderClass.newInstance();
                data = dataLoader.load();
                put(cacheName, key, data);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return data;
    }

}
