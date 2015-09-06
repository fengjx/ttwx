
package com.fengjx.ttwx.modules.sys.controller.admin;

import com.fengjx.ttwx.common.plugin.db.ParamHelper;
import com.fengjx.ttwx.common.plugin.db.page.AdapterPage;
import com.fengjx.ttwx.modules.common.controller.MyController;
import com.fengjx.ttwx.modules.sys.model.ExtApp;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Map;

/**
 * 业务扩展
 *
 * @author fengjx.
 * @date：2015/5/30 0030
 */
@Controller
@RequestMapping("/admin/sys/ext")
public class ExtAppController extends MyController {

    @Autowired
    private ExtApp extApp;

    @RequestMapping("")
    public String view() {
        return "sys/extapp";
    }

    @RequestMapping("selecter")
    public ModelAndView selecter(String app_type, String msg_type, String event_type) {
        ModelAndView mv = new ModelAndView("sys/inc_ext_app");
        // mv.addObject("apps", extApp.listByType(app_type, msg_type,
        // event_type));
        return mv;
    }

    @RequestMapping("pageList")
    @ResponseBody
    public AdapterPage pageList(HttpServletRequest request) {
        return extApp.pageList(getParamHelper(ExtApp.class, request));
    }

    @RequestMapping("save")
    @ResponseBody
    public Map<String, String> save(HttpServletRequest request) {
        ParamHelper paramHelper = getParamHelper(ExtApp.class, request);
        paramHelper.set("in_time", new Date());
        String[] msgTypes = StringUtils.split(paramHelper.getStr("reqTypes"), ",");
        String[] eventTypes = StringUtils.split(paramHelper.getStr("eventTypes"), ",");
        extApp.saveExtApi(paramHelper, msgTypes, eventTypes);
        return retSuccess();
    }

}
