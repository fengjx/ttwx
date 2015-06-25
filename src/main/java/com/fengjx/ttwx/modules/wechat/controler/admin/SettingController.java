
package com.fengjx.ttwx.modules.wechat.controler.admin;

import com.fengjx.ttwx.modules.common.controler.MyController;
import com.fengjx.ttwx.modules.wechat.model.WechatPublicAccount;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

/**
 * 授权设置
 *
 * @author fengjx.
 * @date：2015/5/17 0017
 */
@Controller
@RequestMapping("/admin/wechat/setting")
public class SettingController extends MyController {

    @Autowired
    private WechatPublicAccount publicAccount;

    @RequestMapping(value = "")
    public ModelAndView view(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView("wechat/admin/setting");
        mv.addObject("wechatAccount", publicAccount.getAccountSetting(getLoginSysUserId(request))
                .getColumns());
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
    public Map<String, String> addOrUpdate(final HttpServletRequest request) {
        publicAccount.update(getRequestMap(request));
        return retSuccess();
    }

}
