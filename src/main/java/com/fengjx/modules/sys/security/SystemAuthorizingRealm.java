
package com.fengjx.modules.sys.security;

import com.fengjx.commons.system.security.shiro.session.SessionDAO;
import com.fengjx.modules.common.constants.AppConfig;
import com.fengjx.modules.sys.bean.SysUser;
import com.fengjx.modules.sys.entity.SysUserEntity;
import com.fengjx.modules.sys.service.SystemService;
import com.fengjx.modules.sys.utils.UserUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.Permission;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 系统安全认证实现类
 * 
 * @author Fengjianxin
 * @version 2016-1-3
 */
public class SystemAuthorizingRealm extends AuthorizingRealm {

    private static final Logger LOG = LoggerFactory.getLogger(SystemAuthorizingRealm.class);

    @Autowired
    private SystemService systemService;

    @Autowired
    private SessionDAO sessionDao;

    /**
     * 认证回调函数, 登录时调用
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) {
        UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
        int activeSessionSize = sessionDao.getActiveSessions(false).size();
        LOG.debug("login submit, active session size: {}, username: {}", activeSessionSize,
                token.getUsername());
        // 测试环境忽略掉验证码校验
        if (!AppConfig.isTest()) {
            Session session = UserUtil.getSession();
            String code = (String) session
                    .getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
            // 校验验证码
            if (token.getCaptcha() == null || !token.getCaptcha().equalsIgnoreCase(code)) {
                throw new AuthenticationException("msg:验证码错误, 请重试.");
            }
        }
        // 校验用户名密码
        SysUser user = systemService.getUserByLoginName(token.getUsername());
        if (user != null) {
            if (SysUserEntity.FREEZE_ALIVE.equals(user.getIsValid())) {
                throw new AuthenticationException("msg:该已帐号禁止登录.");
            }
            // 如果查询到返回认证信息AuthenticationInfo
            return new SimpleAuthenticationInfo(new Principal(user), user.getPwd(),
                    ByteSource.Util.bytes(user.getSalt()), this.getName());
        } else {
            return null;
        }
    }

    /**
     * 授权查询回调函数, 进行鉴权但缓存中无用户的授权信息时调用
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        Principal principal = (Principal) getAvailablePrincipal(principals);
        // 是否允许同时多个登陆
        if (!AppConfig.TRUE.equals(AppConfig.getConfig("user.multiAccountLogin"))) {
            Collection<Session> sessions = sessionDao.getActiveSessions(true, principal,
                    UserUtil.getSession());
            if (sessions.size() > 0) {
                // 如果是登录进来的，则踢出已在线用户
                if (UserUtil.getSubject().isAuthenticated()) {
                    for (Session session : sessions) {
                        sessionDao.delete(session);
                    }
                }
                // 记住我进来的，并且当前用户已登录，则退出当前用户提示信息。
                else {
                    UserUtil.getSubject().logout();
                    throw new AuthenticationException("msg:账号已在其它地方登录，请重新登录。");
                }
            }
        }
        SysUser user = systemService.getUserByLoginName(principal.getLoginName());
        if (user != null) {
            SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
            List<Map<String, Object>> list = UserUtil.getMenus();
            String permission;
            for (Map<String, Object> menu : list) {
                permission = (String) menu.get("permission");
                if (StringUtils.isNotBlank(permission)) {
                    // 添加基于Permission的权限信息
                    for (String p : StringUtils.split(permission, ",")) {
                        info.addStringPermission(p);
                    }
                }
            }
            // 添加用户权限
            info.addStringPermission("user");
            // 添加用户角色信息
            for (Map<String, Object> role : user.getRoles()) {
                info.addRole((String) role.get("role_code"));
            }
            return info;
        } else {
            return null;
        }
    }

    @Override
    protected void checkPermission(Permission permission, AuthorizationInfo info) {
        authorizationValidate(permission);
        super.checkPermission(permission, info);
    }

    @Override
    protected boolean[] isPermitted(List<Permission> permissions, AuthorizationInfo info) {
        if (permissions != null && !permissions.isEmpty()) {
            for (Permission permission : permissions) {
                authorizationValidate(permission);
            }
        }
        return super.isPermitted(permissions, info);
    }

    @Override
    public boolean isPermitted(PrincipalCollection principals, Permission permission) {
        authorizationValidate(permission);
        return super.isPermitted(principals, permission);
    }

    @Override
    protected boolean isPermittedAll(Collection<Permission> permissions, AuthorizationInfo info) {
        if (permissions != null && !permissions.isEmpty()) {
            for (Permission permission : permissions) {
                authorizationValidate(permission);
            }
        }
        return super.isPermittedAll(permissions, info);
    }

    /**
     * 授权验证方法
     * 
     * @param permission
     */
    private void authorizationValidate(Permission permission) {
        // 模块授权预留接口
    }

    /**
     * 授权用户信息
     */
    public static class Principal implements Serializable {

        private static final long serialVersionUID = 1L;

        private String id; // 编号
        private String loginName; // 登录名

        public Principal(SysUser user) {
            this.id = user.getId();
            this.loginName = user.getUsername();
        }

        public String getId() {
            return id;
        }

        public String getLoginName() {
            return loginName;
        }

        /**
         * 获取SESSIONID
         */
        public String getSessionid() {
            try {
                return (String) UserUtil.getSession().getId();
            } catch (Exception e) {
                return "";
            }
        }

        @Override
        public String toString() {
            return id;
        }

    }
}
