
package com.fengjx.commons.plugin.db;

import com.fengjx.commons.web.page.PageContext;
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author fengjx. @date：2015/5/8 0008
 */
public abstract class Model<B extends BaseBean> {

    private Class<B> beanClazz;

    public boolean save(B bean) {
        return insert(bean._getColumns());
    }

    public boolean insert(Map<String, Object> attrs) {
        return insert(getUsefulClass(), attrs);
    }

    @Deprecated
    public boolean insert(Class<? extends BaseBean> cls, Map<String, Object> attrs) {
        Table table = getTable(cls);
        Config config = table.getConfig();
        if (config.isAutoId()) {
            String[] pk = table.getPrimaryKey();
            for (String id : pk) {
                if (StringUtils.isBlank((String) attrs.get(id))) {
                    attrs.put(id, config.getIdGenerator().createId());
                }
            }
        }
        StringBuilder sql = new StringBuilder();
        List<Object> params = new ArrayList<>();
        config.getDialect().forModelSave(table, attrs, sql, params);
        return config.getJdbcTemplate().update(sql.toString(), params.toArray()) >= 1;
    }

    @Deprecated
    public boolean insert(Class<? extends BaseBean> cls, Record record) {
        return insert(cls, record._getColumns());
    }

    @Deprecated
    public boolean insert(Record record) {
        return insert(getUsefulClass(), record);
    }

    /**
     * Delete model by id.
     *
     * @param id the id value of the model
     * @return true if delete succeed otherwise false
     */
    public boolean deleteById(Object id) {
        if (id == null) {
            throw new IllegalArgumentException("id can not be null");
        }
        return deleteById(getUsefulClass(), id);
    }

    /**
     * 用于复合主键
     *
     * @param id
     * @return
     */
    public boolean deleteById(Object... id) {
        if (id == null || id.length < 1) {
            throw new IllegalArgumentException("id can not be null");
        }
        return deleteById(getUsefulClass(), id);
    }

    @Deprecated
    public boolean deleteById(Class<? extends BaseBean> cls, Object id) {
        Table table = getTable(cls);
        Config config = table.getConfig();
        String sql = config.getDialect().forModelDeleteById(table);
        return config.getJdbcTemplate().update(sql, id) >= 1;
    }

    @Deprecated
    public boolean deleteById(Class<? extends BaseBean> cls, Object... id) {
        Table table = getTable(cls);
        Config config = table.getConfig();
        String sql = config.getDialect().forModelDeleteById(table);
        return config.getJdbcTemplate().update(sql, id) >= 1;
    }

    /**
     * Update model.
     */
    public boolean update(Map<String, Object> attrs) {
        return update(getUsefulClass(), attrs);
    }

    /**
     * Update model.
     */
    @Deprecated
    public boolean update(Class<? extends BaseBean> cls, Map<String, Object> attrs) {
        Table table = getTable(cls);
        Config config = table.getConfig();
        String[] pKeys = table.getPrimaryKey();
        for (String pKey : pKeys) {
            Object id = attrs.get(pKey);
            if (id == null) {
                throw new MyDbException("You can't update model without Primary Key, " + pKey
                        + " can not be null.");
            }
        }
        StringBuilder sql = new StringBuilder();
        List<Object> paras = new ArrayList<>();
        config.getDialect().forModelUpdate(table, attrs, sql, paras);
        if (paras.size() <= 1) { // Needn't update
            return false;
        }
        int result = config.getJdbcTemplate().update(sql.toString(), paras.toArray());
        return result >= 1;
    }

    /**
     * Update model.
     */
    public boolean update(B bean) {
        if (bean._getModifyFlag().isEmpty()) {
            return false;
        }
        Table table = getTable();
        Config config = table.getConfig();
        String[] pKeys = table.getPrimaryKey();
        Map<String, Object> attrs = bean._getColumns();
        for (String pKey : pKeys) {
            Object id = attrs.get(pKey);
            if (id == null) {
                throw new MyDbException("You can't update model without Primary Key, " + pKey
                        + " can not be null.");
            }
        }
        StringBuilder sql = new StringBuilder();
        List<Object> paras = Lists.newArrayList();
        config.getDialect().forModelUpdate(table, attrs, bean._getModifyFlag(), sql, paras);
        // Needn't update
        if (paras.size() <= 1) {
            return false;
        }
        int result = config.getJdbcTemplate().update(sql.toString(), paras.toArray());
        if (result >= 1) {
            bean._getModifyFlag().clear();
            return true;
        }
        return false;
    }

