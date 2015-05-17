
package com.fengjx.ttwx.modules.wechat.controler.admin;

import com.fengjx.ttwx.common.web.BaseController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author fengjx.
 * @date：2015/5/17 0017
 */
@Controller
@RequestMapping("/admin/wechat")
public class WechatController extends BaseController {

    @RequestMapping(value = {
            "", "index"
    })
    public String view() {
        return "wechat/admin/index";
    }

}
