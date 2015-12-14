
package com.fengjx.commons.plugin.cache;

/**
 * 简单缓存接口
 *
 * @author fengjx.
 * @date：2015/6/3 0003
 */
public interface SimpleCache {

    /**
     * 添加缓存
     *
     * @param key
     * @param value
     * @return
     */
    boolean add(String key, Object value);

    /**
     * 删除缓存
     *
     * @param key
     * @return
     */
    boolean remove(String key);

    /**
     * 删除全部缓存
     *
     * @return
     */
    boolean removeAll();

    /**
     * 取出缓存数据
     *
     * @param key
     * @return
     */
    <T> T get(String key);


    /**
     * 获得缓存，当缓存不存在时，从数据加载器取数据
     *
     * @param key
     * @param dataLoader 数据加载器
     * @param <T>
     * @return
     */
    <T> T get(String key, IDataLoader<T> dataLoader);
}