    /**
     * Find model by id.
     * <p/>
     * 
     * <pre>
     * Example:
     * User user = User.dao.findById(123);
     * </pre>
     *
     * @param idValue the id value of the model
     */
    public B findById(Object idValue) {
        return findByIdLoadColumns(idValue, "*");
    }

    /**
     * Find model by composite id values.
     * <p/>
     * 
     * <pre>
     * Example:
     * User user = User.dao.findById(123, 456);
     * </pre>
     *
     * @param idValues the composite id values of the model
     */
    public B findById(Object... idValues) {
        return findByIdLoadColumns(idValues, "*");
    }

    /**
     * Find model by id and load specific columns only.
     * <p/>
     * 
     * <pre>
     * Example:
     * User user = User.dao.findByIdLoadColumns(123, "name, age");
     * </pre>
     *
     * @param idValue the id value of the model
     * @param columns the specific columns to load
     */
    public B findByIdLoadColumns(Object idValue, String columns) {
        return findByIdLoadColumns(new Object[] {
                idValue
        }, columns);
    }

    /**
     * Find model by composite id values and load specific columns only.
     * <p/>
     * 
     * <pre>
     * Example:
     * User user = User.dao.findByIdLoadColumns(new Object[]{123, 456}, "name, age");
     * </pre>
     *
     * @param idValues the composite id values of the model
     * @param columns the specific columns to load
     */
    public B findByIdLoadColumns(Object[] idValues, String columns) {
        Table table = getTable();
        Config config = table.getConfig();
        if (table.getPrimaryKey().length != idValues.length) {
            throw new IllegalArgumentException(
                    "id values error, need " + table.getPrimaryKey().length + " id value");
        }
        String sql = config.getDialect().forModelFindById(table, columns);
        return findFirst(sql, idValues);
    }

    /**
     * 查询单条记录
     *
     * @param attrs 查询条件及参数
     * @return
     */
    @Deprecated
    public Record findOne(Map<String, Object> attrs) {
        return findOne(getUsefulClass(), attrs);
    }

    /**
     * 查询单条记录
     *
     * @param attrs
     * @return
     */
    public B findFirst(Map<String, Object> attrs) {
        Table table = getTable(getUsefulClass());
        Config config = table.getConfig();
        StringBuilder sql = new StringBuilder();
        List<Object> paras = Lists.newArrayList();
        config.getDialect().forModelFind(table, sql, "*", null, attrs, paras);
        return findFirst(sql.toString(), paras.toArray());
    }

    /**
     * 根据Model查询单条记录
     *
     * @param cls 映射的class
     * @param attrs 查询条件及参数
     * @return
     */
    @Deprecated
    public Record findOne(Class<? extends BaseBean> cls, Map<String, Object> attrs) {
        Table table = getTable(cls);
        Config config = table.getConfig();
        StringBuilder sql = new StringBuilder();
        List<Object> paras = new ArrayList<>();
        config.getDialect().forModelFind(table, sql, "*", null, attrs, paras);
        return findOne(sql.toString(), paras.toArray());
    }

    /**
     * 根据sql查询单条记录
     *
     * @param sql 查询sql
     * @param params 查询参数
     * @return
     */
    public Record findOne(String sql, Object... params) {
        List<Record> list = find(Record.class, sql, params);
        if (CollectionUtils.isEmpty(list)) {
            return new Record();
        } else if (list.size() > 1) {
            throw new MyDbException("Incorrect result size: expected 1, actual " + list.size());
        }
        return list.get(0);
    }

    /**
     * 根据sql查询单条记录
     *
     * @param sql
     * @param params
     * @return
     */
    public B findFirst(String sql, Object... params) {
        return findFirst(getUsefulClass(), sql, params);
    }

