
package com.fengjx.ttwx.common.plugin.db;

import com.fengjx.ttwx.common.plugin.IPlugin;
import com.fengjx.ttwx.common.plugin.db.dialect.Dialect;
import com.fengjx.ttwx.common.plugin.db.dialect.MysqlDialect;
import com.fengjx.ttwx.common.utils.ClassUtil;
import com.fengjx.ttwx.common.utils.LogUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.sql.*;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * 表映射插件
 *
 * @author fengjx.
 * @date：2015/5/9 0009
 */
public class TableMappingPlugin implements IPlugin {

    private static final Logger LOG = LoggerFactory.getLogger(TableMappingPlugin.class);

    private DataSource dataSource;
    private String[] packages;
    private String adapterPageName;

    @Override
    public void start() {
        setConfig(adapterPageName, null);
        try {
            Set<Class<?>> classSet = getModelClasses();
            for (Class<?> cls : classSet) {
                Table table = new Table((Class<? extends Model>) cls);
                bind(table);
                TableMapping.me().putTable(table);
            }
        } catch (Exception e) {
            throw new MyDbException("Can not init table mapping");
        }
    }

    /**
     * 设置配置
     *
     * @param adapterPageName
     * @param dialect
     */
    private void setConfig(String adapterPageName, Dialect dialect) {
        if (StringUtils.isBlank(adapterPageName)) {
            adapterPageName = "JqGridPage";
        }
        if (null == dialect) {
            dialect = new MysqlDialect();
        }
        Config.init(adapterPageName, dialect);
    }

    /**
     * 扫描Model
     *
     * @return
     */
    private Set<Class<?>> getModelClasses() throws Exception {
        Set<Class<?>> classSet = new LinkedHashSet<Class<?>>();
        try {
            for (String pkg : packages) {
                classSet.addAll(ClassUtil.getClasses(pkg, true, Mapper.class));
            }
            return classSet;
        } catch (Exception e) {
            LogUtil.error(LOG, "扫描Model出错", e);
            throw e;
        }
    }

    private void bind(Table table) throws SQLException {
        Connection conn = null;
        Statement stm = null;
        ResultSet rs = null;
        String sql = Config.dialect.forTableBuilderDoBuild(table.getName());
        try {
            conn = dataSource.getConnection();
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
                }
                else if ("java.lang.Integer".equals(colClassName)) {
                    // int, integer, tinyint, smallint, mediumint
                    table.setColumnType(colName, java.lang.Integer.class);
                }
                else if ("java.lang.Long".equals(colClassName)) {
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
                }
                else if ("java.lang.Double".equals(colClassName)) {
                    // real, double
                    table.setColumnType(colName, java.lang.Double.class);
                }
                else if ("java.lang.Float".equals(colClassName)) {
                    // float
                    table.setColumnType(colName, java.lang.Float.class);
                }
                else if ("java.lang.Boolean".equals(colClassName)) {
                    // bit
                    table.setColumnType(colName, java.lang.Boolean.class);
                }
                else if ("java.sql.Time".equals(colClassName)) {
                    // time
                    table.setColumnType(colName, java.sql.Time.class);
                    // colName = "date_format(" + colName + ",'%Y-%m-%d') as " +
                    // colName;
                }
                else if ("java.sql.Timestamp".equals(colClassName)) {
                    // timestamp, datetime
                    table.setColumnType(colName, java.sql.Timestamp.class);
                    // colName = "date_format(" + colName +
                    // ",'%Y-%m-%d %H:%i:%s') as " + colName;
                }
                else if ("java.math.BigDecimal".equals(colClassName)) {
                    // decimal, numeric
                    table.setColumnType(colName, java.math.BigDecimal.class);
                }
                else if ("[B".equals(colClassName)) {
                    // binary, varbinary, tinyblob, blob, mediumblob, longblob
                    // qjd project: print_info.content varbinary(61800);
                    table.setColumnType(colName, byte[].class);
                } else {
                    int type = rsmd.getColumnType(i);
                    if (type == Types.BLOB) {
                        table.setColumnType(colName, byte[].class);
                    }
                    else if (type == Types.CLOB || type == Types.NCLOB) {
                        table.setColumnType(colName, String.class);
                    }
                    else {
                        table.setColumnType(colName, String.class);
                    }
                    // core.TypeConverter
                    // throw new
                    // RuntimeException("You've got new type to mapping. Please add code in "
                    // + TableBuilder.class.getName() +
                    // ". The ColumnClassName can't be mapped: " +
                    // colClassName);
                }
                columnsStr.append(" , ").append(colName);
            }
            table.setColumnsStr(columnsStr.delete(0, 2).toString());
        } finally {
            rs.close();
            stm.close();
            conn.close();
        }
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public String[] getPackages() {
        return packages;
    }

    public void setPackages(String[] packages) {
        this.packages = packages;
    }

    public DataSource getDataSource() {
        return dataSource;
    }

    public String getAdapterPageName() {
        return adapterPageName;
    }

    public void setAdapterPageName(String adapterPageName) {
        this.adapterPageName = adapterPageName;
    }
}
