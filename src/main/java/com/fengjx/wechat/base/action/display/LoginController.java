
package com.fengjx.wechat.base.action.display;

import com.fengjx.common.utils.LoggerUtil;
import com.fengjx.common.web.action.BaseController;
import com.fengjx.wechat.base.model.SysUser;
import com.fengjx.wechat.base.service.SysUserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

@Controller
public class LoginController extends BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private SysUserService sysUserService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> signin(HttpServletRequest request, SysUser user, String valid_code) {
        setErrorMsg(request, "用户名或密码错误");
        Map res = compareValidCode(request, valid_code);
        if ("0".equals(res.get("code"))) {
            return res;
        }
        SysUser sysUser = this.sysUserService.signin(user.getUsername(), user.getPwd());
        LoggerUtil.debug(LOGGER, "查询到登陆用户：" + sysUser);
        if (null == sysUser) {
            throw new RuntimeException("用户名或密码错误");
        }
        request.getSession().setAttribute("sys_user_login_key", sysUser);
        return retSuccess();
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "/wechat/display/login";
    }

    @RequestMapping("/loginout")
    public String loginOut(HttpServletRequest request) {
        SysUser sysUser = (SysUser) request.getSession().getAttribute("sys_user_login_key");
        if (null != sysUser) {
            request.getSession().removeAttribute("sys_user_login_key");
        }
        return "redirect:/";
    }

    @RequestMapping("/validEmail")
    @ResponseBody
    public String validEmail(String email) {
        boolean flag = this.sysUserService.validEmail(email);
        return flag + "";
    }

    @RequestMapping("/validUser")
    @ResponseBody
    public String validUser(String username) {
        boolean flag = this.sysUserService.validUsername(username);
        return flag + "";
    }
}
