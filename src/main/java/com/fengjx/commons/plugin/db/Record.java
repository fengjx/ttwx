
package com.fengjx.commons.plugin.db;

import com.fengjx.commons.utils.JsonUtil;

import com.fengjx.commons.utils.TypeConverter;
import com.google.common.collect.Maps;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.MapUtils;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * Record
 */
public class Record implements Serializable {

    private static final long serialVersionUID = 905784513600884082L;

    private Map<String, Object> columns;

    public Record() {
        this.columns = Maps.newHashMap();
    }

    public Record(Map<String, Object> columns) {
        if(null == columns ){
            this.columns = Maps.newHashMap();
        }else{
            this.columns = columns;
        }
    }

    /**
     * 初始化参数
     *
     * @param modelClass
     * @param columns
     * @param <T>
     * @throws ParseException
     */
    public <T extends Model> Record(Class<T> modelClass, Map<String, String> columns) throws ParseException {
        if (null == modelClass) {
            getColumns().putAll(columns);
        } else {
            Table table = TableUtil.getTable(modelClass);
            for (Map.Entry<String, String> e : columns.entrySet()) {
                String colName = e.getKey();
                if (table.hasColumnLabel(colName)) {
                    Object value = TypeConverter
                            .convert(table.getColumnType(colName), e.getValue());
                    set(colName, value);
                } else {
                    set(e.getKey(), e.getValue());
                }
            }
        }
    }

    public boolean isEmpty(){
        return MapUtils.isEmpty(getColumns());
    }

    /**
     * Return columns map.
     */
    public Map<String, Object> getColumns() {
        if(MapUtils.isEmpty(columns)){
            columns = Maps.newHashMap();
        }
        return columns;
    }

    /**
     * Set columns value with map.
     * 
     * @param columns the columns map
     */
    public Record setColumns(Map<String, Object> columns) {
        this.columns = columns;
        return this;
    }

    /**
     * Remove attribute of this record.
     * 
     * @param column the column name of the record
     */
    public Record remove(String column) {
        getColumns().remove(column);
        return this;
    }

    /**
     * Remove columns of this record.
     * 
     * @param columns the column names of the record
     */
    public Record remove(String... columns) {
        if (columns != null)
            for (String c : columns)
                this.getColumns().remove(c);
        return this;
    }

    /**
     * Set column to record.
     * 
     * @param column the column name
     * @param value the value of the column
     */
    public Record set(String column, Object value) {
        getColumns().put(column, value);
        return this;
    }

    /**
     * Get column of any mysql type
     */
    @SuppressWarnings("unchecked")
    public <T> T get(String column) {
        return (T) getColumns().get(column);
    }

    /**
     * Get column of any mysql type. Returns defaultValue if null.
     */
    @SuppressWarnings("unchecked")
    public <T> T get(String column, Object defaultValue) {
        Object result = getColumns().get(column);
        return (T) (result != null ? result : defaultValue);
    }

    /**
     * Get column of mysql type: varchar, char, enum, set, text, tinytext,
     * mediumtext, longtext
     */
    public String getStr(String column) {
        return (String) getColumns().get(column);
    }

    /**
     * Get column of mysql type: int, integer, tinyint(n) n > 1, smallint,
     * mediumint
     */
    public Integer getInt(String column) {
        return (Integer) getColumns().get(column);
    }

    /**
     * Get column of mysql type: bigint
     */
    public Long getLong(String column) {
        return (Long) getColumns().get(column);
    }

    /**
     * Get column of mysql type: unsigned bigint
     */
    public java.math.BigInteger getBigInteger(String column) {
        return (java.math.BigInteger) getColumns().get(column);
    }

    /**
     * Get column of mysql type: date, year
     */
    public java.util.Date getDate(String column) {
        return (java.util.Date) getColumns().get(column);
    }

    /**
     * Get column of mysql type: time
     */
    public java.sql.Time getTime(String column) {
        return (java.sql.Time) getColumns().get(column);
    }

    /**
     * Get column of mysql type: timestamp, datetime
     */
    public java.sql.Timestamp getTimestamp(String column) {
        return (java.sql.Timestamp) getColumns().get(column);
    }

    /**
     * Get column of mysql type: real, double
     */
    public Double getDouble(String column) {
        return (Double) getColumns().get(column);
    }

    /**
     * Get column of mysql type: float
     */
    public Float getFloat(String column) {
        return (Float) getColumns().get(column);
    }

    /**
     * Get column of mysql type: bit, tinyint(1)
     */
    public Boolean getBoolean(String column) {
        return (Boolean) getColumns().get(column);
    }

    /**
     * Get column of mysql type: decimal, numeric
     */
    public java.math.BigDecimal getBigDecimal(String column) {
        return (java.math.BigDecimal) getColumns().get(column);
    }

    /**
     * Get column of mysql type: binary, varbinary, tinyblob, blob, mediumblob,
     * longblob I have not finished the test.
     */
    public byte[] getBytes(String column) {
        return (byte[]) getColumns().get(column);
    }

    /**
     * Get column of any type that extends from Number
     */
    public Number getNumber(String column) {
        return (Number) getColumns().get(column);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString()).append(" {");
        boolean first = true;
        for (Entry<String, Object> e : getColumns().entrySet()) {
            if (first)
                first = false;
            else
                sb.append(", ");

            Object value = e.getValue();
            if (value != null)
                value = value.toString();
            sb.append(e.getKey()).append(":").append(value);
        }
        sb.append("}");
        return sb.toString();
    }

    public boolean equals(Object o) {
        if (!(o instanceof Record))
            return false;
        if (o == this)
            return true;
        return this.getColumns().equals(((Record) o).getColumns());
    }

    public int hashCode() {
        return getColumns() == null ? 0 : getColumns().hashCode();
    }

    /**
     * Return column names of this record.
     */
    public String[] getColumnNames() {
        Set<String> attrNameSet = getColumns().keySet();
        return attrNameSet.toArray(new String[attrNameSet.size()]);
    }

    /**
     * Return column values of this record.
     */
    public Object[] getColumnValues() {
        java.util.Collection<Object> attrValueCollection = getColumns().values();
        return attrValueCollection.toArray(new Object[attrValueCollection.size()]);
    }

    /**
     * Return json string of this record.
     */
    public String toJson() {
        if(MapUtils.isEmpty(getColumns())){
            return "{}";
        }
        return JsonUtil.toJson(getColumns());
    }

    /**
     * 转成实体
     *
     * @param bean
     * @param <T>
     * @return
     */
    public <T> T toBean(Class<T> bean) {
        T t;
        try {
            t = bean.newInstance();
            BeanUtils.populate(t, getColumns());
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
        return t;
    }

}
