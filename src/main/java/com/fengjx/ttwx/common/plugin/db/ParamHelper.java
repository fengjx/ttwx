
package com.fengjx.ttwx.common.plugin.db;

import com.fengjx.ttwx.common.bean.ToStringBase;
import com.fengjx.ttwx.common.utils.TypeConverter;

import org.apache.commons.collections.MapUtils;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 查询参数封装
 *
 * @Created by FengJianxin on 2015/9/3.
 * @Email xd-fjx@qq.com
 */
public class ParamHelper extends ToStringBase {

    private static final long serialVersionUID = 1L;

    private Map<String, Object> params = new HashMap<>();

    public ParamHelper fromMap(Map<String, String> params) {
        getParams().putAll(params);
        return this;
    }

    public <T extends Model> ParamHelper fromMap(Class<T> modelClass, Map<String, String> params)
            throws ParseException {
        if (null == modelClass) {
            getParams().putAll(params);
        } else {
            Table table = TableUtil.getTable(modelClass);
            for (Map.Entry<String, String> e : params.entrySet()) {
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
        return this;
    }

    public ParamHelper() {
    }

    public ParamHelper(Map<String, Object> params) {
        this.params = params;
    }

    public boolean isEmpty() {
        return MapUtils.isEmpty(getParams());
    }

    /**
     * Remove attribute of this QueryParam.
     *
     * @param column the column name of the QueryParam
     */
    public ParamHelper remove(String column) {
        getParams().remove(column);
        return this;
    }

    /**
     * Remove columns of this QueryParam.
     *
     * @param columns the column names of the QueryParam
     */
    public ParamHelper remove(String... columns) {
        if (columns != null)
            for (String c : columns)
                this.getParams().remove(c);
        return this;
    }

    /**
     * Set column to QueryParam.
     *
     * @param column the column name
     * @param value the value of the column
     */
    public ParamHelper set(String column, Object value) {
        getParams().put(column, value);
        return this;
    }

    /**
     * Get column of any mysql type
     */
    @SuppressWarnings("unchecked")
    public <T> T get(String column) {
        return (T) getParams().get(column);
    }

    /**
     * Get column of any mysql type. Returns defaultValue if null.
     */
    @SuppressWarnings("unchecked")
    public <T> T get(String column, Object defaultValue) {
        Object result = getParams().get(column);
        return (T) (result != null ? result : defaultValue);
    }

    /**
     * Get column of mysql type: varchar, char, enum, set, text, tinytext,
     * mediumtext, longtext
     */
    public String getStr(String column) {
        return (String) getParams().get(column);
    }

    /**
     * Get column of mysql type: int, integer, tinyint(n) n > 1, smallint,
     * mediumint
     */
    public Integer getInt(String column) {
        return (Integer) getParams().get(column);
    }

    /**
     * Get column of mysql type: bigint
     */
    public Long getLong(String column) {
        return (Long) getParams().get(column);
    }

    /**
     * Get column of mysql type: unsigned bigint
     */
    public java.math.BigInteger getBigInteger(String column) {
        return (java.math.BigInteger) getParams().get(column);
    }

    /**
     * Get column of mysql type: date, year
     */
    public java.util.Date getDate(String column) {
        return (java.util.Date) getParams().get(column);
    }

    /**
     * Get column of mysql type: time
     */
    public java.sql.Time getTime(String column) {
        return (java.sql.Time) getParams().get(column);
    }

    /**
     * Get column of mysql type: timestamp, datetime
     */
    public java.sql.Timestamp getTimestamp(String column) {
        return (java.sql.Timestamp) getParams().get(column);
    }

    /**
     * Get column of mysql type: real, double
     */
    public Double getDouble(String column) {
        return (Double) getParams().get(column);
    }

    /**
     * Get column of mysql type: float
     */
    public Float getFloat(String column) {
        return (Float) getParams().get(column);
    }

    /**
     * Get column of mysql type: bit, tinyint(1)
     */
    public Boolean getBoolean(String column) {
        return (Boolean) getParams().get(column);
    }

    /**
     * Get column of mysql type: decimal, numeric
     */
    public java.math.BigDecimal getBigDecimal(String column) {
        return (java.math.BigDecimal) getParams().get(column);
    }

    /**
     * Get column of mysql type: binary, varbinary, tinyblob, blob, mediumblob,
     * longblob I have not finished the test.
     */
    public byte[] getBytes(String column) {
        return (byte[]) getParams().get(column);
    }

    /**
     * Get column of any type that extends from Number
     */
    public Number getNumber(String column) {
        return (Number) getParams().get(column);
    }

    /**
     * Return column names of this QueryParam.
     */
    public String[] getColumnNames() {
        Set<String> attrNameSet = getParams().keySet();
        return attrNameSet.toArray(new String[attrNameSet.size()]);
    }

    /**
     * Return column values of this QueryParam.
     */
    public Object[] getColumnValues() {
        java.util.Collection<Object> attrValueCollection = getParams().values();
        return attrValueCollection.toArray(new Object[attrValueCollection.size()]);
    }

    public Map<String, Object> getParams() {
        return params;
    }

    public ParamHelper setParams(Map<String, Object> params) {
        this.params = params;
        return this;
    }
}
