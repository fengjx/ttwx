
package com.fengjx.ttwx.modules.common.controler;

import com.fengjx.ttwx.common.utils.LogUtil;
import com.fengjx.ttwx.common.web.BaseController;
import com.fengjx.ttwx.modules.common.constants.AppConfig;
import com.fengjx.ttwx.modules.wechat.bean.SysUserEntity;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

/**
 * @author fengjx.
 * @date：2015/5/18 0018
 */
public abstract class MyController extends BaseController {

    private static final Logger LOG = LoggerFactory.getLogger(MyController.class);

    /**
     * 校验验证码
     *
     * @param request
     * @param valid_code
     * @return
     */
    protected Map<String, String> compareValidCode(HttpServletRequest request,
            String valid_code) {
        Map<String, String> res = new HashMap<String, String>();
        res.put("code", "1");
        res.put("msg", "验证码正确");
        String code = getSession(request).getAttribute(
                com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY)
                + "";
        LogUtil.debug(LOG, "比较验证码code=" + code + " valid_code=" + valid_code);
        if (StringUtils.isBlank(code)) {
            res.put("code", "0");
            res.put("msg", "页面超时，请重试！");
        } else if (!code.equalsIgnoreCase(valid_code)) {
            res.put("code", "0");
            res.put("msg", "验证码错误！");
        }
        return res;
    }

    /**
     * 获得当前登录用户
     *
     * @param request
     * @return
     */
    protected SysUserEntity getLoginSysUser(HttpServletRequest request) {
        return (SysUserEntity) getSession(request).getAttribute(AppConfig.LOGIN_FLAG);
    }

}
