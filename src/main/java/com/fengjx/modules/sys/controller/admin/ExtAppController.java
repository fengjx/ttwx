
package com.fengjx.modules.sys.controller.admin;

import com.fengjx.commons.plugin.db.Record;
import com.fengjx.commons.plugin.db.page.AdapterPage;
import com.fengjx.modules.common.controller.MyController;
import com.fengjx.modules.sys.model.ExtApp;
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
@RequestMapping("${adminPath}/sys/ext")
public class ExtAppController extends MyController {

    @Autowired
    private ExtApp extApp;

    @RequestMapping("")
    public String view() {
        return "sys/admin/extapp";
    }

    @RequestMapping("pageList")
    @ResponseBody
    public AdapterPage pageList(HttpServletRequest request) {
        return extApp.pageList(getRecord(ExtApp.class, request));
    }

    @RequestMapping("save")
    @ResponseBody
    public String save(HttpServletRequest request) {
        Record record = getRecord(ExtApp.class, request);
        record.set("in_time", new Date());
        String[] msgTypes = StringUtils.split(record.getStr("reqTypes"), ",");
        String[] eventTypes = StringUtils.split(record.getStr("eventTypes"), ",");
        extApp.saveExtApi(record, msgTypes, eventTypes);
        return retSuccess();
    }

    @RequestMapping("delete")
    @ResponseBody
    public String delete(String id, String app_type) {
        extApp.deleteApp(id, app_type);
        return retSuccess();
    }
}
