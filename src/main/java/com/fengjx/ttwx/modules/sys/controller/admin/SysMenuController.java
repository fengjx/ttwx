
package com.fengjx.ttwx.modules.sys.controller.admin;

import com.fengjx.ttwx.common.plugin.db.Record;
import com.fengjx.ttwx.modules.common.controller.MyController;
import com.fengjx.ttwx.modules.sys.model.SysMenu;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
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
    private SysMenu sysMenu;

    @RequiresPermissions("sys:menu:view")
    @RequestMapping("")
    public String view(Model model) {
        model.addAttribute("treeTable", sysMenu.treeTable());
        return "sys/admin/menu";
    }

    @RequiresPermissions("sys:menu:view")
    @RequestMapping("form")
    public String form(String id, String parentId, Model model) {
        // 从父级菜单添加
        if(StringUtils.isNoneBlank(parentId)){
            Record parent = sysMenu.findById(parentId);
            model.addAttribute("parent_id", parent.getStr("id"));
            model.addAttribute("parent_name", parent.getStr("name"));
            model.addAttribute("parent_level", parent.getInt("level"));
        }else if(StringUtils.isNotBlank(id)){  // 修改菜单
            model.addAllAttributes(sysMenu.get(id).getColumns());
        }
        return "sys/admin/menu_form";
    }

    @RequestMapping("treeNode")
    @ResponseBody
    public List<Map<String, Object>> treeNode() {
        return sysMenu.treeNode();
    }

    /**
     * 更新 / 保存菜单
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "save", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> addOrUpdate(HttpServletRequest request) {
        Map<String, Object> attrs = getNotBlankRequestMap(request);
        sysMenu.saveOrUpdate(attrs);
        return retSuccess();
    }

    /**
     * 删除菜单
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Map<String, String> delete(@RequestParam(required = true) String id) {
        sysMenu.deleteMenuById(id);
        return retSuccess("菜单成功删除");
    }

}
