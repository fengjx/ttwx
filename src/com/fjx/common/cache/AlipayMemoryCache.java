package com.fjx.common.cache;

import java.util.concurrent.TimeUnit;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

/**
 * 内存版本缓存实现
 * 
 * @author jie.hua@alipay.com
 * @version $Id: AlipayCache.java, v 0.1 2014-1-8 下午10:13:18 jie.hua Exp $
 */
public class AlipayMemoryCache implements com.fjx.common.cache.Cache {

    /**
     * cache实现
     */
    private Cache<String, Object> cache;

    /**
     * 构建缓存实例
     * @param duration
     * @param timeUnit
     */
    public AlipayMemoryCache(long duration, TimeUnit timeUnit) {

        cache = CacheBuilder.newBuilder().expireAfterWrite(duration, timeUnit).maximumSize(10000)
            .build();
    }

    /**
     * 实例holder类
     * 
     * @author jie.hua@alipay.com
     * @version $Id: AlipayCache.java, v 0.1 2014-1-8 下午10:14:00 jie.hua Exp $
     */
    private static class CacheHolder {

        /**
         * 时间跨度
         */
        private static final long             duration = 5;

        /**
         * 时间单位
         */
        private static final TimeUnit         timeUnit = TimeUnit.MINUTES;

        /**
         * 实例
         */
        public static final AlipayMemoryCache instance = new AlipayMemoryCache(duration, timeUnit);
    }

    /**
     * 获取实例
     * 
     * @return
     */
    public static AlipayMemoryCache getInstance() {

        return AlipayMemoryCache.CacheHolder.instance;
    }

    /**
     * 放置缓存
     * 
     * @param key
     * @param value
     */
    public void put(String key, Object value) {

        this.cache.put(key, value);

    }

    /**
     * 返回缓存值
     * 
     * @param key
     * @return
     */
    public Object get(String key) {

        return this.cache.getIfPresent(key);
    }

}
