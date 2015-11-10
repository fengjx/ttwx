
package com.fengjx.ttwx.modules.sys.controller.admin;

import com.fengjx.ttwx.common.plugin.db.page.AdapterPage;
import com.fengjx.ttwx.modules.common.controller.MyController;
import com.fengjx.ttwx.modules.sys.model.SysUser;
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

    /**
     * 保存用户
     */
    public Map<String, String> save(HttpServletRequest request) {
        sysUser.insert(getRequestMap(request));
        return retSuccess();
    }

    @RequestMapping("pageList")
    @ResponseBody
    public AdapterPage pageList(HttpServletRequest request) {
        return sysUser.pageList(getParamHelper(SysUser.class, request));
    }




}
