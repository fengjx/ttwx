/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.fjx.common.utils;

import java.util.Collection;
import java.util.Map;

/**
 * ��������
 * 
 * @author jie.hua@alipay.com
 * @version $Id: CollectionUtil.java, v 0.1 2014-1-8 ����11:08:05 jie.hua Exp $
 */
public class CollectionUtil {

    /**
     * �ж�����Ϊ��
     * 
     * @param c
     * @return
     */
    public static boolean isEmpty(Map<?, ?> c) {

        return c == null || c.isEmpty();
    }

    /**
     * �ж�������Ϊ��
     * 
     * @param c
     * @return
     */
    public static boolean isNotEmpty(Map<?, ?> c) {

        return !isEmpty(c);
    }

    /**
     * �ж�����Ϊ��
     * 
     * @param c
     * @return
     */
    public static boolean isEmpty(Collection<?> c) {

        return c == null || c.isEmpty();
    }

    /**
     * �ж�������Ϊ��
     * 
     * @param c
     * @return
     */
    public static boolean isNotEmpty(Collection<?> c) {

        return !isEmpty(c);
    }

}
