
package com.fengjx.modules.common.controller;

import com.fengjx.commons.web.BaseController;
import com.fengjx.modules.sys.bean.SysUser;
import com.fengjx.modules.sys.utils.UserUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

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
     * 用户是否已经登录
     *
     * @return
     */
    protected boolean isLogin(){
        SysUser user = UserUtil.getUser();
        return StringUtils.isNotBlank(user.getId());
    }

    /**
     * 获得当前登录用户
     */
    protected SysUser getLoginSysUser() {
        return UserUtil.getUser();
    }

    /**
     * 获得当前登录用户ID
     */
    protected String getLoginSysUserId() {
        return UserUtil.getUser().getId();
    }

}
