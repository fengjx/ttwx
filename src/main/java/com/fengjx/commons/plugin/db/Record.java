
package com.fengjx.commons.plugin.db;

import com.fengjx.commons.utils.JsonUtil;
import com.fengjx.commons.utils.TypeConverter;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import org.apache.commons.collections.MapUtils;

import java.io.Serializable;
import java.text.ParseException;
import java.util.Map;
import java.util.Set;

/**
 * Record
 */
public class Record implements Serializable {

    private static final long serialVersionUID = 905784513600884082L;

    private Map<String, Object> columns;

    // 有修改的字段
    private Set<String> modifyFlag;

    private Map<String, Object> initColumns() {
        return Maps.newLinkedHashMap();
    }

    public Record() {
        this.columns = initColumns();
    }

    public Record(Map<String, Object> columns) {
        if (null == columns) {
            this.columns = initColumns();
        } else {
            this.columns = columns;
        }
    }

    /**
     * 初始化参数
     *
     * @param beanClass
     * @param columns
     * @param <T>
     * @throws ParseException
     */
    public <T extends BaseBean> Record(Class<T> beanClass, Map<String, String> columns)
            throws ParseException {
        if (null == beanClass) {
            _getColumns().putAll(columns);
        } else {
            Table table = TableUtil.getTable(beanClass);
            for (Map.Entry<String, String> e : columns.entrySet()) {
                String colName = e.getKey();
                if (table.hasColumnLabel(colName)) {
                    Object value = TypeConverter.convert(table.getColumnType(colName),
                            e.getValue());
                    set(colName, value);
                } else {
                    set(e.getKey(), e.getValue());
                }
            }
        }
    }

    public boolean isEmpty() {
        return MapUtils.isEmpty(_getColumns());
    }

    /**
     * Return columns map.
     */
    public Map<String, Object> _getColumns() {
        if (MapUtils.isEmpty(columns)) {
            columns = initColumns();
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
     * Set attributes with Map.
     * 
     * @param attrs attributes of this model
     * @return this Model
     */
    public Record setAttrs(Map<String, Object> attrs) {
        for (Map.Entry<String, Object> e : attrs.entrySet()) {
            set(e.getKey(), e.getValue());
        }
        return this;
    }

    /**
     * Remove attribute of this record.
     * 
     * @param column the column name of the record
     */
    public Record remove(String column) {
        _getColumns().remove(column);
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
                this._getColumns().remove(c);
        return this;
    }

    /**
     * Set column to record.
     * 
     * @param column the column name
     * @param value the value of the column
     */
    public Record set(String column, Object value) {
        _getColumns().put(column, value);
        _getModifyFlag().add(column);
        return this;
    }

    /**
     * Get column of any mysql type
     */
    @SuppressWarnings("unchecked")
    public <T> T get(String column) {
        return (T) _getColumns().get(column);
    }

    /**
     * Get column of any mysql type. Returns defaultValue if null.
     */
    @SuppressWarnings("unchecked")
    public <T> T get(String column, Object defaultValue) {
        Object result = _getColumns().get(column);
        return (T) (result != null ? result : defaultValue);
    }

    /**
     * Get column of mysql type: varchar, char, enum, set, text, tinytext,
     * mediumtext, longtext
     */
    public String getStr(String column) {
        return (String) _getColumns().get(column);
    }

    /**
     * Get column of mysql type: int, integer, tinyint(n) n > 1, smallint,
     * mediumint
     */
    public Integer getInt(String column) {
        return (Integer) _getColumns().get(column);
    }

    /**
     * Get column of mysql type: bigint
     */
    public Long getLong(String column) {
        return (Long) _getColumns().get(column);
    }

    /**
     * Get column of mysql type: unsigned bigint
     */
    public java.math.BigInteger getBigInteger(String column) {
        return (java.math.BigInteger) _getColumns().get(column);
    }

    /**
     * Get column of mysql type: date, year
     */
    public java.util.Date getDate(String column) {
        return (java.util.Date) _getColumns().get(column);
    }

    /**
     * Get column of mysql type: time
     */
    public java.sql.Time getTime(String column) {
        return (java.sql.Time) _getColumns().get(column);
    }

    /**
     * Get column of mysql type: timestamp, datetime
     */
    public java.sql.Timestamp getTimestamp(String column) {
        return (java.sql.Timestamp) _getColumns().get(column);
    }

    /**
     * Get column of mysql type: real, double
     */
    public Double getDouble(String column) {
        return (Double) _getColumns().get(column);
    }

    /**
     * Get column of mysql type: float
     */
    public Float getFloat(String column) {
        return (Float) _getColumns().get(column);
    }

    /**
     * Get column of mysql type: bit, tinyint(1)
     */
    public Boolean getBoolean(String column) {
        return (Boolean) _getColumns().get(column);
    }

    /**
     * Get column of mysql type: decimal, numeric
     */
    public java.math.BigDecimal getBigDecimal(String column) {
        return (java.math.BigDecimal) _getColumns().get(column);
    }

    /**
     * Get column of mysql type: binary, varbinary, tinyblob, blob, mediumblob,
     * longblob I have not finished the test.
     */
    public byte[] getBytes(String column) {
        return (byte[]) _getColumns().get(column);
    }

    /**
     * Get column of any type that extends from Number
     */
    public Number getNumber(String column) {
        return (Number) _getColumns().get(column);
    }

    public String toString() {
        return JsonUtil.toJson(_getColumns());
    }

    public boolean equals(Object o) {
        if (!(o instanceof Record))
            return false;
        if (o == this)
            return true;
        return this._getColumns().equals(((Record) o)._getColumns());
    }

    public int hashCode() {
        return _getColumns() == null ? 0 : _getColumns().hashCode();
    }

    /**
     * Return column names of this record.
     */
    public String[] _getColumnNames() {
        Set<String> attrNameSet = _getColumns().keySet();
        return attrNameSet.toArray(new String[attrNameSet.size()]);
    }

    /**
     * Return column values of this record.
     */
    public Object[] _getColumnValues() {
        java.util.Collection<Object> attrValueCollection = _getColumns().values();
        return attrValueCollection.toArray(new Object[attrValueCollection.size()]);
    }

    /**
     * Return json string of this record.
     */
    public String toJson() {
        if (MapUtils.isEmpty(_getColumns())) {
            return "{}";
        }
        return JsonUtil.toJson(_getColumns());
    }

    public Set<String> _getModifyFlag() {
        if (this.modifyFlag == null) {
            modifyFlag = Sets.newHashSet();
        }
        return modifyFlag;
    }

    public void setModifyFlag(Set<String> modifyFlag) {
        this.modifyFlag = modifyFlag;
    }

    public void setModify(String modifyColumn) {
        _getModifyFlag().add(modifyColumn);
    }

}
