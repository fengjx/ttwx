
package com.fengjx.modules.wechat.controller.admin;

import com.fengjx.commons.plugin.db.Page;
import com.fengjx.commons.utils.JsonUtil;
import com.fengjx.modules.common.controller.MyController;
import com.fengjx.modules.sys.bean.SysUser;
import com.fengjx.modules.wechat.service.WechatMaterialService;
import me.chanjar.weixin.common.exception.WxErrorException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * 素材管理
 *
 * @author fengjx. @date：2015/5/28 0028
 */
@Controller
@RequestMapping("${adminPath}/wechat/material")
public class MaterialController extends MyController {

    @Autowired
    private WechatMaterialService materialService;

    @RequestMapping(value = "")
    public String view() {
        return "wechat/admin/material";
    }

    @RequestMapping("/multiple")
    public ModelAndView multiple(@RequestParam(value = "id", required = false) String id,
            @RequestParam(value = "fname", required = false) String fileName) {
        ModelAndView mv = new ModelAndView("/wechat/admin/multiple_news");
        mv.addObject("id", id);
        mv.addObject("fname", fileName);
        return mv;
    }

    @RequestMapping("/single")
    public ModelAndView single(@RequestParam(value = "id", required = false) String id,
            @RequestParam(value = "fname", required = false) String fileName) {
        ModelAndView mv = new ModelAndView("/wechat/admin/single_news");
        mv.addObject("id", id);
        mv.addObject("fname", fileName);
        return mv;
    }

    @RequestMapping("/page")
    @ResponseBody
    public Object pageList(HttpServletRequest request, String msg_type, int pageNumber,
            int pageSize) {
        Page<Map<String, Object>> page = materialService.getListPageByType(pageNumber, pageSize,
                msg_type, getLoginSysUserId());
        page.setConvert(false);
        return page;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public String addOrUpdate(HttpServletRequest request, String contentsJson) {
        SysUser sysUser = getLoginSysUser();
        List<Map<String, Object>> contents = StringUtils.isNotBlank(contentsJson)
                ? JsonUtil.parseJSON2List(contentsJson) : null;
        Map<String, Object> params = getRequestMap(request);
        String msgFlag = (String) params.get("msgFlag");

        if (msgFlag != null && msgFlag.equals("1")) {
            try {
                toSendMessage(sysUser, contents, params, true);
            } catch (WxErrorException e) {
                return retFail(e.getError().getErrorMsg());
            }
        } else {
            materialService.saveOrUpdate(params, contents, sysUser.getId());
            if ("2".equals(msgFlag)) {
                try {
                    toSendMessage(sysUser, contents, params, false);
                } catch (WxErrorException e) {
                    return retFail(e.getError().getErrorMsg());
                }
            }
        }
        return retSuccess();
    }

    private void toSendMessage(SysUser sysUser, List<Map<String, Object>> contents,
            Map<String, Object> params, boolean isPreview) throws WxErrorException {
        String msgType = (String) params.get("msg_type");
        String wxUserId = (String) params.get("wxUserId");
        if (null != msgType && msgType.equals("news")) { // 图文消息
            if (null != contents && contents.size() > 0) {
                String xml_data = (String) params.get("xml_data");
                if (isPreview) {
                    materialService.previewMsg(contents, xml_data, sysUser.getId(), wxUserId);
                } else {
                    materialService.sendGroupMsg(contents, xml_data, sysUser.getId());
                }
            }
        }
    }

    @RequestMapping("/load")
    @ResponseBody
    public String load(String id) {
        return materialService.findById(id).toJson();
    }

    @RequestMapping(value = "/getContent", produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String loadMaterialContentByUrl(HttpServletResponse response, String url) {
        return materialService.loadMaterialContentByUrl(url);
    }

    @RequestMapping(value = "/delete")
    @ResponseBody
    public String delete(String id) {
        materialService.deleteById(id);
        return retSuccess();
    }

}
