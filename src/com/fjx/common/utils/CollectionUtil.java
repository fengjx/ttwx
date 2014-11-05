package com.fjx.common.utils;

import java.util.Collection;
import java.util.Map;

/**
 * 容器工具
 * 
 * @author jie.hua@alipay.com
 * @version $Id: CollectionUtil.java, v 0.1 2014-1-8 下午11:08:05 jie.hua Exp $
 */
public class CollectionUtil {

    /**
     * 判断容器为空
     * 
     * @param c
     * @return
     */
    public static boolean isEmpty(Map<?, ?> c) {

        return c == null || c.isEmpty();
    }

    /**
     * 判断容器不为空
     * 
     * @param c
     * @return
     */
    public static boolean isNotEmpty(Map<?, ?> c) {

        return !isEmpty(c);
    }

    /**
     * 判断容器为空
     * 
     * @param c
     * @return
     */
    public static boolean isEmpty(Collection<?> c) {

        return c == null || c.isEmpty();
    }

    /**
     * 判断容器不为空
     * 
     * @param c
     * @return
     */
    public static boolean isNotEmpty(Collection<?> c) {

        return !isEmpty(c);
    }

}
