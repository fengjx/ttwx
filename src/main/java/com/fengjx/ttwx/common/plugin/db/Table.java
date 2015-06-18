
package com.fengjx.ttwx.common.plugin.db;

import org.apache.commons.lang3.StringUtils;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Table save the table meta info like column name and column type.
 */
public class Table {

    // 表名
    private String name;
    // 组件名称
    private String primaryKey;
    // 字段及类型
    private Map<String, Class<?>> columnTypeMap = new HashMap();
    // 查询字段
    private String columnsStr;

    private Class<? extends Model> modelClass;

    public Table(Class<? extends Model> modelClass) {
        if (modelClass == null){
            throw new IllegalArgumentException("Model class can not be null.");
        }
        String tableName = TableUtil.getTableName(modelClass);
        if (StringUtils.isBlank(tableName)){
            throw new IllegalArgumentException("Table name can not be blank.");
        }
        String primaryKey = TableUtil.getPrimaryKey(modelClass);
        if (StringUtils.isBlank(primaryKey)){
            throw new IllegalArgumentException("Table primaryKey can not be blank.");
        }
        this.name = tableName.trim();
        this.primaryKey = primaryKey.trim();
        this.modelClass = modelClass;
    }

    void setPrimaryKey(String primaryKey) {
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
    public String getPrimaryKey() {
        return primaryKey;
    }

    public Class<? extends Model> getModelClass() {
        return modelClass;
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
}