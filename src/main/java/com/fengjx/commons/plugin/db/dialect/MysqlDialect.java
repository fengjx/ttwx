
package com.fengjx.commons.plugin.db.dialect;

import com.fengjx.commons.plugin.db.Table;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * MysqlDialect.
 */
public class MysqlDialect extends Dialect {

    public String forTableBuilderDoBuild(String tableName) {
        return "select * from `" + tableName + "` where 1 = 2";
    }

    @Override
    public void forModelSave(Table table, Map<String, ?> attrs, StringBuilder sql,
            List<Object> paras) {
        sql.append("insert into `").append(table.getName()).append("`(");
        StringBuilder temp = new StringBuilder(") values(");
        for (Entry<String, ?> e : attrs.entrySet()) {
            String colName = e.getKey();
            if (table.hasColumnLabel(colName)) {
                if (paras.size() > 0) {
                    sql.append(", ");
                    temp.append(", ");
                }
                sql.append("`").append(colName).append("`");
                temp.append("?");
                paras.add(e.getValue());
            }
        }
        sql.append(temp.toString()).append(")");
    }

    @Override
    public String forModelDeleteById(Table table) {
        String primaryKey = table.getPrimaryKey();
        StringBuilder sql = new StringBuilder(45);
        sql.append("delete from `");
        sql.append(table.getName());
        sql.append("` where `").append(primaryKey).append("` = ?");
        return sql.toString();
    }

    @Override
    public void forModelUpdate(Table table, Map<String, Object> attrs, String primaryKey,
            Object id, StringBuilder sql, List<Object> paras) {
        sql.append("update `").append(table.getName()).append("` set ");
        for (Entry<String, Object> e : attrs.entrySet()) {
            String colName = e.getKey();
            if (!primaryKey.equalsIgnoreCase(colName) && table.hasColumnLabel(colName)) {
                if (paras.size() > 0)
                    sql.append(", ");
                sql.append("`").append(colName).append("` = ? ");
                paras.add(e.getValue());
            }
        }
        sql.append(" where `").append(primaryKey).append("` = ?"); // .append(" limit 1");
        paras.add(id);
    }

    @Override
    public String forModelFindById(Table table, String columns) {
        StringBuilder sql = new StringBuilder(froSelectSql(table, columns));
        sql.append(" where `").append(table.getPrimaryKey()).append("` = ?");
        return sql.toString();
    }

    @Override
    public void forModelFind(Table table, StringBuilder sql, String columns, String orderby,
            Map<String, Object> attrs, List<Object> paras) {
        sql.append(froSelectSql(table, columns));
        sql.append(" where 1 = 1 ");
        if (!MapUtils.isEmpty(attrs)) {
            for (Entry<String, Object> e : attrs.entrySet()) {
                String colName = e.getKey();
                if (table.hasColumnLabel(colName)) {
                    sql.append("and ").append(colName).append(" = ? ");
                    paras.add(e.getValue());
                }
            }
        }
        if (StringUtils.isNotBlank(orderby)) {
            sql.append(" ").append(orderby);
        }
    }

    @Override
    public void forPaginate(StringBuilder pageSql, int pageNumber, int pageSize, String sql) {
        int offset = pageSize * (pageNumber - 1);
        pageSql.append(sql).append(" ");
        pageSql.append(" limit ").append(offset).append(", ").append(pageSize);
    }

    private String froSelectSql(Table table, String columns) {
        StringBuilder sql = new StringBuilder("select ");
        if (StringUtils.isBlank(columns) || columns.trim().equals("*")) {
            sql.append(table.getColumnsStr());
        } else {
            String[] columnsArray = columns.split(",");
            for (int i = 0; i < columnsArray.length; i++) {
                if (i > 0) {
                    sql.append(", ");
                }
                sql.append(" ").append(columnsArray[i].trim());
            }
        }
        sql.append(" from ");
        sql.append(table.getName());
        return sql.toString();
    }

}
