
package com.fengjx.modules.sys.model;

import com.fengjx.commons.plugin.cache.IDataLoader;
import com.fengjx.commons.plugin.cache.ehcache.EhCacheUtil;
import com.fengjx.commons.plugin.db.Mapper;
import com.fengjx.commons.plugin.db.Model;
import com.fengjx.commons.plugin.db.Record;
import com.fengjx.modules.common.constants.AppConfig;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 系统菜单
 *
 * @version 2015/10/17
 * @Created by FengJianxin
 * @Email xd-fjx@qq.com
 */
@Mapper(table = "sys_menu", pid = "parent_id")
@Component
public class SysMenu extends Model {

    // tree
    private static final String TREE_NODE_CACHE = "treeNode";
    // tree table
    private static final String TREE_TABLE_CACHE = "treeTable";

    private static final String ORDER_BY = "order by order_no , update_time desc";

    /**
     * 根据ID查询菜单
     *
     * @param id
     * @return
     */
    public Record get(String id) {
        if (StringUtils.isBlank(id)) {
            return new Record();
        }
        Record record = findById(id);
        if (!record.isEmpty() && StringUtils.isNoneBlank(record.getStr("parent_id"))) {
            Record parent = findById(record.getStr("parent_id"));
            record.set("parent_name", parent.get("name"));
            record.set("parent_level", parent.get("level"));
        }
        return record;
    }

    /**
     * 查询树形菜单 treeNode
     *
     * @return
     */
    public List<Map<String, Object>> treeNode() {
        return EhCacheUtil.get(AppConfig.EhcacheName.SYS_CACHE, TREE_NODE_CACHE,
                new IDataLoader<List<Map<String, Object>>>() {
                    @Override
                    public List<Map<String, Object>> load() {
                        return loadMenuById(null);
                    }
                });
    }

    /**
     * 查询树形菜单 treeTable
     *
     * @return
     */
    public List<Map<String, Object>> treeTable() {
        List<Map<String, Object>> rows = EhCacheUtil.get(AppConfig.EhcacheName.SYS_CACHE, TREE_TABLE_CACHE,
                new IDataLoader<List<Map<String, Object>>>() {
                    @Override
                    public List<Map<String, Object>> load() {
                        return loadMenuDetailById(null);
                    }
                });
        return rows;
    }

    /**
     * 递归查询
     *
     * @param pid
     * @return
     */
    private List<Map<String, Object>> loadMenuDetailById(String pid) {
        List<Map<String, Object>> resList = new ArrayList<>();
        List<Map<String, Object>> list;
        StringBuilder sql = new StringBuilder(getSelectSql("a"));
        sql.append(" where 1 = 1 ");
        if (StringUtils.isBlank(pid)) {
            sql.append(" and (a.parent_id is null or a.parent_id = '') ");
            sql.append(ORDER_BY);
            list = findList(sql.toString());
        } else {
            sql.append(" and a.parent_id = ? ");
            sql.append(ORDER_BY);
            list = findList(sql.toString(), pid);
        }
        if (CollectionUtils.isNotEmpty(list)) {
            for (int i = 0, l = list.size(); i < l; i++) {
                String _id = list.get(i).get("id") + "";
                // 如果存在子节点（不是叶子节点），则继续递归查询
                resList.add(list.get(i));
                if (!isLeef(_id)) {
                    List<Map<String, Object>> tmpList = loadMenuDetailById(_id);
                    list.get(i).put("isLeef", false);
                    list.get(i).put("isParent", true);
                    resList.addAll(tmpList);
                } else {
                    list.get(i).put("isLeef", true);
                    list.get(i).put("isParent", false);
                }
            }
        }
        return resList;
    }


    /**
     * 递归查询
     *
     * @param pid
     * @return
     */
    private List<Map<String, Object>> loadMenuById(String pid) {
        List<Map<String, Object>> res;
        StringBuilder sql = new StringBuilder("select a.id, a.parent_id, a.name, a.level from ");
        sql.append(getTableName()).append(" a");
        sql.append(" where 1 = 1 ");
        if (StringUtils.isBlank(pid)) {
            sql.append(" and (a.parent_id is null or a.parent_id = '') ");
            sql.append(ORDER_BY);
            res = findList(sql.toString());
        } else {
            sql.append(" and a.parent_id = ? ");
            sql.append(ORDER_BY);
            res = findList(sql.toString(), pid);
        }
        if (CollectionUtils.isNotEmpty(res)) {
            for (int i = 0, l = res.size(); i < l; i++) {
                String _id = res.get(i).get("id") + "";
                // 如果存在子节点（不是叶子节点），则继续递归查询
                if (!isLeef(_id)) {
                    List<Map<String, Object>> tmpList = loadMenuDetailById(_id);
                    res.addAll(tmpList);
                    res.get(i).put("isLeef", false);
                    res.get(i).put("isParent", true);
                } else {
                    res.get(i).put("isLeef", true);
                    res.get(i).put("isParent", false);
                }
            }
        }
        return res;
    }

    // 删除菜单缓存数据
    private void deleteMenuCache() {
        EhCacheUtil.remove(AppConfig.EhcacheName.SYS_CACHE, TREE_NODE_CACHE);
        EhCacheUtil.remove(AppConfig.EhcacheName.SYS_CACHE, TREE_TABLE_CACHE);
    }

    public void saveOrUpdate(Map<String, Object> attrs) {
        attrs.put("update_time", new Date());
        insertOrUpdate(attrs);
        deleteMenuCache();
    }


    public void deleteMenuById(String id) {
        deleteById(id);
        deleteMenuCache();
    }

}
