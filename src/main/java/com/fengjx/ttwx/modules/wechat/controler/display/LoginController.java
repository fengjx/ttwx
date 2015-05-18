
package com.fengjx.ttwx.modules.wechat.controler.display;

import com.fengjx.ttwx.common.utils.LogUtil;
import com.fengjx.ttwx.common.web.MyExecuteCallback;
import com.fengjx.ttwx.modules.common.constants.AppConfig;
import com.fengjx.ttwx.modules.common.controler.MyController;
import com.fengjx.ttwx.modules.wechat.bean.SysUserEntity;
import com.fengjx.ttwx.modules.wechat.model.SysUser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

/**
 * 用户登录注册
 *
 * @author fengjx.
 * @date：2015/5/16 0016
 */
@Controller
public class LoginController extends MyController {

    private static final Logger LOG = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private SysUser sysUser;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> signin(HttpServletRequest request, SysUserEntity user,
            String valid_code) {
        Map<String, String> res = compareValidCode(request, valid_code);
        if ("0".equals(res.get("code"))) {
            return res;
        }
        SysUserEntity loginUser = sysUser.signin(user.getUsername(), user.getPwd());
        LogUtil.debug(LOG, "查询到登陆用户：" + loginUser);
        if (null == loginUser) {
            return retFail("用户名或密码错误");
        }
        request.getSession().setAttribute(AppConfig.LOGIN_FLAG, loginUser);
        return retSuccess();
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "/wechat/display/login";
    }

    @RequestMapping(value = "/loginout")
    public String loginOut(final HttpServletRequest request) {
        SysUserEntity sysUser = (SysUserEntity) request.getSession().getAttribute(
                AppConfig.LOGIN_FLAG);
        if (null != sysUser) {
            request.getSession().removeAttribute(AppConfig.LOGIN_FLAG);
        }
        return "redirect:/";
    }

    @RequestMapping(value = "/validEmail")
    @ResponseBody
    public String validEmail(String email) {
        boolean flag = sysUser.validEmail(email);
        return flag + "";
    }

    @RequestMapping(value = "/validUser")
    @ResponseBody
    public String validUser(String username) {
        boolean flag = sysUser.validUsername(username);
        return flag + "";
    }

    /**
     * 注册页面跳转
     *
     * @return
     */
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String registerView() {
        return "wechat/display/register";
    }

    /**
     * 注册数据提交
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> register(final HttpServletRequest request) {
        return doResult(new MyExecuteCallback() {
            @Override
            public void execute() throws Exception {
                sysUser.register(getRequestMap(request));
            }
        }, "注册用户失败！");
    }

    /**
     * 注册用户激活
     * 
     * @param ser
     * @return
     */
    @RequestMapping(value = "/activate")
    public String activate(String ser) {
        if (sysUser.activate(ser)) {
            return "/wechat/display/activate-ok";
        }
        return "/wechat/display/activate-error";
    }

}