    /**
     * 根据sql查询单条记录
     *
     * @param cls 返回值类型
     * @param sql 查询sql
     * @param params 查询参数
     * @return
     */
    public <T extends Record> T findFirst(Class<T> cls, String sql, Object... params) {
        List<T> list = find(cls, sql, params);
        if (CollectionUtils.isEmpty(list)) {
            return null;
        } else if (list.size() > 1) {
            throw new MyDbException("Incorrect result size: expected 1, actual " + list.size());
        }
        return list.get(0);
    }

    /**
     * 查询多条记录
     *
     * @param attrs 查询条件及参数
     * @return
     */
    @Deprecated
    public List<Map<String, Object>> findList(Map<String, Object> attrs) {
        return findList(attrs, null);
    }

    /**
     * 查询多条记录
     *
     * @param attrs 查询条件及参数
     * @return
     */
    public List<B> find(Map<String, Object> attrs) {
        return find(attrs, null);
    }

    /**
     * 查询多条记录
     *
     * @param attrs
     * @param orderby 排序艾段
     * @return
     */
    @Deprecated
    public List<Map<String, Object>> findList(Map<String, Object> attrs, String orderby) {
        return findList(getUsefulClass(), attrs, orderby);
    }

    public List<B> find(Map<String, Object> attrs, String orderby) {
        return find(getUsefulClass(), attrs, orderby);
    }

    /**
     * 根据Model查询多条记录
     *
     * @param cls 映射的class
     * @param attrs 查询条件及参数
     * @return
     */
    @Deprecated
    public List<Map<String, Object>> findList(Class<? extends BaseBean> cls,
            Map<String, Object> attrs) {
        return findList(cls, attrs, null);
    }

    /**
     * 根据Model查询多条记录
     *
     * @param cls
     * @param attrs
     * @param orderby 排序 "order by in_time"
     * @return
     */
    @Deprecated
    public List<Map<String, Object>> findList(Class<? extends BaseBean> cls,
            Map<String, Object> attrs, String orderby) {
        Table table = getTable(cls);
        Config config = table.getConfig();
        StringBuilder sql = new StringBuilder();
        List<Object> paras = new ArrayList<>();
        config.getDialect().forModelFind(table, sql, "*", orderby, attrs, paras);
        return findList(sql.toString(), paras.toArray());
    }

    public <T extends BaseBean> List<T> find(Class<T> cls, Map<String, Object> attrs,
            String orderby) {
        Table table = getTable(cls);
        Config config = table.getConfig();
        StringBuilder sql = new StringBuilder();
        List<Object> paras = Lists.newArrayList();
        config.getDialect().forModelFind(table, sql, "*", orderby, attrs, paras);
        return find(cls, sql.toString(), paras.toArray());
    }

    /**
     * 根据sql查询多条记录
     *
     * @param sql
     * @param params
     * @return
     */
    public List<Map<String, Object>> findList(String sql, Object... params) {
        return getJdbcTemplate().queryForList(sql, params);
    }

    /**
     * 根据sql查询多条记录
     *
     * @param sql
     * @param params
     * @return
     */
    public <T extends Record> List<T> find(final Class<T> cls, String sql, Object... params) {
        return getJdbcTemplate().query(sql, new RowMapper<T>() {
            @Override
            public T mapRow(ResultSet rs, int rowNum) throws SQLException {
                T bean;
                try {
                    bean = RecordBuilder.build(rs, cls);
                } catch (InstantiationException | IllegalAccessException e) {
                    throw new MyDbException(e);
                }
                return bean;
            }
        }, params);
    }

    /**
     * 根据sql查询多条记录
     *
     * @param sql
     * @param params
     * @return
     */
    public List<B> find(String sql, Object... params) {
        return find(getUsefulClass(), sql, params);
    }

    /**
     * 单表查询，查询当前Model关联的表，此查询依赖PageContext
     *
     * @param attrs
     * @return
     */
    @Deprecated
    public Page<Map<String, Object>> paginate(Map<String, Object> attrs) {
        return paginate(attrs, null);
    }

    public Page<B> page(Map<String, Object> attrs) {
        return page(attrs, null);
    }

