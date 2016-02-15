
package com.fengjx.modules.common.controller;

import com.fengjx.modules.sys.service.SysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
