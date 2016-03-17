
package com.fengjx.modules.sys.security;

import com.fengjx.commons.utils.LogUtil;
import com.fengjx.commons.utils.WebUtil;
import com.fengjx.modules.common.constants.AppConfig;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * 表单验证（包含验证码）过滤类
 *
 * @author fengjx
 * @version 2015-10-17
 */
public class FormAuthenticationFilter
        extends org.apache.shiro.web.filter.authc.FormAuthenticationFilter {

    private static final Logger LOG = LoggerFactory.getLogger(FormAuthenticationFilter.class);

    private String captchaParam;

    @Override
    protected AuthenticationToken createToken(ServletRequest request, ServletResponse response) {
        String username = getUsername(request);
        String password = getPassword(request);
        if (password == null) {
            password = "";
        }
        boolean rememberMe = isRememberMe(request);
        String host = WebUtil.getRemoteAddr((HttpServletRequest) request);
        String captcha = getCaptcha(request);
        return new UsernamePasswordToken(username, password, rememberMe, host, captcha);
    }

    protected String getCaptcha(ServletRequest request) {
        return WebUtils.getCleanParam(request, getCaptchaParam());
    }

    /**
     * 返回true，交给直接controller（/login）处理
     *
     * @param token
     * @param subject
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @Override
    protected boolean onLoginSuccess(AuthenticationToken token, Subject subject,
            ServletRequest request, ServletResponse response) throws Exception {
        return true;
    }

    /**
     * 登录失败调用事件
     */
    @Override
    protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e,
            ServletRequest request, ServletResponse response) {
        String className = e.getClass().getName(), message = "";
        if (IncorrectCredentialsException.class.getName().equals(className)
                || UnknownAccountException.class.getName().equals(className)) {
            message = "用户或密码错误, 请重试！";
        } else if (e.getMessage() != null && StringUtils.startsWith(e.getMessage(), "msg:")) {
            message = StringUtils.replace(e.getMessage(), "msg:", "");
        } else {
            message = "系统出现点问题，请稍后再试！";
            LogUtil.error(LOG, "系统登录异常", e);
        }
        request.setAttribute(getFailureKeyAttribute(), className);
        request.setAttribute(AppConfig.REQUEST_ERROE_MSG_KEY, message);
        return true;
    }

    public String getCaptchaParam() {
        return captchaParam;
    }

    public void setCaptchaParam(String captchaParam) {
        this.captchaParam = captchaParam;
    }

}
