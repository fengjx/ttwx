
package com.fengjx.ttwx.modules.sys.controller.admin;

import com.fengjx.ttwx.common.plugin.db.ParamHelper;
import com.fengjx.ttwx.modules.common.controller.MyController;
import com.fengjx.ttwx.modules.sys.model.SysRole;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
 * 角色管理
 *
 * @version 2015/10/31
 * @Created by FengJianxin
 * @Email xd-fjx@qq.com
 */
@RequestMapping("${adminPath}/sys/role")
@Controller
public class SysRoleController extends MyController {

    private static final Logger LOG = LoggerFactory.getLogger(SysRoleController.class);

    @Autowired
    private SysRole sysRole;

    @RequestMapping("")
    public String view() {
        return "sys/admin/role";
    }

    @RequestMapping("list")
    @ResponseBody
    public List<Map<String, Object>> list() {
        return sysRole.findList(null);
    }

    /**
     * 删除角色
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Map<String, String> delete(@RequestParam(required = true) String id) {
        sysRole.deleteRole(id);
        return retSuccess("角色成功删除");
    }

    @RequiresPermissions("sys_menu_edit")
    @RequestMapping("form")
    public String form(String id, Model model) {
        if (StringUtils.isNotBlank(id)) {
            model.addAllAttributes(sysRole.findById(id).getColumns());
            model.addAttribute("menuIds", sysRole.getMenuIds(id));
        }
        return "sys/admin/role_form";
    }

    /**
     * 更新 / 保存角色
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "save", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> addOrUpdate(HttpServletRequest request) {
        validate();
        ParamHelper params = getParamHelper(SysRole.class, request);
        sysRole.saveOrUpdate(params);
        return retSuccess();
    }

    /**
     * 数据校验
     */
    private void validate() {
        validateRequired("name", "角色名称不能为空");
        validateRequired("role_code", "角色标识不能为空");
        HttpServletRequest request = getRequest();
        String oldName = request.getParameter("oldName");
        String name = request.getParameter("name");
        if (!name.equals(oldName) && !sysRole.getRoleByName(name).isEmpty()) {
            addError("角色名称已存在");
        }
        String oldRoleCode = request.getParameter("oldRoleCode");
        String roleCode = request.getParameter("role_code");
        if (!roleCode.equals(oldRoleCode) && !sysRole.getRoleByCode(roleCode).isEmpty()) {
            addError("角色标识已存在");
        }
    }

}
