
package com.fengjx.ttwx.modules.wechat.controler.admin;

import com.fengjx.ttwx.common.utils.JsonUtil;
import com.fengjx.ttwx.modules.common.controler.MyController;
import com.fengjx.ttwx.modules.wechat.bean.SysUserEntity;
import com.fengjx.ttwx.modules.wechat.model.Material;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 素材管理
 *
 * @author fengjx.
 * @date：2015/5/28 0028
 */
@Controller
@RequestMapping("/admin/wechat/material")
public class MaterialController extends MyController {

    @Autowired
    private Material material;

    @RequestMapping(value = "")
    public ModelAndView view(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView("wechat/admin/material");
        return mv;
    }

    @RequestMapping("/multiple")
    public ModelAndView multiple(String id) {
        ModelAndView mv = new ModelAndView("/wechat/admin/multiple_news");
        mv.addObject("id", id);
        return mv;
    }

    @RequestMapping("/single")
    public ModelAndView single(String id) {
        ModelAndView mv = new ModelAndView("/wechat/admin/single_news");
        mv.addObject("id", id);
        return mv;
    }

    @RequestMapping("/page")
    @ResponseBody
    public String pageList(HttpServletRequest request, String msg_type, int pageNumber, int pageSize) {
        SysUserEntity sysUser = getLoginSysUser(request);
        return material.getListPageByType(pageNumber, pageSize, msg_type, sysUser.getId()).toJson();
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> addOrUpdate(HttpServletRequest request, String contentsJson) {
        if (StringUtils.isBlank(contentsJson)) {
            return retFail("请求数据不完整，参数contentsJson为空！");
        }
        SysUserEntity sysUser = getLoginSysUser(request);
        List<Map<String, Object>> contents = StringUtils.isNotBlank(contentsJson) ? JsonUtil
                .parseJSON2List(contentsJson) : null;
        material.saveOrUpdate(getRequestMap(request), contents, sysUser.getId());
        return retSuccess();
    }

    @RequestMapping("/load")
    @ResponseBody
    public String load(String id) {
        return material.findById(id).toJson();
    }

    @RequestMapping(value = "/getContent",produces="text/html;charset=UTF-8")
    @ResponseBody
    public String loadMaterialContentByUrl(HttpServletResponse response, String url) {
        return material.loadMaterialContentByUrl(url);
    }

}
