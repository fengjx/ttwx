
package com.fengjx.commons.plugin.cache;

/**
 * @version 2016/1/21
 * @Created by FengJianxin
 * @Email xd-fjx@qq.com
 */
public class SimpleCacheUtil {

    private static final SimpleCache cache = CacheFactory.getDefaultSimpleCache();

    /**
     * 添加缓存
     *
     * @param key
     * @param value
     * @return
     */
    public static boolean add(String key, Object value) {
        return cache.add(key, value);
    }

    /**
     * 删除缓存
     *
     * @param key
     * @return
     */
    public static boolean remove(String key) {
        return cache.remove(key);
    }

    /**
     * 删除全部缓存
     *
     * @return
     */
    public static boolean removeAll() {
        return cache.removeAll();
    }

    /**
     * 取出缓存数据
     *
     * @param key
     * @return
     */
    public static <T> T get(String key) {
        return cache.get(key);
    }

    /**
     * 获得缓存，当缓存不存在时，从数据加载器取数据
     *
     * @param key
     * @param dataLoader 数据加载器
     * @param <T>
     * @return
     */
    public static <T> T get(String key, IDataLoader<T> dataLoader) {
        return cache.get(key, dataLoader);
    }

}
