
package com.fengjx.modules.sys.controller.admin;

import com.fengjx.commons.plugin.cache.ehcache.EhCacheUtil;
import com.fengjx.commons.plugin.db.annotation.BindBean;
import com.fengjx.modules.common.constants.AppConfig;
import com.fengjx.modules.common.controller.MyController;
import com.fengjx.modules.sys.bean.SysDict;
import com.fengjx.modules.sys.service.SysDictService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * 字典管理
 *
 * @Created by FengJianxin on 2015/8/23.
 * @Email xd-fjx@qq.com
 */
@Controller
@RequestMapping("${adminPath}/sys/dict")
public class DictAdminController extends MyController {

    @Autowired
    private SysDictService dictService;

    @RequestMapping(value = "")
    public String view() {
        return "sys/admin/dict";
    }

    @RequestMapping("pageList")
    @ResponseBody
    public Object pageList(@BindBean SysDict dict) {
        return dictService.findPage(dict);
    }

    @RequestMapping("save")
    @ResponseBody
    public String save(HttpServletRequest request) {
        SysDict sysDict = getModel(SysDict.class, request);
        sysDict.set("in_time", new Date());
        if (StringUtils.isBlank(sysDict.getId())) {
            dictService.save(sysDict);
        } else {
            dictService.update(sysDict);
        }
        EhCacheUtil.removeAll(AppConfig.EhcacheName.DICT_CACHE);
        return retSuccess();
    }

    @RequestMapping("delete")
    @ResponseBody
    public String delete(String id) {
        dictService.deleteById(id);
        EhCacheUtil.removeAll(AppConfig.EhcacheName.DICT_CACHE);
        return retSuccess();
    }
}
