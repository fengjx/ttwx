
package com.fengjx.ttwx.modules.wechat.controller.admin;

import com.fengjx.ttwx.common.utils.JsonUtil;
import com.fengjx.ttwx.modules.common.controller.MyController;
import com.fengjx.ttwx.modules.wechat.model.WechatMenu;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

/**
 * 菜单管理
 *
 * @author fengjx.
 * @date：2015/6/9 0009
 */
@Controller
@RequestMapping("/admin/wechat/menu")
public class WechatMenuController extends MyController {

    @Autowired
    private WechatMenu wechatMenu;

    /**
     * 菜单管理界面
     * 
     * @return
     */
    @RequestMapping(value = "")
    public String view() {
        return "/wechat/admin/menu";
    }

    /**
     * 加载树形菜单
     * 
     * @param request
     * @return
     */
    @RequestMapping(value = "/load")
    @ResponseBody
    public List<Map<String, Object>> load(HttpServletRequest request) {
        List<Map<String, Object>> tree = wechatMenu.treeMenu(getLoginSysUserId(request));
        return tree;
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
        attrs.put("user_id", getLoginSysUserId(request));
        wechatMenu.saveOrUpdate(attrs);
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
    public Map<String, String> delete(String id) {
        wechatMenu.deleteMenu(id);
        return retSuccess();
    }

    /**
     * 发布菜单
     * 
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/release")
    @ResponseBody
    public Map<String, String> release(HttpServletRequest request) {
        wechatMenu.release(getLoginSysUserId(request));
        return retSuccess();
    }

    @RequestMapping(value = "/sort")
    @ResponseBody
    public Map<String, String> sort(HttpServletRequest request, String sortStr) {
        List<Map<String, Object>> sortJson = StringUtils.isNotBlank(sortStr) ? JsonUtil
                .parseJSON2List(sortStr) : null;
        wechatMenu.sort(sortJson, getLoginSysUserId(request));
        return retSuccess();
    }

}
