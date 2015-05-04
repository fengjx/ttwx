
package com.fengjx.wechat.base.action.admin;

import com.fengjx.common.web.action.BaseController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/base")
public class UserAction extends BaseController {

    @RequestMapping("/user")
    public String view() {
        return "/wechat/admin/base/user";
    }
}
