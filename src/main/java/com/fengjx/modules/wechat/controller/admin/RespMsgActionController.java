
package com.fengjx.modules.wechat.controller.admin;

import com.fengjx.commons.utils.WebUtil;
import com.fengjx.modules.common.controller.MyController;
import com.fengjx.modules.wechat.service.WechatMaterialService;
import com.fengjx.modules.wechat.service.WechatMenuService;
import com.fengjx.modules.wechat.service.WechatRespMsgActionService;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.mp.bean.WxMpXmlOutMessage;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * 消息动作规则
 *
 * @author fengjx. @date：2015/6/5 0005
 */
@Controller
@RequestMapping("${adminPath}/wechat/action")
public class RespMsgActionController extends MyController {

    @Autowired
    private WechatRespMsgActionService msgActionService;

    @Autowired
    private WechatMenuService menuService;

    @Autowired
    private WechatMaterialService materialService;

    /**
     * 关键字回复界面
     * 
     * @return
     */
    @RequestMapping(value = "/keyword")
    public String keyword() {
        return "/wechat/admin/keyword_action";
    }

    /**
     * 添加关键字回复界面
     *
     * @return
     */
    @RequestMapping(value = "/keywordAdd")
    public ModelAndView keywordAdd(String id) {
        ModelAndView mv = new ModelAndView("/wechat/admin/keyword_action_add");
        mv.addObject("id", id);
        return mv;
    }

    /**
     * 默认消息回复界面
     * 
     * @return
     */
    @RequestMapping(value = "/default")
    public String defaultAction() {
        return "/wechat/admin/default_action";
    }

    /**
     * 粉丝关注回复界面
     * 
     * @return
     */
    @RequestMapping(value = "/subscribe")
    public String subscribe() {
        return "/wechat/admin/subscribe_action";
    }

    /**
     * LBS地理位置消息回复
     * 
     * @return
     */
    @RequestMapping(value = "/lbs")
    public String lbs() {
        return "/wechat/admin/lbs_action";
    }

    /**
     * 根据ID读取动作规则
     *
     * @return
     */
    @RequestMapping(value = "/load")
    @ResponseBody
    public String load(HttpServletRequest request) {
        String id = request.getParameter("id");
        if (StringUtils.isNotBlank(id)) {
            return msgActionService.loadDetailById(id, getLoginSysUserId()).toJson();
        }
        String ext_type = request.getParameter("ext_type");
        String req_type = request.getParameter("req_type");
        String event_type = request.getParameter("event_type");
        String key_word = request.getParameter("key_word");
        return msgActionService
                .loadMsgAction(ext_type, req_type, event_type, key_word, getLoginSysUserId())
                .toJson();
    }

    /**
     * 分页查询
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/pageList")
    @ResponseBody
    public Object pageList(HttpServletRequest request) {
        return msgActionService.pageMsgAction(WebUtil.getNotBlankRequestParams(request),
                getLoginSysUserId());
    }

    /**
     * 保存消息响应规则
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public String save(HttpServletRequest request) {
        Map<String, Object> reqMap = getNotBlankRequestMap(request);
        Map<String, Object> materialMap = getMaterial(reqMap);

        String userId = getLoginSysUserId();
        materialMap.put("user_id", userId);
        reqMap.put("user_id", userId);
        if (StringUtils.isNotBlank((String) reqMap.get("id"))) {
            msgActionService.updateAction(reqMap, getWechatMenu(reqMap), materialMap);
        } else {
            msgActionService.saveAction(reqMap, getWechatMenu(reqMap), materialMap);
        }
        return retSuccess();
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public String delete(String id, HttpServletRequest request) {
        msgActionService.deleteMsgActionById(id, getLoginSysUserId());
        return retSuccess();
    }

    /**
     * 从请求参数中获得菜单对象
     * 
     * @param reqMap
     * @return
     */
    private Map<String, Object> getWechatMenu(Map<String, Object> reqMap) {
        Map<String, Object> menuMap = new HashMap<>();
        if (MapUtils.isNotEmpty(reqMap) && StringUtils.isNotBlank((String) reqMap.get("menuId"))) {
            menuMap = menuService.findById(reqMap.get("menuId"))._getColumns();
            menuMap.put("type", reqMap.get("menuType"));
            menuMap.put("url", reqMap.get("menuUrl"));
        }
        return menuMap;
    }

    /**
     * 从请求参数中获得素材对象
     * 
     * @param reqMap
     * @return
     */
    private Map<String, Object> getMaterial(Map<String, Object> reqMap) {
        Map<String, Object> materialMap = new HashMap<>();
        if (MapUtils.isEmpty(reqMap)) {
            return materialMap;
        }
        if (StringUtils.isNotBlank((String) reqMap.get("material_id"))) {
            materialMap = materialService.findById(reqMap.get("material_id"))._getColumns();
        }
        if (StringUtils.isNotBlank((String) reqMap.get("materiaContent"))) {
            materialMap = new HashMap<>();
            materialMap.put("xml_data",
                    WxMpXmlOutMessage.TEXT().content((String) reqMap.get("materiaContent"))
                            .fromUser("").toUser("").build().toXml());
            materialMap.put("msg_type", WxConsts.XML_MSG_TEXT);
        }
        return materialMap;
    }

}
