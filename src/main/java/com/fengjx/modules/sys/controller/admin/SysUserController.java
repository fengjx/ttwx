
package com.fengjx.modules.sys.controller.admin;

import com.fengjx.modules.common.controller.MyController;
import com.fengjx.modules.sys.bean.SysUser;
import com.fengjx.modules.sys.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * 用户管理
 *
 * @author fengjx. @date：2015/5/6 0006
 */
@Controller
@RequestMapping("${adminPath}/sys/user")
public class SysUserController extends MyController {

    @Autowired
    private SysUserService sysUserService;

    @RequestMapping("")
    public String view() {
        return "sys/admin/user";
    }

    @RequestMapping("save")
    @ResponseBody
    public String save(HttpServletRequest request) {
        validateRequired("username", "用户名不能为空");
        validateRequired("email", "邮箱不能为空");
        SysUser record = getModel(SysUser.class, request);
        sysUserService.saveOrUpdate(record);
        return retSuccess();
    }

    @RequestMapping("pageList")
    @ResponseBody
    public Object pageList(HttpServletRequest request) {
        return sysUserService.pageList(getRecord(SysUser.class, request));
    }

    @RequestMapping("delete")
    @ResponseBody
    public String delete(String id) {
        sysUserService.deleteById(id);
        return retSuccess("菜单成功删除");
    }

}
