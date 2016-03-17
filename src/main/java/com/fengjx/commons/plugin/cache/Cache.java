
package com.fengjx.commons.plugin.cache;

import java.util.Map;

/**
 * 缓存接口
 *
 * @author fengjx.
 * @date：2015/6/3 0003
 */
public interface Cache extends SimpleCache {

    /**
     * 添加缓存，并设置过期时间
     *
     * @param key
     * @param value
     * @param seconds 过期时间(秒)，注意客户端与服务端时间是否一致
     * @return
     */
    boolean add(String key, Object value, int seconds);

    /**
     * 更新缓存
     *
     * @param key
     * @param value
     * @return
     */
    boolean update(String key, Object value);

    /**
     * 更新缓存，并设置过期时间
     *
     * @param key
     * @param value
     * @param seconds 过期时间(秒)，注意客户端与服务端时间是否一致
     * @return
     */
    boolean update(String key, Object value, int seconds);

    /**
     * 根据key数组取出没有过期的缓存数据
     *
     * @param keyArr
     * @return
     */
    Map<String, Object> getMulti(String[] keyArr);

}
