
package com.fengjx.commons.utils;

import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * 基于apache common bean的实体与Map转换
 *
 * @author fengjx.
 * @date：2015/5/18 0018
 */
public final class BeanUtil {

    /**
     * 实体转map
     *
     * @param bean
     * @return
     */
    @SuppressWarnings("unchecked")
    public static final Map<String, Object> bean2Map(Object bean) {
        try {
            return BeanUtils.describe(bean);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * map转实体
     *
     * @param bean
     * @param map
     */
    public static final void map2Bean(Object bean, Map<String, Object> map) {
        try {
            BeanUtils.populate(bean, map);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

}
