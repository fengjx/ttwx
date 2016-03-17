
package com.fengjx.modules.sys.controller.admin;

import com.fengjx.commons.plugin.db.Record;
import com.fengjx.commons.plugin.db.annotation.BindBean;
import com.fengjx.modules.common.controller.MyController;
import com.fengjx.modules.sys.bean.SysMenu;
import com.fengjx.modules.sys.service.SysMenuService;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * 系统菜单管理
 *
 * @version 2015/10/17
 * @Created by FengJianxin
 * @Email xd-fjx@qq.com
 */
@RequestMapping("${adminPath}/sys/menu")
@Controller
public class SysMenuController extends MyController {

    @Autowired
    private SysMenuService sysMenuService;

    @RequiresPermissions("sys_menu_view")
    @RequestMapping("")
    public String view(Model model) {
        model.addAttribute("treeTable", sysMenuService.listTreeMenu());
        return "sys/admin/menu";
    }

    @RequiresPermissions("sys_menu_edit")
    @RequestMapping("form")
    public String form(String id, String parentId, Model model) {
        // 从父级菜单添加
        if (StringUtils.isNoneBlank(parentId)) {
            Record parent = sysMenuService.findById(parentId);
            model.addAttribute("parent_id", parent.getStr("id"));
            model.addAttribute("parent_ids", (StringUtils.isBlank(parent.getStr("parent_ids")) ? ""
                    : parent.getStr("parent_ids") + ",") + parent.getStr("id"));
            model.addAttribute("parent_name", parent.getStr("name"));
            model.addAttribute("parent_level", parent.getInt("level"));
        } else if (StringUtils.isNotBlank(id)) { // 修改菜单
            model.addAllAttributes(sysMenuService.get(id)._getColumns());
        }
        return "sys/admin/menu_form";
    }

    @RequestMapping("treeNode")
    @ResponseBody
    public List<Map<String, Object>> treeNode() {
        return sysMenuService.listTreeMenu();
    }

    /**
     * 更新 / 保存菜单
     *
     * @param menu
     * @return
     */
    @RequestMapping(value = "save", method = RequestMethod.POST)
    @ResponseBody
    public String addOrUpdate(@BindBean SysMenu menu) {
        sysMenuService.saveOrUpdate(menu);
        return retSuccess();
    }

    /**
     * 删除菜单
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "delete")
    @ResponseBody
    public String delete(@RequestParam(required = true) String id) {
        sysMenuService.deleteMenuById(id);
        return retSuccess("菜单成功删除");
    }

}
