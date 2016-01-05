
package com.fengjx.modules.common.controller;

import com.fengjx.commons.web.BaseController;
import com.fengjx.modules.common.constants.AppConfig;
import com.fengjx.modules.sys.entity.SysUserEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import javax.servlet.http.HttpServletRequest;

/**
 * @author fengjx.
 * @date：2015/5/18 0018
 */
public abstract class MyController extends BaseController {

    private static final Logger LOG = LoggerFactory.getLogger(MyController.class);


    /**
     * 管理基础路径
     */
    @Value("${adminPath}")
    protected String adminPath;

    /**
     * 获得当前登录用户
     *
     * @param request
     * @return
     */
    protected SysUserEntity getLoginSysUser(HttpServletRequest request) {
        return (SysUserEntity) getSession(request).getAttribute(AppConfig.LOGIN_FLAG);
    }

    /**
     * 获得当前登录用户ID
     *
     * @param request
     * @return
     */
    protected String getLoginSysUserId(HttpServletRequest request) {
        return getLoginSysUser(request).getId();
    }

}
