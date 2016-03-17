
package com.fengjx.commons.plugin.db;

import com.fengjx.commons.plugin.db.dialect.Dialect;
import com.google.common.collect.Maps;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Map;

/**
 * 数据库配置
 *
 * @version 2016/1/23
 * @Created by FengJianxin
 * @Email xd-fjx@qq.com
 */
public class Config {

    // 数据库方言
    private Dialect dialect;

    // 主键生成器
    private IdGenerator idGenerator = new IdDefaultGen();

    // 是否自动生成ID
    private boolean autoId = true;

    private Map<Class<? extends BaseBean>, Table> beanToTableMap = Maps.newHashMap();

    private JdbcTemplate jdbcTemplate;

    public Config(Dialect dialect, JdbcTemplate jdbcTemplate) {
        this.dialect = dialect;
        this.jdbcTemplate = jdbcTemplate;
    }

    public Config(Dialect dialect, IdGenerator idGenerator, boolean autoId,
            JdbcTemplate jdbcTemplate) {
        this.dialect = dialect;
        this.idGenerator = idGenerator;
        this.autoId = autoId;
        this.jdbcTemplate = jdbcTemplate;
    }

    public Dialect getDialect() {
        return dialect;
    }

    public void setDialect(Dialect dialect) {
        this.dialect = dialect;
    }

    public IdGenerator getIdGenerator() {
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

    public void putTable(Table table) {
        beanToTableMap.put(table.getModelClass(), table);
    }

    public Table getTable(Class<? extends BaseBean> beanClass) {
        Table table = beanToTableMap.get(beanClass);
        if (table == null) {
            throw new RuntimeException("The Table mapping of model: " + beanClass.getName()
                    + " not exists. Please add mapping package to TableMappingPlugin for spring config ");

        }
        return table;
    }

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}
