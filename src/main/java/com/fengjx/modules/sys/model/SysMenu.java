
package com.fengjx.modules.sys.model;

import com.fengjx.commons.plugin.cache.IDataLoader;
import com.fengjx.commons.plugin.cache.ehcache.EhCacheUtil;
import com.fengjx.commons.plugin.db.Mapper;
import com.fengjx.commons.plugin.db.Model;
import com.fengjx.commons.plugin.db.Record;
import com.fengjx.modules.common.constants.AppConfig;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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

    // tree menu key
    private static final String TREE_MENU_CACHE = "treeMenu";

    private static final String ORDER_BY = "order by order_no , update_time desc";

    @Autowired
    private SysRole sysRole;

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
        return EhCacheUtil.get(AppConfig.EhcacheName.SYS_CACHE, TREE_MENU_CACHE,
                new IDataLoader<List<Map<String, Object>>>() {
                    @Override
                    public List<Map<String, Object>> load() {
                        return recursive(null);
                    }
                });
    }

    /**
     * 查询树形菜单 treeTable
     *
     * @return
     */
    public List<Map<String, Object>> treeTable() {
        List<Map<String, Object>> rows = EhCacheUtil.get(AppConfig.EhcacheName.SYS_CACHE, TREE_MENU_CACHE,
                new IDataLoader<List<Map<String, Object>>>() {
                    @Override
                    public List<Map<String, Object>> load() {
                        return recursive(null);
                    }
                });
        return rows;
    }

    /**
     * 递归查询
     *
     * @param pid 父级ID
     * @return
     */
    private List<Map<String, Object>> recursive(String pid){
        List<Map<String, Object>> resList;
        StringBuilder sql = new StringBuilder(getSelectSql("a"));
        sql.append(" where 1 = 1 ");
        if (StringUtils.isBlank(pid)) {
            sql.append(" and (a.parent_id is null or a.parent_id = '') ").append(ORDER_BY);
            resList = findList(sql.toString());
        } else {
            sql.append(" and a.parent_id = ? ").append(ORDER_BY);
            resList = findList(sql.toString(), pid);
        }
        if (CollectionUtils.isNotEmpty(resList)) {
            for (int i = 0, l = resList.size(); i < l; i++) {
                String _id = resList.get(i).get("id") + "";
                // 如果存在子节点（不是叶子节点），则继续递归查询
                if (!isLeef(_id)) {
                    List<Map<String, Object>> tmpList = recursive(_id);
                    resList.addAll(tmpList);
                    resList.get(i).put("isLeef", false);
                    resList.get(i).put("isParent", true);
                } else {
                    resList.get(i).put("isLeef", true);
                    resList.get(i).put("isParent", false);
                }
            }
        }
        return resList;
    }

    // 删除菜单缓存数据
    private void deleteMenuCache() {
        EhCacheUtil.remove(AppConfig.EhcacheName.SYS_CACHE, TREE_MENU_CACHE);
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

    /**
     * 查找用户一级菜单
     *
     * @param username 用户名
     * @return
     */
    public List<Map<String, Object>> findUserHeadMenus(String username){

        return null;
    }

}
