package com.fengjx.ttwx.modules.wechat.controler.admin;

import com.fengjx.ttwx.common.utils.WebUtil;
import com.fengjx.ttwx.common.web.BaseController;
import com.fengjx.ttwx.modules.wechat.model.SysUser;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 *
 *
 * @author fengjx.
 * @date：2015/5/6 0006
 */
public class SysUserControler extends BaseController {

    @Autowired
    private SysUser sysUser;

    /**
     * 保存用户
     */
    public Map<String,String> save(HttpServletRequest request){
        sysUser.insert(getRequestMap(request));
        return retSuccess();
    }


}
