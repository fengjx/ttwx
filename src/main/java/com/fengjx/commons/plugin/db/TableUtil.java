
package com.fengjx.commons.plugin.db;

import org.apache.commons.lang3.StringUtils;

/**
 * @author fengjx. @date：2015/5/9 0009
 */
public class TableUtil {

    /**
     * 解析Mapper注解中的表名
     *
     * @param cls
     * @return
     */
    public static String getTableName(Class<? extends BaseBean> cls) {
        Mapper mapper = cls.getAnnotation(Mapper.class);
        if (null == mapper) {
            throw new MyDbException(cls.getName() + "没有添加@Table注解");
        }
        return mapper.table();
    }

    /**
     * 解析Mapper注解中的主键名称
     *
     * @param cls
     * @return
     */
    public static String[] getPrimaryKey(Class<? extends BaseBean> cls) {
        Mapper mapper = cls.getAnnotation(Mapper.class);
        if (null == mapper) {
            throw new RuntimeException(cls.getName() + "没有添加@Table注解");
        }
        String[] pk;
        String id = mapper.id();
        if (StringUtils.isBlank(id)) {
            pk = new String[] {
                    Config.dialect.getDefaultPrimaryKey()
            };
        } else {
            pk = StringUtils.split(id, ",");
        }
        return pk;
    }

    /**
     * 获得树形结构表的父级ID
     *
     * @param cls
     * @return
     */
    public static String getParentId(Class<? extends BaseBean> cls) {
        Mapper mapper = cls.getAnnotation(Mapper.class);
        if (null == mapper) {
            throw new MyDbException(cls.getName() + "没有添加@Table注解");
        }
        return mapper.pid();
    }

    /**
     * 通过class获得映射table
     *
     * @param cls
     * @return
     */
    public static Table getTable(Class<? extends BaseBean> cls) {
        return TableMapping.me().getTable(cls);
    }

}
