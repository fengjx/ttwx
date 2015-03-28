
package com.fjx.wechat.base.admin.action;

import com.fjx.common.action.BaseController;
import com.fjx.common.utils.CommonUtils;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@Controller
@RequestMapping("/admin")
public class CommonController extends BaseController {

    @RequestMapping(value = "/main")
    public String view() {
        return "/wechat/admin/main";
    }

    @RequestMapping("/center")
    public String center() {
        return "/wechat/admin/layout/center";
    }

    @RequestMapping("/east")
    public String east() {
        return "/wechat/admin/layout/east";
    }

    @RequestMapping("/north")
    public String north() {
        return "/wechat/admin/layout/north";
    }

    @RequestMapping("/south")
    public String south() {
        return "/wechat/admin/layout/south";
    }

    @RequestMapping("/west")
    public String west() {
        return "/wechat/admin/layout/west";
    }

    @RequestMapping("/sysmenu")
	@ResponseBody
    public String menu() throws IOException {
        return IOUtils.toString(new FileInputStream(CommonUtils.getClassPath() + File.separator
                + "admin-menu.json"),"UTF-8");
    }

}
