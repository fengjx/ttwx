
package com.fengjx.ttwx.common.plugin.db.dialect;

import com.fengjx.ttwx.common.plugin.db.Table;

import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * Dialect.
 */
public abstract class Dialect {

    /**
     * 根据表明查询空记录，用于表字段映射
     *
     * @param tableName
     * @return
     */
    public abstract String forTableBuilderDoBuild(String tableName);

    public abstract void forModelSave(Table table, Map<String, ?> attrs, StringBuilder sql,
            List<Object> paras);

    public abstract String forModelDeleteById(Table table);

    public abstract void forModelUpdate(Table table, Map<String, Object> attrs, String pKey,
            Object id, StringBuilder sql, List<Object> paras);

    public abstract String forModelFindById(Table table, String columns);

    public abstract void forModelFind(Table table, StringBuilder sql,String orderby, String columns,
            Map<String, Object> attrs, List<Object> paras);

    public abstract void forPaginate(StringBuilder pageSql, int pageNumber, int pageSize, String sql);

    public void forCount(StringBuilder countSql, String sql) {
        sql = replaceFormatSqlOrderBy(sql.toLowerCase(Locale.ENGLISH));
        int index = sql.indexOf(" from ");
        countSql.append("select count(*) ");
        countSql.append(sql.substring(index));
    }

    protected static String replaceFormatSqlOrderBy(String sql) {
        sql = sql.replaceAll("(\\s)+", " ");
        int index = sql.toLowerCase().lastIndexOf("order by");
        if (index > sql.toLowerCase().lastIndexOf(")")) {
            String sql1 = sql.substring(0, index);
            String sql2 = sql.substring(index);
            sql2 = sql2
                    .replaceAll(
                            "[oO][rR][dD][eE][rR] [bB][yY] [\u4e00-\u9fa5a-zA-Z0-9_.]+((\\s)+(([dD][eE][sS][cC])|([aA][sS][cC])))?(( )*,( )*[\u4e00-\u9fa5a-zA-Z0-9_.]+(( )+(([dD][eE][sS][cC])|([aA][sS][cC])))?)*",
                            "");
            return sql1 + sql2;
        }
        return sql;
    }

    public String getDefaultPrimaryKey() {
        return "id";
    }

}
