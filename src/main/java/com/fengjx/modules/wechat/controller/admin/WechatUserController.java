
package com.fengjx.modules.wechat.controller.admin;

import com.fengjx.commons.utils.WebUtil;
import com.fengjx.modules.common.controller.MyController;
import com.fengjx.modules.wechat.service.WechatUserGroupService;
import com.fengjx.modules.wechat.service.WechatUserInfoService;
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
    private WechatUserInfoService wechatUser;
    @Autowired
    private WechatUserGroupService userGroupService;

    @RequestMapping(value = "")
    public String view() {
        return "/wechat/admin/user";
    }

    @RequestMapping(value = "/groupList")
    @ResponseBody
    public List<Map<String, Object>> groupList(HttpServletRequest request) {
        return userGroupService.list(getLoginSysUserId());
    }

    @RequestMapping(value = "/userPageList")
    @ResponseBody
    public Object userList(HttpServletRequest request) {
        return wechatUser.pageList(WebUtil.getRequestParams(request), getLoginSysUserId());
    }

    /**
     * 保存粉丝分组
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/saveGroup")
    @ResponseBody
    public String saveGroup(HttpServletRequest request) {
        Map<String, Object> attrs = getRequestMap(request);
        attrs.put("in_time", new Date());
        attrs.put("user_id", getLoginSysUserId());
        userGroupService.insertOrUpdate(attrs);
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
    public String deleteGroup(String id) {
        userGroupService.deleteById(id);
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
    public String saveUser(HttpServletRequest request) {
        wechatUser.update(getRequestMap(request));
        return retSuccess();
    }

}
