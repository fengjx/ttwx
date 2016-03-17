
package com.fengjx.modules.wechat.controller.admin;

import com.fengjx.modules.common.controller.MyController;
import com.fengjx.modules.wechat.bean.WechatExtApp;
import com.fengjx.modules.wechat.service.WechatExtAppService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * 业务扩展
 *
 * @author fengjx. @date：2015/5/30 0030
 */
@Controller
@RequestMapping("${adminPath}/wechat/ext")
public class ExtAppController extends MyController {

    @Autowired
    private WechatExtAppService extAppService;

    @RequestMapping("")
    public String view() {
        return "wechat/admin/extapp";
    }

    @RequestMapping("pageList")
    @ResponseBody
    public Object pageList(HttpServletRequest request) {
        return extAppService.pageList(getRecord(WechatExtApp.class, request));
    }

    @RequestMapping("save")
    @ResponseBody
    public String save(HttpServletRequest request) {
        WechatExtApp record = getModel(WechatExtApp.class, request);
        record.set("in_time", new Date());
        String[] msgTypes = StringUtils.split(record.getStr("reqTypes"), ",");
        String[] eventTypes = StringUtils.split(record.getStr("eventTypes"), ",");
        extAppService.saveExtApi(record, msgTypes, eventTypes);
        return retSuccess();
    }

    @RequestMapping("delete")
    @ResponseBody
    public String delete(String id, String app_type) {
        extAppService.deleteApp(id, app_type);
        return retSuccess();
    }
}
