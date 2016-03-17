
package com.fengjx.commons.plugin.db;

import com.fengjx.commons.plugin.IPlugin;
import com.fengjx.commons.plugin.db.annotation.Mapper;
import com.fengjx.commons.plugin.db.dialect.Dialect;
import com.fengjx.commons.plugin.db.dialect.MysqlDialect;
import com.fengjx.commons.plugin.db.dialect.OracleDialect;
import com.fengjx.commons.utils.ClassUtil;
import com.fengjx.commons.utils.LogUtil;
import com.fengjx.commons.utils.StrUtil;
import org.apache.commons.lang3.ClassUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.*;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * 表映射插件
 *
 * @author fengjx. @date：2015/5/9 0009
 */
public class TableMappingPlugin implements IPlugin {

    private static final Logger LOG = LoggerFactory.getLogger(TableMappingPlugin.class);

    private JdbcTemplate jdbcTemplate;

    private String[] packages;

    // 数据库方言
    private String dialect;

    // 主键生成器
    private IdGenerator idGenerator = new IdDefaultGen();

    // 是否自动生成ID
    private boolean autoId = true;

    @Override
    public void start() {
        Config config = new Config(getDialect(), getIdGenerator(), isAutoId(), getJdbcTemplate());
        try {
            Set<Class<? extends BaseBean>> classSet = getModelClasses();
            for (Class<? extends BaseBean> cls : classSet) {
                Table table = new Table(cls);
                bind(table, config);
                TableMapping.me().putTable(table);
            }
        } catch (Exception e) {
            LogUtil.error(LOG, "Can not init table mapping", e);
            throw new MyDbException("Can not init table mapping");
        }
    }

    /**
     * 扫描表
     *
     * @return
     */
    @SuppressWarnings("unchecked")
    private Set<Class<? extends BaseBean>> getModelClasses() throws Exception {
        Set<Class<? extends BaseBean>> classSet = new LinkedHashSet<>();
        try {
            for (String pkg : packages) {
                Set<Class<?>> getCls = ClassUtil.getClasses(pkg, true, Mapper.class);
                for (Class<?> cls : getCls) {
                    // 判断是否可以转型（即是够是BaseBean的子类）
                    if (ClassUtils.isAssignable(cls.getSuperclass(), BaseBean.class)) {
                        classSet.add((Class<? extends BaseBean>) cls);
                    }
                }
            }
            return classSet;
        } catch (Exception e) {
            LogUtil.error(LOG, "扫描表出错", e);
            throw e;
        }
    }

    private void bind(Table table, Config config) throws SQLException {
        Connection conn = null;
        Statement stm = null;
        ResultSet rs = null;
        String sql = config.getDialect().forTableBuilderDoBuild(table.getName());
        try {
            conn = config.getJdbcTemplate().getDataSource().getConnection();
            stm = conn.createStatement();
            rs = stm.executeQuery(sql);
            LogUtil.debug(LOG, "executeQuery sql ==>" + sql);
            ResultSetMetaData rsmd = rs.getMetaData();
            StringBuilder columnsStr = new StringBuilder();
            for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                String colName = rsmd.getColumnName(i);
                String colClassName = rsmd.getColumnClassName(i);
                if ("java.lang.String".equals(colClassName)) {
                    // varchar, char, enum, set, text, tinytext, mediumtext,
                    // longtext
                    table.setColumnType(colName, java.lang.String.class);
                } else if ("java.lang.Integer".equals(colClassName)) {
                    // int, integer, tinyint, smallint, mediumint
                    table.setColumnType(colName, java.lang.Integer.class);
                } else if ("java.lang.Long".equals(colClassName)) {
                    // bigint
                    table.setColumnType(colName, java.lang.Long.class);
                }
                // else if ("java.util.Date".equals(colClassName)) { //
                // java.util.Data can not be returned
                // java.sql.Date, java.sql.Time, java.sql.Timestamp all extends
                // java.util.Data so getDate can return the three types data
                // result.addInfo(colName, java.util.Date.class);
                // }
                else if ("java.sql.Date".equals(colClassName)) {
                    // date, year
                    table.setColumnType(colName, java.sql.Date.class);
                    // colName = "date_format(" + colName + ",'%Y-%m-%d') as " +
                    // colName;
                } else if ("java.lang.Double".equals(colClassName)) {
                    // real, double
                    table.setColumnType(colName, java.lang.Double.class);
                } else if ("java.lang.Float".equals(colClassName)) {
                    // float
                    table.setColumnType(colName, java.lang.Float.class);
                } else if ("java.lang.Boolean".equals(colClassName)) {
                    // bit
                    table.setColumnType(colName, java.lang.Boolean.class);
                } else if ("java.sql.Time".equals(colClassName)) {
                    // time
                    table.setColumnType(colName, java.sql.Time.class);
                    // colName = "date_format(" + colName + ",'%Y-%m-%d') as " +
                    // colName;
                } else if ("java.sql.Timestamp".equals(colClassName)) {
                    // timestamp, datetime
                    table.setColumnType(colName, java.sql.Timestamp.class);
                    // colName = "date_format(" + colName +
                    // ",'%Y-%m-%d %H:%i:%s') as " + colName;
                } else if ("java.math.BigDecimal".equals(colClassName)) {
                    // decimal, numeric
                    table.setColumnType(colName, java.math.BigDecimal.class);
                } else if ("[B".equals(colClassName)) {
                    // binary, varbinary, tinyblob, blob, mediumblob, longblob
                    // qjd project: print_info.content varbinary(61800);
                    table.setColumnType(colName, byte[].class);
                } else {
                    int type = rsmd.getColumnType(i);
                    if (type == Types.BLOB) {
                        table.setColumnType(colName, byte[].class);
                    } else if (type == Types.CLOB || type == Types.NCLOB) {
                        table.setColumnType(colName, String.class);
                    } else {
                        table.setColumnType(colName, String.class);
                    }
                    // core.TypeConverter
                    // throw new
                    // RuntimeException("You've got new type to mapping. Please
                    // add code in "
                    // + TableBuilder.class.getName() +
                    // ". The ColumnClassName can't be mapped: " +
                    // colClassName);
                }
                columnsStr.append(" , ").append(colName);
            }
            table.setColumnsStr(columnsStr.delete(0, 2).toString());
            table.setConfig(config);
        } finally {
            if (null != rs) {
                rs.close();
            }
            if (null != stm) {
                stm.close();
            }
            if (null != conn) {
                conn.close();
            }
        }
    }

    public JdbcTemplate getJdbcTemplate() {
        if (null == jdbcTemplate) {
            throw new RuntimeException("jdbcTemplate in TableMappingPlugin must be not null");
        }
        return jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public String[] getPackages() {
        return packages;
    }

    public void setPackages(String[] packages) {
        this.packages = packages;
    }

    public Dialect getDialect() {
        if (StrUtil.isBlank(dialect)) {
            return new MysqlDialect();
        }
        if ("mysql".equals(dialect)) {
            return new MysqlDialect();
        } else if ("oracle".equals(dialect)) {
            return new OracleDialect();
        }
        throw new RuntimeException("unkonw dialect type: " + dialect);
    }

    public void setDialect(String dialect) {
        this.dialect = dialect;
    }

    public IdGenerator getIdGenerator() {
        if (null == idGenerator) {
            idGenerator = new IdDefaultGen();
        }
        return idGenerator;
    }

    public void setIdGenerator(IdGenerator idGenerator) {
        this.idGenerator = idGenerator;
    }

    public boolean isAutoId() {
        return autoId;
    }

    public void setAutoId(boolean autoId) {
        this.autoId = autoId;
    }
}
