
package com.fengjx.modules.sys.controller.admin;

import com.fengjx.commons.plugin.cache.ehcache.EhCacheUtil;
import com.fengjx.commons.plugin.db.ParamHelper;
import com.fengjx.commons.plugin.db.page.AdapterPage;
import com.fengjx.modules.sys.model.Dict;
import com.fengjx.modules.common.constants.AppConfig;
import com.fengjx.modules.common.controller.MyController;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Map;

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
    private Dict dict;

    @RequestMapping(value = "")
    public ModelAndView view() {
        ModelAndView mv = new ModelAndView("sys/admin/dict");
        return mv;
    }

    @RequestMapping("pageList")
    @ResponseBody
    public AdapterPage pageList(HttpServletRequest request) {
        return dict.page(getParamHelper(Dict.class, request));
    }

    @RequestMapping("save")
    @ResponseBody
    public Map<String, String> save(HttpServletRequest request) {
        ParamHelper paramHelper = getParamHelper(request);
        paramHelper.set("in_time", new Date());
        if (StringUtils.isBlank(paramHelper.getStr("is_valid"))) {
            paramHelper.set("is_valid", 0);
        }
        dict.insertOrUpdate(paramHelper.getParams());
        EhCacheUtil.removeAll(AppConfig.EhcacheName.DICT_CACHE);
        return retSuccess();
    }

    @RequestMapping("delete")
    @ResponseBody
    public Map<String, String> delete(String id) {
        dict.deleteById(id);
        EhCacheUtil.removeAll(AppConfig.EhcacheName.DICT_CACHE);
        return retSuccess();
    }
}
