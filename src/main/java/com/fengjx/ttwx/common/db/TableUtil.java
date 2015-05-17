
package com.fengjx.ttwx.common.db;

import org.apache.commons.lang3.StringUtils;

/**
 * @author fengjx.
 * @date：2015/5/9 0009
 */
public class TableUtil {

    public static String getTableName(Class<? extends Model> cls) {
        Mapper mapper = cls.getAnnotation(Mapper.class);
        if (null == mapper) {
            throw new MyDbException(cls.getName() + "没有添加@Table注解");
        }
        return mapper.table();
    }

    public static String getPrimaryKey(Class<? extends Model> cls) {
        Mapper mapper = cls.getAnnotation(Mapper.class);
        if (null == mapper) {
            throw new MyDbException(cls.getName() + "没有添加@Table注解");
        }
        String pk = mapper.id();
        return StringUtils.isBlank(pk) ? Config.dialect.getDefaultPrimaryKey() : pk;
    }

    public static Table getTable(Class<? extends Model> cls) {
        return TableMapping.me().getTable(cls);
    }

}
