
package com.fengjx.modules.sys.model;

import com.fengjx.commons.plugin.cache.IDataLoader;
import com.fengjx.commons.plugin.cache.ehcache.EhCacheUtil;
import com.fengjx.commons.plugin.db.Mapper;
import com.fengjx.commons.plugin.db.Model;
import com.fengjx.modules.common.constants.AppConfig;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * 角色菜单（权限）关联表
 *
 * @version 2015/10/31
 * @Created by FengJianxin
 * @Email xd-fjx@qq.com
 */
@Mapper(table = "sys_role_menu")
@Component
public class SysRoleMenu extends Model {

    private static final String SYS_ROLE_MENU = "SysRoleMenu";

    /**
     * 查询所有菜单角色用户关联
     * select DISTINCT(a.menu_id), a.role_id, c.parent_id, c.`name` AS "menu_name", c.is_show, c.`level`, c.url, c.permission, c.remarks, c.update_time, e.id AS "user_id" from sys_role_menu a
     * join sys_role b on a.role_id = b.id
     * join sys_menu c on a.menu_id = c.id
     * join sys_user_role d on b.id = d.role_id
     * join sys_user e on d.user_id = e.id
     * where
     * b.is_valid = '1'
     * and c.is_valid = '1'
     * and e.is_valid = '1'
     * ORDER BY c.order_no
     */
    public List<Map<String, Object>> findAllRoleMenus() {
        return EhCacheUtil.get(AppConfig.EhcacheName.SYS_CACHE, SYS_ROLE_MENU,
                new IDataLoader<List<Map<String, Object>>>() {
                    @Override
                    public List<Map<String, Object>> load() {
                        StringBuilder sql = new StringBuilder(200);
                        sql.append("select DISTINCT(a.menu_id), a.role_id, c.parent_id, c.`name` AS \"menu_name\", c.is_show, c.`level`, c.url, c.permission, c.remarks, c.update_time, e.id AS \"user_id\" from ");
                        sql.append(getTableName()).append(" a");
                        sql.append(" join ").append(getTableName(SysRole.class))
                                .append(" b on a.role_id = b.id ");
                        sql.append(" join ").append(getTableName(SysMenu.class))
                                .append(" c on a.menu_id = c.id ");
                        sql.append(" join ").append(getTableName(SysUserRole.class))
                                .append(" d on b.id = d.role_id ");
                        sql.append(" join ").append(getTableName(SysUser.class))
                                .append(" e on d.user_id = e.id ");
                        sql.append("where b.is_valid = '1' and c.is_valid = '1' and e.is_valid = '1' ORDER BY c.order_no");
                        return findList(sql.toString());
                    }
                });
    }

}
