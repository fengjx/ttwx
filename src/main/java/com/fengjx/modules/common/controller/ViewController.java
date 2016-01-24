
package com.fengjx.modules.common.controller;

import com.fengjx.commons.plugin.db.Record;
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
     * 后台菜单跳转
     *
     * @param menuId
     * @return
     */
    @RequestMapping(value = "${adminPath}/f/{menuId}")
    public String forward(@PathVariable("menuId") String menuId, Model model) {
        Record menu = sysMenuService.get(menuId);
        if (StringUtils.isBlank(menu.getStr("url"))) {
            return "forward:" + adminPath;
        }
        String pid = menu.getStr("id");
        if (StringUtils.isNotBlank(menu.getStr("parents_ids"))) {
            pid = StringUtils.split(menu.getStr("parents_ids"), ",")[0];
        } else if (StringUtils.isNotBlank(menu.getStr("parent_id"))) {
            pid = menu.getStr("parent_id");
        }
        model.addAttribute("admin_menu_pid", pid);
        return "forward:" + menu.getStr("url");
    }

}
