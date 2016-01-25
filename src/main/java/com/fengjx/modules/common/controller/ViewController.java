
package com.fengjx.modules.common.controller;

import com.fengjx.modules.common.constants.AppConfig;
import com.fengjx.modules.sys.service.SysMenuService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 后台视图
 *
 * @author fengjx. @date：2015/5/17 0017
 */
@Controller
public class ViewController extends MyController {

    @Autowired
    private SysMenuService sysMenuService;

    @RequestMapping(value = "${adminPath}")
    public String admin() {
        return "/common/admin";
    }

    /**
     * 各模块后台首页
     *
     * @param module
     * @return
     */
    @RequestMapping(value = "${adminPath}/{module}")
    public String view(@PathVariable("module") String module) {
        if (StringUtils.isBlank(module)) {
            return "/common/admin";
        }
        return "/" + module + AppConfig.ADMIN_PATH + "/index";
    }

    /**
     * 查询用户菜单
     *
     * @param pid
     * @return
     */
    @RequestMapping(value = "${adminPath}/leftMenu")
    public String findUserMenus(String pid, Model m) {
        m.addAttribute("menu_pid", pid);
        m.addAttribute("menus", sysMenuService.findUserMenus(pid, getLoginSysUser()));
        return "common/inc/admin-left-menu";
    }

}
