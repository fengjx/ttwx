
package com.fengjx.wechat.base.action.admin;

import com.fengjx.common.web.action.BaseController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class ViewAction extends BaseController {
    @RequestMapping("/main")
    public String view() {
        return "/wechat/admin/main";
    }

    @RequestMapping("/base")
    public String base() {
        return "/wechat/admin/base/view";
    }
}
