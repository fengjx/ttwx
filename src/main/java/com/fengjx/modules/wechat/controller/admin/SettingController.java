
package com.fengjx.modules.wechat.controller.admin;

import com.fengjx.modules.common.controller.MyController;
import com.fengjx.modules.wechat.service.WechatPublicAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * 授权设置
 *
 * @author fengjx. @date：2015/5/17 0017
 */
@Controller
@RequestMapping("${adminPath}/wechat/setting")
public class SettingController extends MyController {

    @Autowired
    private WechatPublicAccountService publicAccountService;

    @RequestMapping(value = "")
    public ModelAndView view(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView("wechat/admin/setting");
        mv.addObject("wechatAccount",
                publicAccountService.getAccountByUserId(getLoginSysUserId()).getColumns());
        return mv;
    }

    /**
     * 更新授权信息
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public String update(final HttpServletRequest request) {
        publicAccountService.updateAccount(getRequestMap(request), getLoginSysUserId());
        return retSuccess();
    }

    @RequestMapping(value = "/reset", method = RequestMethod.POST)
    @ResponseBody
    public String reset(HttpServletRequest request, String id) {
        publicAccountService.reset(id, getLoginSysUserId());
        return retSuccess();
    }

}