    @Deprecated
    public Page<Map<String, Object>> paginate(Map<String, Object> attrs, String orderby) {
        return paginate(getUsefulClass(), attrs, orderby);
    }

    /**
     * @param attrs
     * @param orderby
     * @return
     */
    public Page<B> page(Map<String, Object> attrs, String orderby) {
        return page(getUsefulClass(), attrs, orderby);
    }

    /**
     * 单表查询，此查询依赖PageContext
     *
     * @param cls
     * @param attrs
     * @return
     */
    @Deprecated
    public Page<Map<String, Object>> paginate(Class<? extends BaseBean> cls,
            Map<String, Object> attrs) {
        return paginate(cls, attrs, null);
    }

    public Page<B> page(Class<? extends BaseBean> cls, Map<String, Object> attrs) {
        return page(cls, attrs, null);
    }

    public Page<B> page(B bean) {
        return page(bean, null);
    }

    public Page<B> page(B bean, String orderBy) {
        return page(bean.getClass(), bean._getColumns(), orderBy);
    }

    /**
     * paginate(User.class,attrs,"order by in_time")
     *
     * @param cls
     * @param attrs
     * @param orderby
     * @return
     */
    @Deprecated
    public Page<Map<String, Object>> paginate(Class<? extends BaseBean> cls,
            Map<String, Object> attrs, String orderby) {
        Table table = getTable(cls);
        Config config = table.getConfig();
        StringBuilder sql = new StringBuilder();
        List<Object> paras = Lists.newArrayList();
        config.getDialect().forModelFind(table, sql, "*", orderby, attrs, paras);
        return paginate(PageContext.getPageNumber(), PageContext.getPageSize(), sql.toString(),
                paras.toArray());
    }

    public Page<B> page(Class<? extends BaseBean> cls, Map<String, Object> attrs, String orderby) {
        Table table = getTable(cls);
        Config config = table.getConfig();
        StringBuilder sql = new StringBuilder();
        List<Object> paras = new ArrayList<>();
        config.getDialect().forModelFind(table, sql, "*", orderby, attrs, paras);
        return page(sql.toString(), paras.toArray());
    }

    /**
     * 分页查询，此查询依赖PageContext
     *
     * @param sql
     * @param paras
     * @return
     */
    public Page<Map<String, Object>> paginate(String sql, Object... paras) {
        return paginate(PageContext.getPageNumber(), PageContext.getPageSize(), sql, paras);
    }

    /**
     * 分页查询，此查询依赖PageContext
     *
     * @param sql
     * @param paras
     * @return
     */
    public Page<B> page(String sql, Object... paras) {
        return page(PageContext.getPageNumber(), PageContext.getPageSize(), sql, paras);
    }

    /**
     * 分页查询，此查询依赖PageContext
     *
     * @param cls 返回值类型
     * @param sql 查询sql
     * @param paras 查询参数
     * @param <T>
     * @return
     */
    public <T extends Record> Page<T> page(Class<T> cls, String sql, Object... paras) {
        return page(cls, PageContext.getPageNumber(), PageContext.getPageSize(), sql, paras);
    }

    /**
     * 分页查询
     *
     * @param pageNumber
     * @param pageSize
     * @param sql
     * @param paras
     * @return
     */
    public Page<Map<String, Object>> paginate(int pageNumber, int pageSize, String sql,
            Object... paras) {
        Config config = getConfig();
        if (pageNumber < 1 || pageSize < 1) {
            throw new MyDbException("pageNumber and pageSize must be more than 0");
        }
        int totalRow;
        int totalPage;
        totalRow = getCount(sql, paras);
        if (totalRow < 1) {
            return new Page<>(Lists.<Map<String, Object>> newArrayList(), pageNumber, pageSize, 0,
                    0);
        }
        totalPage = totalRow / pageSize;
        if (totalRow % pageSize != 0) {
            totalPage++;
        }
        String pageSql = config.getDialect().forPaginate(pageNumber, pageSize, sql);
        List<Map<String, Object>> list = findList(pageSql, paras);
        return new Page<>(list, pageNumber, pageSize, totalPage, totalRow);
    }

