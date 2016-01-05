
package com.fengjx.modules.sys.controller.admin;

import com.fengjx.commons.plugin.db.Record;
import com.fengjx.commons.plugin.db.page.AdapterPage;
import com.fengjx.modules.common.controller.MyController;
import com.fengjx.modules.sys.model.SysUser;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * 用户管理
 *
 * @author fengjx.
 * @date：2015/5/6 0006
 */
@Controller
@RequestMapping("${adminPath}/sys/user")
public class SysUserController extends MyController {

    @Autowired
    private SysUser sysUser;

    @RequestMapping("")
    public String view() {
        return "sys/admin/user";
    }

    @RequestMapping("save")
    @ResponseBody
    public String save(HttpServletRequest request) {
        validateRequired("username", "用户名不能为空");
        validateRequired("email", "邮箱不能为空");
        Record record = getRecord(SysUser.class, request);
        if (StringUtils.isBlank(record.getStr("id"))) {
            record.set("pwd", "admin");
            sysUser.register(record);
        } else {
            sysUser.update(record);
        }
        return retSuccess();
    }

    @RequestMapping("pageList")
    @ResponseBody
    public AdapterPage pageList(HttpServletRequest request) {
        return sysUser.pageList(getRecord(SysUser.class, request));
    }

    @RequestMapping("delete")
    @ResponseBody
    public String delete(String id) {
        sysUser.deleteById(id);
        return retSuccess("菜单成功删除");
    }

}
