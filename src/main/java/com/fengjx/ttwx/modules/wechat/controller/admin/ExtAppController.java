
package com.fengjx.ttwx.modules.wechat.controller.admin;

import com.fengjx.ttwx.modules.common.controller.MyController;
import com.fengjx.ttwx.modules.wechat.model.ExtApp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 业务可扩展
 *
 * @author fengjx.
 * @date：2015/5/30 0030
 */
@Controller
@RequestMapping("/admin/wechat/ext")
public class ExtAppController extends MyController {

    @Autowired
    private ExtApp extApp;

    @RequestMapping("selecter")
    public ModelAndView selecter(String app_type, String msg_type, String event_type) {
        ModelAndView mv = new ModelAndView("wechat/admin/inc_ext_app");
        mv.addObject("apps", extApp.listByType(app_type, msg_type, event_type));
        return mv;
    }

}
