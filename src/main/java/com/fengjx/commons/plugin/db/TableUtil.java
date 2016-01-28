
package com.fengjx.commons.plugin.db;

import com.fengjx.commons.plugin.db.annotation.Mapper;
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
            throw new MyDbException(cls.getName() + "没有添加@Mapper注解");
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
            throw new RuntimeException(cls.getName() + "没有添加@Mapper注解");
        }
        String[] pk;
        String id = mapper.id();
        if (StringUtils.isBlank(id)) {
            throw new RuntimeException(cls.getName() + "@Mapper注解没有指定ID");
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
            throw new MyDbException(cls.getName() + "没有添加@Mapper注解");
        }
        return mapper.pid();
    }

    /**
     * 通过class获得映射Table
     *
     * @param cls
     * @return
     */
    public static Table getTable(Class<? extends BaseBean> cls) {
        return TableMapping.me().getTable(cls);
    }

}
