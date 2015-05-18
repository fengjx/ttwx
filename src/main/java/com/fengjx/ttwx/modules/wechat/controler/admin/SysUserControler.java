
package com.fengjx.ttwx.modules.wechat.controler.admin;

import com.fengjx.ttwx.modules.common.controler.MyController;
import com.fengjx.ttwx.modules.wechat.model.SysUser;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

/**
 * @author fengjx.
 * @date：2015/5/6 0006
 */
public class SysUserControler extends MyController {

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
