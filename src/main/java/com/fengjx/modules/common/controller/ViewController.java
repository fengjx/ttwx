
package com.fengjx.modules.common.controller;

import com.fengjx.commons.plugin.db.Record;
import com.fengjx.commons.system.exception.MyRuntimeException;
import com.fengjx.modules.common.constants.AppConfig;
import com.fengjx.modules.sys.model.SysMenu;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 后台视图
 *
 * @author fengjx. @date：2015/5/17 0017
 */
@Controller
public class ViewController {

    @Autowired
    private SysMenu sysMenu;

    @RequestMapping(value = "${adminPath}")
    public String admin() {
        return "/common/admin";
    }

    /**
     * 各模块后台首页
     *
     * @param module
     * @return
     */
    @RequestMapping(value = "${adminPath}/{module}")
    public String view(@PathVariable("module") String module) {
        if (StringUtils.isBlank(module)) {
            return "/common/admin";
        }
        return "/" + module + AppConfig.ADMIN_PATH + "/index";
    }

    /**
     * 后台菜单跳转
     *
     * @param menuId
     * @return
     */
    @RequestMapping(value = "${adminPath}/f/{menuId}")
    public String forward(@PathVariable("menuId") String menuId) {
        Record menu = sysMenu.get(menuId);
        if (StringUtils.isBlank(menu.getStr("url"))) {
            throw new MyRuntimeException("无效访问路径");
        }
        return "forward:" + menu.getStr("url");
    }

}
