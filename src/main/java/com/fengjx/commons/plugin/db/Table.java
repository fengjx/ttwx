
package com.fengjx.commons.plugin.db;

import com.google.common.collect.Maps;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Collections;
import java.util.Map;

/**
 * Table save the table meta info like column name and column type.
 */
public class Table {
    // 表名
    private String name;
    // 主键名称
    private String[] primaryKey;
    // 父级ID，用在树形表
    private String parentId;
    // 字段及类型
    private Map<String, Class<?>> columnTypeMap = Maps.newHashMap();
    // 查询字段
    private String columnsStr;

    private Class<? extends BaseBean> beanClass;

    private Config config;

    public Table(Class<? extends BaseBean> beanClass) {
        if (beanClass == null){
            throw new IllegalArgumentException("Model class can not be null.");
        }
        String tableName = TableUtil.getTableName(beanClass);
        if (StringUtils.isBlank(tableName)){
            throw new IllegalArgumentException("Table name can not be blank.");
        }
        String[] primaryKey = TableUtil.getPrimaryKey(beanClass);
        if (ArrayUtils.isEmpty(primaryKey)){
            throw new IllegalArgumentException("Table primaryKey can not be blank.");
        }
        this.name = tableName.trim();
        this.primaryKey = primaryKey;
        this.parentId = TableUtil.getParentId(beanClass);
        this.beanClass = beanClass;
    }

    void setPrimaryKey(String[] primaryKey) {
        this.primaryKey = primaryKey;
    }

    void setColumnTypeMap(Map<String, Class<?>> columnTypeMap) {
        if (columnTypeMap == null){
            throw new IllegalArgumentException("columnTypeMap can not be null");
        }
        this.columnTypeMap = columnTypeMap;
    }

    public String getName() {
        return name;
    }

    void setColumnType(String columnLabel, Class<?> columnType) {
        columnTypeMap.put(columnLabel, columnType);
    }

    public Class<?> getColumnType(String columnLabel) {
        return columnTypeMap.get(columnLabel);
    }

    /**
     * Model.save() need know what columns belongs to himself that he can saving to db.
     * Think about auto saving the related table's column in the future.
     */
    public boolean hasColumnLabel(String columnLabel) {
        return columnTypeMap.containsKey(columnLabel);
    }

    /**
     * update() and delete() need this method.
     */
    public String[] getPrimaryKey() {
        return primaryKey;
    }

    public String getParentId() {
        return parentId;
    }

    public Class<? extends BaseBean> getModelClass() {
        return beanClass;
    }

    public Map<String, Class<?>> getColumnTypeMap() {
        return Collections.unmodifiableMap(columnTypeMap);
    }

    public String[] getColumns(){
        return columnsStr.replaceAll(" ", "") .split(",");
    }

    public String getColumnsStr() {
        return columnsStr;
    }

    public void setColumnsStr(String columnsStr) {
        this.columnsStr = columnsStr;
    }

    public Config getConfig() {
        return config;
    }

    public void setConfig(Config config) {
        this.config = config;
    }
}