    public Page<B> page(int pageNumber, int pageSize, String sql, Object... paras) {
        return page(getUsefulClass(), pageNumber, pageSize, sql, paras);
    }

    public <T extends Record> Page<T> page(Class<T> cls, int pageNumber, int pageSize, String sql,
            Object... paras) {
        Config config = getConfig();
        if (pageNumber < 1 || pageSize < 1) {
            throw new MyDbException("pageNumber and pageSize must be more than 0");
        }
        int totalRow;
        int totalPage;
        totalRow = getCount(sql, paras);
        if (totalRow < 1) {
            return new Page<>(Lists.<T> newArrayList(), pageNumber, pageSize, 0, 0);
        }
        totalPage = totalRow / pageSize;
        if (totalRow % pageSize != 0) {
            totalPage++;
        }
        String pageSql = config.getDialect().forPaginate(pageNumber, pageSize, sql);
        List<T> list = find(cls, pageSql, paras);
        return new Page<>(list, pageNumber, pageSize, totalPage, totalRow);
    }

    /**
     * 获得总记录数
     *
     * @param sql
     * @param paras
     * @return
     */
    public int getCount(String sql, Object... paras) {
        Config config = getConfig();
        StringBuilder countSql = new StringBuilder();
        config.getDialect().forCount(countSql, sql);
        return config.getJdbcTemplate().queryForObject(countSql.toString(), paras, Integer.class);
    }

    /**
     * 判断菜单是否是叶子节点 getParentId
     *
     * @param pid
     * @return
     * @throws Exception
     */
    public boolean isLeef(Object pid) {
        int count = getCount("select * from " + getTableName() + " where " + getParentId() + " = ?",
                pid);
        return count <= 0;
    }

    /**
     * 执行新增、更新、删除语句
     *
     * @param sql
     * @param args
     * @return
     */
    public int execute(String sql, Object... args) {
        return getJdbcTemplate().update(sql, args);
    }

    /**
     * 批量执行新增、更新、删除语句
     *
     * @param sql
     * @param bpss
     * @return
     */
    public int[] batchExecute(String sql, BatchPreparedStatementSetter bpss) {
        return getJdbcTemplate().batchUpdate(sql, bpss);
    }

    /**
     * 批量执行新增、更新、删除语句
     *
     * @param sql
     * @param batchArgs 每次执行的参数
     * @return
     */
    public int[] batchExecute(String sql, List<Object[]> batchArgs) {
        return getJdbcTemplate().batchUpdate(sql, batchArgs);
    }

    /**
     * 批量执行新增、更新、删除语句
     *
     * @param sqls
     * @return
     */
    public int[] batchExecute(String... sqls) {
        return getJdbcTemplate().batchUpdate(sqls);
    }

    /**
     * 获得当前Model全部字段名
     *
     * @return
     */
    public String getColumnsStr() {
        return getColumnsStr(getUsefulClass());
    }

    /**
     * 获得当前Model全部字段名
     *
     * @param alias 别名
     * @return
     */
    public String getColumnsStr(String alias) {
        return getColumnsStr(getUsefulClass(), alias);
    }

    /**
     * 通过class获得映射table的字段（如：id,name,age）
     *
     * @param cls
     * @return
     */
    public String getColumnsStr(Class<? extends BaseBean> cls) {
        Table t = getTable(cls);
        return t.getColumnsStr();
    }

    /**
     * 通过class获得映射table的字段（如：a.id, a.name, a.age）
     *
     * @param cls
     * @return
     */
    public String getColumnsStr(Class<? extends BaseBean> cls, String alias) {
        Table t = getTable(cls);
        StringBuilder columnsStr = new StringBuilder();
        String[] columns = t.getColumns();
        for (String col : columns) {
            columnsStr.append(" ,").append(alias).append(".").append(col);
        }
        columnsStr.delete(0, 2);
        return columnsStr.toString();
    }

    /**
     * 通过class获得映射表明
     *
     * @param cls
     * @return
     */
    public String getTableName(Class<? extends BaseBean> cls) {
        Table t = getTable(cls);
        return t.getName();
    }

    /**
     * 获得当前Model表名
     *
     * @return
     */
    public String getTableName() {
        return getTableName(getUsefulClass());
    }

