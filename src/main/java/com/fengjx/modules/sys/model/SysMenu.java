
package com.fengjx.modules.sys.model;

import com.fengjx.commons.plugin.cache.IDataLoader;
import com.fengjx.commons.plugin.cache.ehcache.EhCacheUtil;
import com.fengjx.commons.plugin.db.Mapper;
import com.fengjx.commons.plugin.db.Model;
import com.fengjx.commons.plugin.db.Record;
import com.fengjx.modules.common.constants.AppConfig;
import com.google.common.collect.Lists;
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

    private static final String TREE_MENU_CACHE = "listTreeMenu";
    private static final String MENU__ID_CACHE = "menu_id_";

    private static final String ORDER_BY = "order by order_no , update_time desc";

    @Autowired
    private SysRoleMenu sysRoleMenu;

    /**
     * 根据ID查询菜单
     *
     * @param id
     * @return
     */
    public Record get(final String id) {
        return EhCacheUtil.get(AppConfig.EhcacheName.SYS_CACHE, MENU__ID_CACHE + id, new IDataLoader<Record>() {
            @Override
            public Record load() {
                if (StringUtils.isBlank(id)) {
                    return new Record();
                }
                Record record = findById(id);
                if (!record.isEmpty() && StringUtils.isNotBlank(record.getStr("parent_id"))) {
                    Record parent = findById(record.getStr("parent_id"));
                    record.set("parent_name", parent.get("name"));
                    record.set("parent_level", parent.get("level"));
                }
                return record;
            }
        });
    }

    /**
     * 查询树形菜单 treeNode
     *
     * @return
     */
    public List<Map<String, Object>> listTreeMenu() {
        return EhCacheUtil.get(AppConfig.EhcacheName.SYS_CACHE, TREE_MENU_CACHE,
                new IDataLoader<List<Map<String, Object>>>() {
                    @Override
                    public List<Map<String, Object>> load() {
                        return recursive(null);
                    }
                });
    }

    /**
     * 递归查询
     *
     * @param pid 父级ID
     * @return
     */
    private List<Map<String, Object>> recursive(String pid) {
        List<Map<String, Object>> resList = Lists.newArrayList();
        List<Map<String, Object>> list;
        StringBuilder sql = new StringBuilder(getSelectSql("a"));
        sql.append(" where 1 = 1 ");
        if (StringUtils.isBlank(pid)) {
            sql.append(" and (a.parent_id is null or a.parent_id = '') ").append(ORDER_BY);
            list = findList(sql.toString());
        } else {
            sql.append(" and a.parent_id = ? ").append(ORDER_BY);
            list = findList(sql.toString(), pid);
        }
        if (CollectionUtils.isNotEmpty(list)) {
            for (Map<String, Object> m : list) {
                String _id = m.get("id") + "";
                // 如果存在子节点（不是叶子节点），则继续递归查询
                resList.add(m);
                if (!isLeef(_id)) {
                    List<Map<String, Object>> tmpList = recursive(_id);
                    m.put("isLeef", false);
                    m.put("isParent", true);
                    resList.addAll(tmpList);
                } else {
                    m.put("isLeef", true);
                    m.put("isParent", false);
                }
            }
        }
        return resList;
    }

    /**
     * 递归查询用户菜单
     *
     * @param pid
     * @param userId
     * @return
     */
    private List<Map<String, Object>> recursiveTree(String pid, String userId) {
        List<Map<String, Object>> resList = Lists.newArrayList();
        List<Map<String, Object>> list;
        StringBuilder sql = new StringBuilder(getSelectSql("a"));
        sql.append(" where user_id = ? ");
        if (StringUtils.isBlank(pid)) {
            sql.append(" and (a.parent_id is null or a.parent_id = '') ").append(ORDER_BY);
            list = findList(sql.toString(),userId);
        } else {
            sql.append(" and a.parent_id = ? ").append(ORDER_BY);
            list = findList(sql.toString(), userId, pid);
        }
        if (CollectionUtils.isNotEmpty(list)) {
            for (Map<String, Object> m : list) {
                String _id = m.get("id") + "";
                // 如果存在子节点（不是叶子节点），则继续递归查询
                resList.add(m);
                if (!isLeef(_id)) {
                    List<Map<String, Object>> tmpList = recursive(_id);
                    m.put("isLeef", false);
                    m.put("isParent", true);
                    m.put("children", tmpList);
                } else {
                    m.put("isLeef", true);
                    m.put("isParent", false);
                }
            }
        }
        return resList;
    }

    /**
     * 根据pid递归查询菜单
     *
     * @return
     */
    public List<Map<String, Object>> findMenuByPid(){



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

    private static final String USER_MENU = "user_menu_";

    /**
     * 查找用户一级菜单
     *
     * @param userId 用户ID
     * @return
     */
    public List<Map<String, Object>> findUserMenus(final String userId) {
        if (StringUtils.isBlank(userId)) {
            return null;
        }
        return EhCacheUtil.get(AppConfig.EhcacheName.SYS_CACHE, USER_MENU + userId,
                new IDataLoader<List<Map<String, Object>>>() {
                    @Override
                    public List<Map<String, Object>> load() {
                        List<Map<String, Object>> menus = sysRoleMenu.findAllRoleMenus();
                        List<Map<String, Object>> res = Lists.newArrayList();
                        for (Map<String, Object> m : menus) {
                            if (userId.equals(m.get("user_id"))) {
                                res.add(m);
                            }
                        }
                        return res;
                    }
                });
    }


}
