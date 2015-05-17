
package com.fengjx.ttwx.modules.wechat.controler.display;

import com.fengjx.ttwx.common.web.BaseController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author fengjx.
 * @date：2015/5/16 0016
 */
@Controller
public class DisplayController extends BaseController {

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
