
package com.fengjx.ttwx.modules.common.controller;

import com.fengjx.ttwx.modules.common.constants.AppConfig;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 后台视图
 *
 * @author fengjx.
 * @date：2015/5/17 0017
 */
@Controller
public class ViewController {

    @RequestMapping(value = "${adminPath}")
    public String admin() {
        return "/common/admin";
    }

    @RequestMapping(value = "${adminPath}/{module}")
    public String view(@PathVariable("module") String module) {
        if (StringUtils.isBlank(module)) {
            return "/common/admin";
        }
        return "/" + module + AppConfig.ADMIN_PATH + "/index";
    }

}