    /**
     * 获得指定Model的主键名称
     *
     * @param cls
     * @return
     */
    public String getPrimaryKey(Class<? extends BaseBean> cls) {
        Table t = getTable(cls);
        return t.getPrimaryKey()[0];
    }

    /**
     * 获得指定Model的主键名称
     *
     * @param cls
     * @return
     */
    public String[] getPrimaryKeys(Class<? extends BaseBean> cls) {
        Table t = getTable(cls);
        return t.getPrimaryKey();
    }

    /**
     * 获得主键名称
     *
     * @return
     */
    public String getPrimaryKey() {
        return getPrimaryKey(getUsefulClass());
    }

    /**
     * 获得指定Model的主键名称
     *
     * @param cls
     * @return
     */
    public String getParentId(Class<? extends BaseBean> cls) {
        Table t = getTable(cls);
        return t.getParentId();
    }

    /**
     * 获得主键名称
     *
     * @return
     */
    public String getParentId() {
        return getParentId(getUsefulClass());
    }

    /**
     * 获得单表查询sql（如：select id, name, age from user）
     *
     * @param cls
     * @return
     */
    public String getSelectSql(Class<? extends BaseBean> cls) {
        StringBuilder sql = new StringBuilder();
        sql.append("select ").append(getColumnsStr(cls));
        sql.append(" from ").append(getTableName(cls));
        return sql.toString();
    }

    /**
     * 获得带别名的单表查询sql（如：select u.id, u.name, u.age from user u)
     *
     * @param cls
     * @param alias 表的别名
     * @return
     */
    public String getSelectSql(Class<? extends BaseBean> cls, String alias) {
        StringBuilder sql = new StringBuilder();
        sql.append("select ").append(getColumnsStr(cls, alias));
        sql.append(" from ").append(getTableName(cls)).append(" ").append(alias);
        return sql.toString();
    }

    /**
     * 获得带别名的单表查询sql（如：select u.id, u.name, u.age from user u)
     *
     * @return
     */
    public String getSelectSql() {
        return getSelectSql(getUsefulClass());
    }

    /**
     * 根据class拼装查询sql语句
     *
     * @param sql 拼装后的sql语句
     * @param cls 对应表映射的bean
     * @param attrs key:表字段，value:参数值
     * @param paras 查询参数值
     * @param orderby 排序：order by ...
     */
    public void forSqlFind(Class<? extends BaseBean> cls, StringBuilder sql,
            Map<String, Object> attrs,
            List<Object> paras, String orderby) {
        Table table = getTable(cls);
        Config config = table.getConfig();
        config.getDialect().forModelFind(table, sql, "*", orderby, attrs, paras);
    }

    /**
     * 拼装当前bean的查询sql语句
     *
     * @param sql 拼装后的sql语句
     * @param attrs key:表字段，value:参数值
     * @param paras 查询参数值
     * @param orderby 排序：order by ...
     */
    public void forSqlFind(StringBuilder sql, Map<String, Object> attrs, List<Object> paras,
            String orderby) {
        forSqlFind(getUsefulClass(), sql, attrs, paras, orderby);
    }

    /**
     * 获得单表查询sql（如：select id, name, age from user）
     *
     * @return
     */
    public String getSelectSql(String alias) {
        return getSelectSql(getUsefulClass(), alias);
    }

    @SuppressWarnings("unchecked")
    private Class<B> getUsefulClass() {
        if (this.beanClazz == null) {
            Type genType = this.getClass().getGenericSuperclass();
            Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
            this.beanClazz = (Class<B>) params[0];
        }
        return this.beanClazz;
    }

    private Config getConfig(Class<? extends BaseBean> cls) {
        return getTable(cls).getConfig();
    }

    private Config getConfig() {
        return getConfig(getUsefulClass());
    }

    private Table getTable(Class<? extends BaseBean> cls) {
        return TableUtil.getTable(cls);
    }

    private Table getTable() {
        return getTable(getUsefulClass());
    }

    public JdbcTemplate getJdbcTemplate() {
        return getConfig().getJdbcTemplate();
    }

}
