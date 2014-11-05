package com.fjx.common.cache;

/**
 * 缓存接口
 * 
 * @author jie.hua@alipay.com
 * @version $Id: Cache.java, v 0.1 2014-1-8 下午10:24:25 jie.hua Exp $
 */
public interface Cache {

    /**
     * 放置缓存
     * 
     * @param key
     * @param value
     */
    public void put(String key, Object value);

    /**
     * 返回缓存值
     * 
     * @param key
     * @return
     */
    public Object get(String key);

}
