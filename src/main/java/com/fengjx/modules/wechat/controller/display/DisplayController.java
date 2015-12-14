
package com.fengjx.modules.wechat.controller.display;

import com.fengjx.modules.common.controller.MyController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author fengjx.
 * @date：2015/5/16 0016
 */
@Controller
public class DisplayController extends MyController {

    @RequestMapping(value = {
            "", "index"
    })
    public String view() {
        return "wechat/display/index";
    }

    @RequestMapping("/contact")
    public String contact() {
        return "/wechat/display/contact";
    }

    @RequestMapping("/about")
    public String about() {
        return "/wechat/display/about";
    }

}
