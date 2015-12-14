
package com.fengjx.modules.sys.controller.admin;

import com.fengjx.commons.plugin.db.ParamHelper;
import com.fengjx.commons.plugin.db.page.AdapterPage;
import com.fengjx.modules.common.controller.MyController;
import com.fengjx.modules.sys.model.SysUser;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

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
    public Map<String, String> save(HttpServletRequest request) {
        validateRequired("username", "用户名不能为空");
        validateRequired("email", "邮箱不能为空");
        ParamHelper param = getParamHelper(SysUser.class, request);
        if (StringUtils.isBlank(param.getStr("id"))) {
            Map<String, Object> attr = param.getParams();
            attr.put("pwd", "admin");
            sysUser.register(param.getParams());
        } else {
            sysUser.update(param.getParams());
        }
        return retSuccess();
    }

    @RequestMapping("pageList")
    @ResponseBody
    public AdapterPage pageList(HttpServletRequest request) {
        return sysUser.pageList(getParamHelper(SysUser.class, request));
    }

    @RequestMapping("delete")
    @ResponseBody
    public Map<String, String> delete(String id) {
        sysUser.deleteById(id);
        return retSuccess("菜单成功删除");
    }

}
