
package com.fengjx.modules.sys.service;

import com.fengjx.commons.plugin.cache.IDataLoader;
import com.fengjx.commons.plugin.cache.ehcache.EhCacheUtil;
import com.fengjx.commons.plugin.db.Model;
import com.fengjx.modules.common.constants.AppConfig;
import com.fengjx.modules.sys.bean.SysRole;
import com.google.common.collect.Sets;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import com.fengjx.modules.sys.bean.SysUserRole;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Autu Generated .
 */
@Component
public class SysUserRoleService extends Model<SysUserRole> {


    private static final String SYS_USER_ROLE = "SysUserRole";

    private List<Map<String, Object>> findAll() {
        return EhCacheUtil.get(AppConfig.EhcacheName.SYS_CACHE, SYS_USER_ROLE,
                new IDataLoader<List<Map<String, Object>>>() {
                    @Override
                    public List<Map<String, Object>> load() {
                        StringBuilder sql = new StringBuilder("select ").append(getColumnsStr("a"));
                        sql.append(", b.name as \"role_name\", b.role_code").append(" from ");
                        sql.append(getTableName()).append(" a join ");
                        sql.append(getTableName(SysRole.class)).append(" b ");
                        sql.append(" on a.role_id = b.id");
                        return findList(sql.toString());
                    }
                });
    }

    /**
     * 查找用户拥有的角色
     *
     * @param userId
     * @return
     */
    public Set<Map<String, Object>> findUserRoles(String userId) {
        if (StringUtils.isBlank(userId)) {
            return null;
        }
        List<Map<String, Object>> list = findAll();
        if (CollectionUtils.isNotEmpty(list)) {
            Set<Map<String, Object>> res = Sets.newHashSet();
            Map<String, Object> m;
            for (Map<String, Object> aList : list) {
                m = aList;
                if (userId.equals(m.get("user_id"))) {
                    res.add(m);
                }
            }
            if (res.size() > 0) {
                return res;
            }
        }
        return null;
    }


}
