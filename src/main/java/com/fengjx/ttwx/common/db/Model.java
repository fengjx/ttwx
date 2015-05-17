
package com.fengjx.ttwx.common.db;

import com.fengjx.ttwx.common.utils.CommonUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author fengjx.
 * @date：2015/5/8 0008
 */
public abstract class Model {

    // 注入jdbcTemplate
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public int insert(Class<? extends Model> cls, Map<String, Object> attrs) {
        Table table = TableUtil.getTable(cls);
        String pk = table.getPrimaryKey();
        if(StringUtils.isBlank((String)attrs.get(pk))){
            attrs.put(pk, CommonUtils.getPrimaryKey());
        }
        StringBuilder sql = new StringBuilder();
        List<Object> paras = new ArrayList<Object>();
        Config.dialect.forModelSave(table, attrs, sql, paras);
        return jdbcTemplate.update(sql.toString(), paras.toArray());
    }

    public int insert(Map<String, Object> attrs) {
        return insert(getClass(), attrs);
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
        List<Object> paras = new ArrayList<Object>();
        Config.dialect.forModelUpdate(table, attrs, pKey, id, sql, paras);
        if (paras.size() <= 1) { // Needn't update
            return false;
        }
        int result = jdbcTemplate.update(sql.toString(), paras.toArray());
        return result >= 1;
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
        List<Object> paras = new ArrayList<Object>();
        Config.dialect.forModelFind(table, sql, "*", attrs, paras);
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
        return new Record(jdbcTemplate.queryForMap(sql, params));
    }

    /**
     * 查询多条记录
     *
     * @param attrs 查询条件及参数
     * @return
     */
    public List<Map<String, Object>> findList(Map<String, Object> attrs) {
        return findList(this.getClass(), attrs);
    }

    /**
     * 根据Model查询多条记录
     *
     * @param cls 映射的class
     * @param attrs 查询条件及参数
     * @return
     */
    public List<Map<String, Object>> findList(Class<? extends Model> cls, Map<String, Object> attrs) {
        Table table = TableUtil.getTable(cls);
        StringBuilder sql = new StringBuilder();
        List<Object> paras = new ArrayList<Object>();
        Config.dialect.forModelFind(table, sql, "*", attrs, paras);
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
        return paginate(this.getClass(), attrs);
    }

    /**
     * 单表查询，此查询依赖PageContext
     *
     * @param cls
     * @param attrs
     * @return
     */
    public Page<Map<String, Object>> paginate(Class<? extends Model> cls, Map<String, Object> attrs) {
        Table table = TableUtil.getTable(cls);
        StringBuilder sql = new StringBuilder();
        List<Object> paras = new ArrayList<Object>();
        Config.dialect.forModelFind(table, sql, "*", attrs, paras);
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
        int totalRow = 0;
        int totalPage = 0;

        StringBuilder countSql = new StringBuilder();
        Config.dialect.forCount(countSql, sql);
        totalRow = jdbcTemplate.queryForObject(countSql.toString(), paras, Integer.class);
        if (totalRow < 1) {
            return new Page<Map<String, Object>>(new ArrayList<Map<String, Object>>(0), pageNumber,
                    pageSize, 0, 0);
        }
        totalPage = totalRow / pageSize;
        if (totalRow % pageSize != 0) {
            totalPage++;
        }
        StringBuilder pageSql = new StringBuilder();
        Config.dialect.forPaginate(pageSql, pageNumber, pageSize, sql);
        List<Map<String, Object>> list = findList(pageSql.toString(), paras);
        return new Page<Map<String, Object>>(list, pageNumber, pageSize, totalPage, totalRow);
    }

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}
