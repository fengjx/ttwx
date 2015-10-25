package com.fengjx.ttwx.modules.sys.controller.admin;

import com.fengjx.ttwx.common.plugin.cache.ehcache.EhCacheUtil;
import com.fengjx.ttwx.common.plugin.db.Record;
import com.fengjx.ttwx.modules.common.controller.MyController;
import com.fengjx.ttwx.modules.sys.model.SysMenu;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 系统菜单管理
 *
 * @version 2015/10/17
 * @Created by FengJianxin
 * @Email xd-fjx@qq.com
 */
@RequestMapping("${adminPath}/sys/menu")
@Controller
public class SysMenuController extends MyController {

    @Autowired
    private SysMenu sysMenu;

    @RequiresPermissions("sys:menu:view")
    @RequestMapping("")
    public String view() {
        return "sys/admin/menu";
    }

    @RequiresPermissions("sys:menu:view")
    @RequestMapping("form")
    public String form(String id, Model m) {
        Record record = new Record();
        if(StringUtils.isNoneBlank(id)){
            record = sysMenu.findById(id);
        }
        if(!record.isEmpty()){
            m.addAttribute(record.getColumns());
        }
        return "sys/admin/menu_form";
    }


    @RequestMapping("treeNode")
    @ResponseBody
    public List<Map<String, Object>> treeNode(){
        return sysMenu.treeNode();
    }


    @RequestMapping("tree")
    @ResponseBody
    public List<Map<String, Object>> treeTable(){
        return sysMenu.treeTable();
    }


    /**
     * 更新 / 保存菜单
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> addOrUpdate(HttpServletRequest request) {
        Map<String, Object> attrs = getNotBlankRequestMap(request);
        sysMenu.saveOrUpdate(attrs);
        return retSuccess();
    }

    /**
     * 删除菜单
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Map<String, String> delete(@RequestParam(required=true) String id) {
        sysMenu.deleteById(id);
        return retSuccess();
    }



}
