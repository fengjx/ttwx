
package com.fengjx.modules.sys.utils;

import com.fengjx.commons.plugin.cache.ehcache.EhCacheUtil;
import com.fengjx.commons.system.init.SpringBeanFactoryUtil;
import com.fengjx.modules.common.constants.AppConfig;
import com.fengjx.modules.sys.entity.SysUserEntity;
import com.fengjx.modules.sys.model.SysMenu;
import com.fengjx.modules.sys.model.SysUser;
import com.fengjx.modules.sys.model.SysUserRole;
import com.fengjx.modules.sys.security.SystemAuthorizingRealm;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

import java.util.List;
import java.util.Map;

/**
 * @version 2016/1/1
 * @Created by FengJianxin
 * @Email xd-fjx@qq.com
 */
public class UserUtil {

    private static final String ALGORITHM_NAME = AppConfig.getConfig("algorithm.name");
    private static final int HASH_ITERATIONS = Integer.valueOf(AppConfig
            .getConfig("hash.iterations"));
    public static final String USER_CACHE_ID_ = "user_cache_id_";
    public static final String USER_CACHE_LOGIN_NAME_ = "user_cache_login_name_";

    public static final String CACHE_ROLE_LIST = "roleList";
    public static final String CACHE_MENU_LIST = "menuList";

    private static final SysMenu sysMenu = SpringBeanFactoryUtil.getBean(SysMenu.class);
    private static final SysUser sysUser = SpringBeanFactoryUtil.getBean(SysUser.class);
    private static final SysUserRole sysUserRole = SpringBeanFactoryUtil.getBean(SysUserRole.class);

    /**
     * 用户密码加密
     *
     * @param pwd
     * @param salt
     * @return
     */
    public static String encryptPwd(String pwd, String salt) {
        SimpleHash simpleHash = new SimpleHash(ALGORITHM_NAME, pwd, salt, HASH_ITERATIONS);
        return simpleHash.toHex();
    }

    /**
     * 根据ID获取用户
     *
     * @param id
     * @return 取不到返回null
     */
    public static SysUserEntity get(String id) {
        SysUserEntity user = EhCacheUtil.get(AppConfig.EhcacheName.SYS_CACHE, USER_CACHE_ID_ + id);
        if (user == null) {
            user = sysUser.get(id);
            if (null != user) {
                user.setRoles(sysUserRole.findUserRoles(user.getId()));
                EhCacheUtil.put(AppConfig.EhcacheName.SYS_CACHE,
                        USER_CACHE_LOGIN_NAME_ + user.getUsername(), user);
                EhCacheUtil.put(AppConfig.EhcacheName.SYS_CACHE, USER_CACHE_ID_ + id, user);
            }
        }
        return user;
    }

    /**
     * 获得当前登录用户菜单
     *
     * @return
     */
    public static List<Map<String, Object>> getMenus() {
        List<Map<String, Object>> menuList = getCache(CACHE_MENU_LIST);
        if (menuList == null) {
            SysUserEntity user = getUser();
            if (user.isAdmin()) {
                menuList = sysMenu.listTreeMenu();
            } else {
                menuList = sysMenu.findUserMenus(user.getId());
            }
            putCache(CACHE_MENU_LIST, menuList);
        }
        return menuList;
    }

    /**
     * 获取授权主要对象
     */
    public static Subject getSubject() {
        return SecurityUtils.getSubject();
    }

    /**
     * 获取当前用户
     *
     * @return 取不到返回 new User()
     */
    public static SysUserEntity getUser() {
        SystemAuthorizingRealm.Principal principal = getPrincipal();
        if (principal != null) {
            SysUserEntity user = get(principal.getId());
            if (user != null) {
                return user;
            }
            return new SysUserEntity();
        }
        // 如果没有登录，则返回实例化空的User对象。
        return new SysUserEntity();
    }

    public static Session getSession() {
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession(false);
        if (session == null) {
            session = subject.getSession();
        }
        return session;
    }

    /**
     * 获取当前登录者对象
     */
    public static SystemAuthorizingRealm.Principal getPrincipal() {
        Subject subject = getSubject();
        return (SystemAuthorizingRealm.Principal) subject.getPrincipal();
    }

    @SuppressWarnings("unchecked")
    public static <T> T getCache(String key) {
        return (T) getCache(key, null);
    }

    @SuppressWarnings("unchecked")
    public static <T> T getCache(String key, T defaultValue) {
        Object obj = getSession().getAttribute(key);
        return obj == null ? defaultValue : (T) obj;
    }

    public static void putCache(String key, Object value) {
        getSession().setAttribute(key, value);
    }

    public static void removeCache(String key) {
        getSession().removeAttribute(key);
    }

}
