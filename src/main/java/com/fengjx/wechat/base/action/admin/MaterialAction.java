
package com.fengjx.wechat.base.action.admin;

import com.fengjx.common.mybatis.PageWapper;
import com.fengjx.common.utils.JsonUtil;
import com.fengjx.common.web.action.BaseController;
import com.fengjx.wechat.base.model.Material;
import com.fengjx.wechat.base.model.SysUser;
import com.fengjx.wechat.base.service.MaterialService;

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

@Controller
@RequestMapping("/admin/base/material")
public class MaterialAction extends BaseController
{

    @Autowired
    private MaterialService materialService;

    @RequestMapping("")
    public String view() {
        return "/wechat/admin/base/material";
    }

    @RequestMapping("/multiple")
    public ModelAndView multiple(String id) {
        ModelAndView mv = new ModelAndView("/wechat/admin/material/multiple_news");
        mv.addObject("id", id);
        return mv;
    }

    @RequestMapping("/single")
    public ModelAndView single(String id) {
        ModelAndView mv = new ModelAndView("/wechat/admin/material/single_news");
        mv.addObject("id", id);
        return mv;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> addOrUpdate(HttpServletRequest request, Material material,
            String contentsJson) throws Exception {
        setErrorMsg(request, "素材编辑失败");
        SysUser sysUser = getLoginSysUser(request);
        material.setUserId(sysUser.getId());
        List contents = (StringUtils.isNotBlank(contentsJson)) ? JsonUtil
                .parseJSON2List(contentsJson) : null;

        this.materialService.saveOrUpdate(material, contents);
        return retSuccess();
    }

    @RequestMapping("/page")
    @ResponseBody
    public PageWapper<Material> page(HttpServletRequest request, String msg_type) throws Exception {
        PageWapper page = this.materialService
                .getListPageByType(msg_type, getLoginSysUser(request));
        return page;
    }

    @RequestMapping("/load")
    @ResponseBody
    public Material load(String id) throws Exception {
        Material entity = this.materialService.selectByPrimaryKey(id);
        return entity;
    }

    @RequestMapping("/delete")
    @ResponseBody
    public Map<String, String> delete(HttpServletRequest request, String id) throws Exception {
        setErrorMsg(request, "删除失败");
        this.materialService.deleteByPrimaryKey(id);
        return retSuccess();
    }
}
