
package com.fengjx.ttwx.modules.wechat.controller.admin;

import com.fengjx.ttwx.common.utils.WebUtil;
import com.fengjx.ttwx.modules.common.controller.MyController;
import com.fengjx.ttwx.modules.wechat.model.Material;
import com.fengjx.ttwx.modules.wechat.model.RespMsgAction;
import com.fengjx.ttwx.modules.wechat.model.WechatMenu;
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
 * @author fengjx.
 * @date：2015/6/5 0005
 */
@Controller
@RequestMapping("/admin/wechat/action")
public class RespMsgActionController extends MyController {

    @Autowired
    private RespMsgAction respMsgAction;

    @Autowired
    private WechatMenu wechatMenu;

    @Autowired
    private Material material;

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
            return respMsgAction.loadDetailById(id, getLoginSysUserId(request)).toJson();
        }
        String ext_type = request.getParameter("ext_type");
        String req_type = request.getParameter("req_type");
        String event_type = request.getParameter("event_type");
        String key_word = request.getParameter("key_word");
        return respMsgAction.loadMsgAction(ext_type, req_type, event_type, key_word,
                getLoginSysUserId(request)).toJson();
    }

    /**
     * 分页查询
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/pageList")
    @ResponseBody
    public String pageList(HttpServletRequest request) {
        return respMsgAction.pageMsgAction(WebUtil.getNotBlankRequestParams(request),
                getLoginSysUserId(request)).toJson();
    }

    /**
     * 保存消息响应规则
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> save(HttpServletRequest request) {
        Map<String, Object> reqMap = getRequestMap(request);
        Map<String, Object> materialMap = getMaterial(reqMap);

        String userId = getLoginSysUserId(request);
        materialMap.put("user_id", userId);
        reqMap.put("user_id", userId);
        if (StringUtils.isNotBlank((String) reqMap.get("id"))) {
            respMsgAction.updateAction(reqMap, getWechatMenu(reqMap), materialMap);
        } else {
            respMsgAction.saveAction(reqMap, getWechatMenu(reqMap), materialMap);
        }
        return retSuccess();
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> delete(String id) {
        respMsgAction.deleteById(id);
        return retSuccess();
    }

    /**
     * 从请求参数中获得菜单对象
     * 
     * @param reqMap
     * @return
     */
    private Map<String, Object> getWechatMenu(Map<String, Object> reqMap) {
        Map<String, Object> menuMap = new HashMap();
        if (MapUtils.isNotEmpty(reqMap) && StringUtils.isNotBlank((String) reqMap.get("menuId"))) {
            menuMap = wechatMenu.findById(reqMap.get("menuId")).getColumns();
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
        Map<String, Object> materialMap = new HashMap();
        if (MapUtils.isEmpty(reqMap)) {
            return materialMap;
        }
        if (StringUtils.isNotBlank((String) reqMap.get("material_id"))) {
            materialMap = material.findById(reqMap.get("material_id")).getColumns();
        }
        if (StringUtils.isNotBlank((String) reqMap.get("materiaContent"))) {
            materialMap = new HashMap();
            materialMap.put("xml_data",
                    WxMpXmlOutMessage.TEXT().content((String) reqMap.get("materiaContent"))
                            .fromUser("")
                            .toUser("").build().toXml());
            materialMap.put("msg_type", WxConsts.XML_MSG_TEXT);
        }
        return materialMap;
    }

}
