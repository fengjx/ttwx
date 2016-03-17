
package com.fengjx.commons.plugin.cache.ehcache;

import com.fengjx.commons.plugin.cache.IDataLoader;
import com.fengjx.commons.plugin.cache.SimpleCache;
import com.fengjx.modules.common.constants.AppConfig;

/**
 * @author fengjx.
 * @date：2015/6/9 0009
 */
public class SimpleEhCache implements SimpleCache {

    private static final String CACHE_NAME = AppConfig.EhcacheName.DEFAULT_CACHE;

    /**
     * 添加缓存
     *
     * @param key
     * @param value
     * @return
     */
    @Override
    public boolean add(String key, Object value) {
        EhCacheUtil.put(CACHE_NAME, key, value);
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
        EhCacheUtil.remove(CACHE_NAME, key);
        return true;
    }

    /**
     * 删除全部缓存
     *
     * @return
     */
    @Override
    public boolean removeAll() {
        EhCacheUtil.removeAll(CACHE_NAME);
        return false;
    }

    /**
     * 取出缓存数据
     *
     * @param key
     * @return
     */
    @Override
    public <T> T get(String key) {
        EhCacheUtil.get(CACHE_NAME, key);
        return null;
    }

    /**
     * 获得缓存，当缓存不存在时，从数据加载器取数据
     *
     * @param key
     * @param dataLoader 数据加载器
     * @return
     */
    @Override
    public <T> T get(String key, IDataLoader<T> dataLoader) {
        return EhCacheUtil.get(CACHE_NAME, key, dataLoader);
    }
}
