package com.fjx.common.cache;

import org.apache.log4j.Logger;

import com.fjx.common.utils.LoggerUtil;

/**
 * 
 *  缓存日志扩展
 * 
 * @author jie.hua@alipay.com
 * @version $Id: CacheLoggerWapper.java, v 0.1 2014-1-9 上午3:55:25 jie.hua Exp $
 */
public class CacheLoggerWapper implements Cache {

    /**
     * 操作名称
     */
    private static final String OPERATION_NAME = "【缓存操作】";

    /**
     * 日志
     */
    private static final Logger logger = Logger.getLogger(CacheLoggerWapper.class);

    /**
     * 目标缓存
     */
    private Cache cache;

    /**
     * @param cache
     */
    public CacheLoggerWapper(Cache cache) {
        super();
        this.cache = cache;
    }

    /** 
     * @see com.alipay.demo.cache.Cache#put(java.lang.String, java.lang.Object)
     */
    @Override
    public void put(String key, Object value) {

        this.cache.put(key, value);

        LoggerUtil.info(logger, OPERATION_NAME + ",保存数据：[key=" + key + ",value=" + value + "]");

    }

    /** 
     * @see com.alipay.demo.cache.Cache#get(java.lang.String)
     */
    @Override
    public Object get(String key) {

        Object object = this.cache.get(key);

        LoggerUtil.info(logger, OPERATION_NAME + ",获取数据：[key=" + key + ",value=" + object + "]");

        return object;
    }

}
