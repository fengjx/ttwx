
package com.fengjx.commons.plugin.cache.memory;

import com.fengjx.commons.plugin.cache.IDataLoader;
import com.fengjx.commons.plugin.cache.SimpleCache;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

import java.util.concurrent.TimeUnit;

/**
 * 基于内存缓存实现
 */
public class MemoryCache implements SimpleCache {

    /**
     * cache实现
     */
    private Cache<String, Object> cache;

    /**
     * 构建缓存实例
     * 
     * @param duration
     * @param timeUnit
     */
    public MemoryCache(long duration, TimeUnit timeUnit) {

        cache = CacheBuilder.newBuilder().expireAfterWrite(duration, timeUnit).maximumSize(10000)
                .build();
    }

    /**
     * 实例holder类
     */
    private static class CacheHolder {

        /**
         * 时间跨度
         */
        private static final long duration = 5;

        /**
         * 时间单位
         */
        private static final TimeUnit timeUnit = TimeUnit.MINUTES;

        /**
         * 实例
         */
        public static final MemoryCache instance = new MemoryCache(duration, timeUnit);
    }

    /**
     * 获取实例
     * 
     * @return
     */
    public static MemoryCache getInstance() {

        return MemoryCache.CacheHolder.instance;
    }

    /**
     * 放置缓存
     * 
     * @param key
     * @param value
     */
    public boolean add(String key, Object value) {
        this.cache.put(key, value);
        return true;
    }

    /**
     * 删除缓存
     *
     * @param key
     * @return
     */
    @Override
    public boolean remove(String key) {
        this.cache.put(key, null);
        return true;
    }

    /**
     * 删除全部缓存
     *
     * @return
     */
    @Override
    public boolean removeAll() {
        this.cache.cleanUp();
        return true;
    }

    /**
     * 返回缓存值
     * 
     * @param key
     * @return
     */
    @SuppressWarnings("unchecked")
    public <T> T get(String key) {
        return (T) this.cache.getIfPresent(key);
    }

    /**
     * 获得缓存，当缓存不存在时，从数据加载器取数据
     *
     * @param key
     * @param dataLoader 数据加载器
     * @return
     */
    @Override
    @SuppressWarnings("unchecked")
    public <T> T get(String key, IDataLoader<T> dataLoader) {
        Object o = get(key);
        if (null != o) {
            return (T) o;
        }
        T t = dataLoader.load();
        add(key, t);
        return t;
    }

}
