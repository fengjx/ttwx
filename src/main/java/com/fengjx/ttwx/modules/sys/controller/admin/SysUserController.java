
package com.fengjx.ttwx.modules.sys.controller.admin;

import com.fengjx.ttwx.modules.common.controller.MyController;
import com.fengjx.ttwx.modules.sys.model.SysUser;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author fengjx.
 * @date：2015/5/6 0006
 */
public class SysUserController extends MyController {

    @Autowired
    private SysUser sysUser;

    /**
     * 保存用户
     */
    public Map<String, String> save(HttpServletRequest request) {
        sysUser.insert(getRequestMap(request));
        return retSuccess();
    }

}
