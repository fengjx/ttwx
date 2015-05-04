
package com.fengjx.wechat.base.action.display;

import com.fengjx.common.web.action.BaseController;
import com.fengjx.wechat.base.model.SysUser;
import com.fengjx.wechat.base.service.SysUserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

@Controller
public class DisplayController extends BaseController {

    @Autowired
    private SysUserService sysUserService;

    @RequestMapping({
            "", "index"
    })
    public String view() {
        return "wechat/display/index";
    }

    @RequestMapping("/contact")
    public String contact() {
        return "/wechat/display/contact";
    }

    @RequestMapping("/about")
    public String about() {
        return "/wechat/display/about";
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String registerView() {
        return "wechat/display/register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> register(HttpServletRequest request, SysUser user)
            throws Exception {
        setErrorMsg(request, "注册用户失败！");
        this.sysUserService.register(user);
        return retSuccess();
    }

    @RequestMapping("/activate")
    public String activate(String ser) {
        if (this.sysUserService.activate(ser)) {
            return "/wechat/display/activate-ok";
        }
        return "/wechat/display/activate-error";
    }
}
