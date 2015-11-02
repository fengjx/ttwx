
package com.fengjx.ttwx.common.plugin.db;

import com.fengjx.ttwx.common.plugin.db.page.Page;
import com.fengjx.ttwx.common.plugin.db.page.PageContext;
import com.fengjx.ttwx.common.utils.CommonUtils;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author fengjx. @date：2015/5/8 0008
 */
public abstract class Model {

    // 注入jdbcTemplate
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public boolean insert(Class<? extends Model> cls, Map<String, Object> attrs) {
        Table table = TableUtil.getTable(cls);
        String pk = table.getPrimaryKey();
        if (StringUtils.isBlank((String) attrs.get(pk))) {
            attrs.put(pk, CommonUtils.getPrimaryKey());
        }
        StringBuilder sql = new StringBuilder();
        List<Object> params = new ArrayList<>();
        Config.dialect.forModelSave(table, attrs, sql, params);
        return jdbcTemplate.update(sql.toString(), params.toArray()) >= 1;
    }

    public boolean insert(Map<String, Object> attrs) {
        return insert(getClass(), attrs);
    }

    public void insertOrUpdate(Map<String, Object> attrs) {
        insertOrUpdate(getClass(), attrs);
    }

    /**
     * 新增或修改 - 如果是新增，那么生成的ID是UUID
     *
     * @param cls
     * @param attrs
     */
    public void insertOrUpdate(Class<? extends Model> cls, Map<String, Object> attrs) {
        Table table = TableUtil.getTable(cls);
        String pk = table.getPrimaryKey();
        if (StringUtils.isBlank((String) attrs.get(pk))) {
            insert(attrs);
        } else {
            update(attrs);
        }
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
        return deleteById(this.getClass(), id);
    }

    public boolean deleteById(Class<? extends Model> cls, Object id) {
        Table table = TableUtil.getTable(cls);
        String sql = Config.dialect.forModelDeleteById(table);
        return jdbcTemplate.update(sql, id) >= 1;
    }

    /**
     * Update model.
     */
    public boolean update(Map<String, Object> attrs) {
        return update(getClass(), attrs);
    }

    /**
     * Update model.
     */
    public boolean update(Class<? extends Model> cls, Map<String, Object> attrs) {
        Table table = TableUtil.getTable(cls);
        String pKey = table.getPrimaryKey();
        Object id = attrs.get(pKey);
        if (id == null) {
            throw new MyDbException("You can't update model without Primary Key.");
        }
        StringBuilder sql = new StringBuilder();
        List<Object> paras = new ArrayList<>();
        Config.dialect.forModelUpdate(table, attrs, pKey, id, sql, paras);
        if (paras.size() <= 1) { // Needn't update
            return false;
        }
        int result = jdbcTemplate.update(sql.toString(), paras.toArray());
        return result >= 1;
    }

    public Record findById(Object id) {
        return findById(id, "*");
    }

    /**
     * Find model by id. Fetch the specific columns only. Example: User user =
     * findById(15, "name, age");
     *
     * @param id the id value of the model
     * @param columns the specific columns separate with comma character ==> ","
     */
    public Record findById(Object id, String columns) {
        return findById(this.getClass(), id, columns);
    }

    public Record findById(Class<? extends Model> cls, Object id, String columns) {
        Table table = TableUtil.getTable(cls);
        String sql = Config.dialect.forModelFindById(table, columns);
        return findOne(sql, id);
    }

    /**
     * 查询单条记录
     *
     * @param attrs 查询条件及参数
     * @return
     */
    public Record findOne(Map<String, Object> attrs) {
        return findOne(this.getClass(), attrs);
    }

    /**
     * 根据Model查询单条记录
     *
     * @param cls 映射的class
     * @param attrs 查询条件及参数
     * @return
     */
    public Record findOne(Class<? extends Model> cls, Map<String, Object> attrs) {
        Table table = TableUtil.getTable(cls);
        StringBuilder sql = new StringBuilder();
        List<Object> paras = new ArrayList<>();
        Config.dialect.forModelFind(table, sql, "*", null, attrs, paras);
        return findOne(sql.toString(), paras.toArray());
    }

