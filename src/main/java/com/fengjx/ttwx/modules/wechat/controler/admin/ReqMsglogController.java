
package com.fengjx.ttwx.modules.wechat.controler.admin;

import com.fengjx.ttwx.common.utils.WebUtil;
import com.fengjx.ttwx.modules.common.controler.MyController;
import com.fengjx.ttwx.modules.wechat.model.ReqMsgLog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * @author fengjx.
 * @date：2015/5/19 0019
 */
@RequestMapping("/admin/wechat/msglog")
@Controller
public class ReqMsglogController extends MyController {

    @Autowired
    private ReqMsgLog reqMsgLog;

    @RequestMapping(value = "")
    public ModelAndView view(String openid) {
        ModelAndView mv = new ModelAndView("wechat/admin/msg_log");
        mv.addObject("openid", openid);
        return mv;
    }

    @RequestMapping(value = "/pageList")
    @ResponseBody
    public String pageList(HttpServletRequest request) {
        return reqMsgLog.pageList(WebUtil.getRequestParams(request), getLoginSysUserId(request))
                .toJson();
    }

}
