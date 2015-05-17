
package com.fengjx.ttwx.modules.common.controler;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 后台视图
 *
 * @author fengjx.
 * @date：2015/5/17 0017
 */
@Controller
public class ViewController {

    @RequestMapping(value = "/admin")
    public String view() {
        return "/common/index";
    }

}
