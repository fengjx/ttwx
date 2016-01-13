
package com.fengjx.modules.sys.model;

import com.fengjx.commons.plugin.cache.IDataLoader;
import com.fengjx.commons.plugin.cache.ehcache.EhCacheUtil;
import com.fengjx.commons.plugin.db.Mapper;
import com.fengjx.commons.plugin.db.Model;
import com.fengjx.commons.plugin.db.Record;
import com.fengjx.modules.common.constants.AppConfig;
import com.fengjx.modules.sys.utils.SysUtil;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
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
    private static final String MENU_ID_CACHE = "menu_id_";
    private static final String MENU_URL_CACHE = "menu_url_";

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
        return EhCacheUtil.get(AppConfig.EhcacheName.SYS_CACHE, MENU_ID_CACHE + id,
                new IDataLoader<Record>() {
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

    public Record findByUrl(final String url) {
        if (StringUtils.isBlank(url)) {
            return new Record();
        }
        return EhCacheUtil.get(AppConfig.EhcacheName.SYS_CACHE, MENU_URL_CACHE + url,
                new IDataLoader<Record>() {
                    @Override
                    public Record load() {
                        Map<String, Object> param = Maps.newHashMap();
                        param.put("url", url);
                        return findOne(param);
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
     * @param pid
     * @return
     */
    private List<Map<String, Object>> recursive(String pid) {
        return recursive(pid, null);
    }

    /**
     * 递归查询用户菜单
     *
     * @param pid
     * @param userId
     * @return
     */
    private List<Map<String, Object>> recursive(String pid, String userId) {
        List<Map<String, Object>> resList = Lists.newArrayList();
        List<Map<String, Object>> list;
        StringBuilder sql = new StringBuilder(100);
        sql.append("select ").append(getColumnsStr("a"));
        sql.append(" from ").append(getTableName()).append(" a ");
        if (StringUtils.isNotBlank(userId)) {
            sql.append(" join ").append(getTableName(SysRoleMenu.class))
                    .append(" c ON a.id = c.menu_id ");
            sql.append(" join ").append(getTableName(SysRole.class))
                    .append(" b ON c.role_id = b.id ");
            sql.append(" join ").append(getTableName(SysUserRole.class))
                    .append(" d ON b.id = d.role_id ");
            sql.append(" join ").append(getTableName(SysUser.class))
                    .append(" e ON d.user_id = e.id ");
        }
        sql.append(" where 1 = 1 ");
        if (StringUtils.isNotBlank(userId)) {
            sql.append("and e.id = ? and b.is_valid = '1' and c.is_valid = '1' and e.is_valid = '1' ");
        }
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
                    List<Map<String, Object>> tmpList = recursive(_id, userId);
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

    public void saveOrUpdate(Map<String, Object> attrs) {
        attrs.put("update_time", new Date());
        insertOrUpdate(attrs);
        SysUtil.deleteSysCache();
    }

    public void deleteMenuById(String id) {
        deleteById(id);
        SysUtil.deleteSysCache();
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
                        return recursive(null, userId);
                    }
                });
    }

}
