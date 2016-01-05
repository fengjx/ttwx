
package com.fengjx.modules.sys.model;

import com.fengjx.commons.plugin.cache.IDataLoader;
import com.fengjx.commons.plugin.cache.ehcache.EhCacheUtil;
import com.fengjx.commons.plugin.db.Mapper;
import com.fengjx.commons.plugin.db.Model;
import com.fengjx.modules.common.constants.AppConfig;
import com.google.common.collect.Sets;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Set;

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

    @Autowired
    private SysUserRole sysUserRole;

    private List<Map<String, Object>> findAll() {
        return EhCacheUtil.get(AppConfig.EhcacheName.SYS_CACHE, SYS_ROLE_MENU,
                new IDataLoader<List<Map<String, Object>>>() {
                    @Override
                    public List<Map<String, Object>> load() {
                        return findList(null);
                    }
                });
    }

    /**
     * 根据用户ID获得菜单ID
     *
     * @param userId
     * @return
     */
    public String[] getMenuIds(String userId) {
        if (StringUtils.isBlank(userId)) {
            return null;
        }
        String[] roleIds = sysUserRole.findUserRoleIds(userId);
        if (ArrayUtils.isEmpty(roleIds)) {
            return null;
        }
        List<Map<String, Object>> list = findAll();
        if (CollectionUtils.isNotEmpty(list)) {
            Set<String> res = Sets.newHashSet();
            Map<String, Object> m;
            for (int i = 0; i < list.size(); i++) {
                m = list.get(i);
                if (ArrayUtils.contains(roleIds, m.get("role_id"))) {
                    res.add((String) m.get("menu_id"));
                }
            }
            if (res.size() > 0) {
                return res.toArray(new String[res.size()]);
            }
        }
        return null;
    }


}
