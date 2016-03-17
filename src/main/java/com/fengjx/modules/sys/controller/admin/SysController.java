
package com.fengjx.modules.sys.controller.admin;

import com.fengjx.modules.common.controller.MyController;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * @version 2016/2/14
 * @Created by FengJianxin
 * @Email xd-fjx@qq.com
 */
@RequestMapping("${adminPath}")
@Controller
public class SysController extends MyController {

    @RequestMapping("tag/treeselect")
    public String tagTreeselect(HttpServletRequest request, Model m) {
        m.addAllAttributes(getNotBlankParams(request));
        return "sys/admin/tagTreeselect";
    }

}