    /**
     * 根据sql查询单条记录
     *
     * @param sql
     * @param params
     * @return
     */
    public Record findOne(String sql, Object... params) {
        List<Map<String, Object>> list = findList(sql, params);
        if (CollectionUtils.isEmpty(list)) {
            return new Record();
        } else if (list.size() > 1) {
            throw new MyDbException("Incorrect result size: expected 1, actual " + list.size());
        }
        Map<String, Object> map = list.get(0);
        return new Record(map);
    }

    /**
     * 查询多条记录
     *
     * @param attrs 查询条件及参数
     * @return
     */
    public List<Map<String, Object>> findList(Map<String, Object> attrs) {
        return findList(attrs, null);
    }

    /**
     * 查询多条记录
     *
     * @param attrs
     * @param orderby 排序艾段
     * @return
     */
    public List<Map<String, Object>> findList(Map<String, Object> attrs, String orderby) {
        return findList(this.getClass(), attrs, orderby);
    }

    /**
     * 根据Model查询多条记录
     *
     * @param cls 映射的class
     * @param attrs 查询条件及参数
     * @return
     */
    public List<Map<String, Object>> findList(Class<? extends Model> cls,
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
    public List<Map<String, Object>> findList(Class<? extends Model> cls,
            Map<String, Object> attrs,
            String orderby) {
        Table table = TableUtil.getTable(cls);
        StringBuilder sql = new StringBuilder();
        List<Object> paras = new ArrayList<>();
        Config.dialect.forModelFind(table, sql, "*", orderby, attrs, paras);
        return findList(sql.toString(), paras.toArray());
    }

    /**
     * 根据sql查询多条记录
     *
     * @param sql
     * @param params
     * @return
     */
    public List<Map<String, Object>> findList(String sql, Object... params) {
        return jdbcTemplate.queryForList(sql, params);
    }

    /**
     * 单表查询，查询当前Model关联的表，此查询依赖PageContext
     *
     * @param attrs
     * @return
     */
    public Page<Map<String, Object>> paginate(Map<String, Object> attrs) {
        return paginate(attrs, null);
    }

    public Page<Map<String, Object>> paginate(Map<String, Object> attrs, String orderby) {
        return paginate(this.getClass(), attrs, orderby);
    }

    /**
     * 单表查询，此查询依赖PageContext
     *
     * @param cls
     * @param attrs
     * @return
     */
    public Page<Map<String, Object>> paginate(Class<? extends Model> cls,
            Map<String, Object> attrs) {
        return paginate(cls, attrs, null);
    }

    /**
     * paginate(User.class,attrs,"order by in_time")
     *
     * @param cls
     * @param attrs
     * @param orderby
     * @return
     */
    public Page<Map<String, Object>> paginate(Class<? extends Model> cls,
            Map<String, Object> attrs,
            String orderby) {
        Table table = TableUtil.getTable(cls);
        StringBuilder sql = new StringBuilder();
        List<Object> paras = new ArrayList<>();
        Config.dialect.forModelFind(table, sql, "*", orderby, attrs, paras);
        return paginate(PageContext.getPageNumber(), PageContext.getPageSize(), sql.toString(),
                paras.toArray());
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
        if (pageNumber < 1 || pageSize < 1) {
            throw new MyDbException("pageNumber and pageSize must be more than 0");
        }
        Long totalRow = 0L;
        int totalPage = 0;
        totalRow = getCount(sql, paras);
        if (totalRow < 1) {
            return new Page<>(new ArrayList<Map<String, Object>>(0), pageNumber, pageSize, 0, 0L);
        }
        totalPage = totalRow.intValue() / pageSize;
        if (totalRow % pageSize != 0) {
            totalPage++;
        }
        StringBuilder pageSql = new StringBuilder();
        Config.dialect.forPaginate(pageSql, pageNumber, pageSize, sql);
        List<Map<String, Object>> list = findList(pageSql.toString(), paras);
        return new Page<>(list, pageNumber, pageSize, totalPage, totalRow);
    }

    /**
     * 获得总记录数
     * 
     * @param sql
     * @param paras
     * @return
     */
    public Long getCount(String sql, Object... paras) {
        StringBuilder countSql = new StringBuilder();
        Config.dialect.forCount(countSql, sql);
        return jdbcTemplate.queryForObject(countSql.toString(), paras, Long.class);
    }

    /**
     * 判断菜单是否是叶子节点 getParentId
     * 
     * @param pid
     * @return
     * @throws Exception
     */
    public boolean isLeef(Object pid) {
        StringBuilder sql = new StringBuilder("select * from ");
        sql.append(getTableName()).append(" where ").append(getParentId()).append(" = ?");
        Long count = getCount(sql.toString(), pid);
        if (count > 0) {
            return false;
        }
        return true;
    }

    /**
     * 执行新增、更新、删除语句
     *
     * @param sql
     * @param args
     * @return
     */
    public int execute(String sql, Object... args) {
        return jdbcTemplate.update(sql, args);
    }

    /**
     * 批量执行新增、更新、删除语句
     *
     * @param sql
     * @param bpss
     * @return
     */
    public int[] batchExecute(String sql, BatchPreparedStatementSetter bpss) {
        return jdbcTemplate.batchUpdate(sql, bpss);
    }

    /**
     * 批量执行新增、更新、删除语句
     *
     * @param sql
     * @param batchArgs 每次执行的参数
     * @return
     */
    public int[] batchExecute(String sql, List<Object[]> batchArgs) {
        return jdbcTemplate.batchUpdate(sql, batchArgs);
    }

    /**
     * 批量执行新增、更新、删除语句
     *
     * @param sqls
     * @return
     */
    public int[] batchExecute(String... sqls) {
        return jdbcTemplate.batchUpdate(sqls);
    }

    /**
     * 获得当前Model全部字段名
     *
     * @return
     */
    public String getColumnsStr() {
        return getColumnsStr(this.getClass());
    }

    /**
     * 获得当前Model全部字段名
     *
     * @param alias 别名
     * @return
     */
    public String getColumnsStr(String alias) {
        return getColumnsStr(this.getClass(), alias);
    }

    /**
     * 通过class获得映射table的字段（如：id,name,age）
     *
     * @param cls
     * @return
     */
    public String getColumnsStr(Class<? extends Model> cls) {
        Table t = TableUtil.getTable(cls);
        return t.getColumnsStr();
    }

    /**
     * 通过class获得映射table的字段（如：a.id, a.name, a.age）
     *
     * @param cls
     * @return
     */
    public String getColumnsStr(Class<? extends Model> cls, String alias) {
        Table t = TableUtil.getTable(cls);
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
    public String getTableName(Class<? extends Model> cls) {
        Table t = TableUtil.getTable(cls);
        return t.getName();
    }

    /**
     * 获得当前Model表名
     *
     * @return
     */
    public String getTableName() {
        return getTableName(this.getClass());
    }

    /**
     * 获得指定Model的主键名称
     *
     * @param cls
     * @return
     */
    public String getPrimaryKey(Class<? extends Model> cls) {
        Table t = TableUtil.getTable(cls);
        return t.getPrimaryKey();
    }

    /**
     * 获得主键名称
     *
     * @return
     */
    public String getPrimaryKey() {
        return getPrimaryKey(this.getClass());
    }

    /**
     * 获得指定Model的主键名称
     *
     * @param cls
     * @return
     */
    public String getParentId(Class<? extends Model> cls) {
        Table t = TableUtil.getTable(cls);
        return t.getParentId();
    }

    /**
     * 获得主键名称
     *
     * @return
     */
    public String getParentId() {
        return getParentId(this.getClass());
    }

    /**
     * 获得单表查询sql（如：select id, name, age from user）
     *
     * @param cls
     * @return
     */
    public String getSelectSql(Class<? extends Model> cls) {
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
    public String getSelectSql(Class<? extends Model> cls, String alias) {
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
        return getSelectSql(this.getClass());
    }

    /**
     * 获得单表查询sql（如：select id, name, age from user）
     *
     * @return
     */
    public String getSelectSql(String alias) {
        return getSelectSql(this.getClass(), alias);
    }

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}
