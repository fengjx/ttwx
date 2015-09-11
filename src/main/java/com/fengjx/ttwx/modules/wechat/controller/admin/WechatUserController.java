
package com.fengjx.ttwx.modules.wechat.controller.admin;

import com.fengjx.ttwx.common.plugin.db.page.AdapterPage;
import com.fengjx.ttwx.common.utils.WebUtil;
import com.fengjx.ttwx.modules.common.controller.MyController;
import com.fengjx.ttwx.modules.wechat.model.WechatUser;
import com.fengjx.ttwx.modules.wechat.model.WechatUserGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 粉丝管理
 *
 * @author fengjx.
 * @date：2015/6/19 0019
 */
@Controller
@RequestMapping("${adminPath}/wechat/user")
public class WechatUserController extends MyController {

    @Autowired
    private WechatUser wechatUser;
    @Autowired
    private WechatUserGroup wechatUserGroup;

    @RequestMapping(value = "")
    public String view() {
        return "/wechat/admin/user";
    }

    @RequestMapping(value = "/groupList")
    @ResponseBody
    public List<Map<String, Object>> groupList(HttpServletRequest request) {
        return wechatUserGroup.list(getLoginSysUserId(request));
    }

    @RequestMapping(value = "/userPageList")
    @ResponseBody
    public AdapterPage userList(HttpServletRequest request) {
        return wechatUser.pageList(WebUtil.getRequestParams(request), getLoginSysUserId(request));
    }

    /**
     * 保存粉丝分组
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/saveGroup")
    @ResponseBody
    public Map<String, String> saveGroup(HttpServletRequest request) {
        Map<String, Object> attrs = getRequestMap(request);
        attrs.put("in_time", new Date());
        attrs.put("user_id", getLoginSysUserId(request));
        wechatUserGroup.insertOrUpdate(attrs);
        return retSuccess();
    }

    /**
     * 删除粉丝分组
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/deleteGroup")
    @ResponseBody
    public Map<String, String> deleteGroup(String id) {
        wechatUserGroup.deleteById(id);
        return retSuccess();
    }

    /**
     * 更新粉丝分组
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/updateUser")
    @ResponseBody
    public Map<String, String> saveUser(HttpServletRequest request) {
        wechatUser.update(getRequestMap(request));
        return retSuccess();
    }

}
