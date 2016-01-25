
package com.fengjx.modules.sys.service;

import com.fengjx.commons.plugin.cache.ehcache.EhCacheUtil;
import com.fengjx.modules.common.constants.AppConfig;
import com.fengjx.modules.sys.bean.SysUser;
import com.fengjx.modules.sys.utils.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 系统管理，安全相关实体的管理类,包括用户、角色、菜单.
 * 
 * @author ThinkGem
 * @version 2013-12-05
 */
@Service
@Transactional(readOnly = true)
public class SystemService {

    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SysUserRoleService sysUserRoleService;

    /**
     * 根据登录名获取用户
     * 
     * @param loginName 登录账号
     * @return 取不到返回null
     */
    public SysUser getUserByLoginName(final String loginName) {
        SysUser user = EhCacheUtil.get(AppConfig.EhcacheName.SYS_CACHE,
                UserUtil.USER_CACHE_LOGIN_NAME_ + loginName);
        if (null == user) {
            user = sysUserService.getUserByUsername(loginName);
            if (user == null || user.isEmpty()) {
                return null;
            }
            user.setRoles(sysUserRoleService.findUserRoles(user.getId()));
            EhCacheUtil.put(AppConfig.EhcacheName.SYS_CACHE, UserUtil.USER_CACHE_LOGIN_NAME_
                    + loginName, user);
            EhCacheUtil.put(AppConfig.EhcacheName.SYS_CACHE,
                    UserUtil.USER_CACHE_ID_ + user.getId(), user);
        }
        return user;
    }

}
