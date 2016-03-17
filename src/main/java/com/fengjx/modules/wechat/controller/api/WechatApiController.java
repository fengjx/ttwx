
package com.fengjx.modules.wechat.controller.api;

import com.fengjx.commons.utils.LogUtil;
import com.fengjx.modules.common.controller.MyController;
import com.fengjx.modules.wechat.constants.WechatConst;
import com.fengjx.modules.wechat.process.ServiceEngine;
import com.fengjx.modules.wechat.process.bean.WechatContext;
import com.fengjx.modules.wechat.service.WechatPublicAccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 微信消息请求网关
 *
 * @author fengjx. @date：2015/6/21 0021
 */
@Controller
public class WechatApiController extends MyController {

    private static final Logger LOG = LoggerFactory.getLogger(WechatApiController.class);

    @Autowired
    private ServiceEngine serviceEngine;

    @Autowired
    private WechatPublicAccountService publicAccountService;

    /**
     * 接口认证
     */
    @RequestMapping(value = "${apiPath}", method = RequestMethod.GET)
    @ResponseBody
    public String valid(HttpServletRequest request) {
        // 拦截器里已经做了消息签名校验，这里直接返回就可以了
        Map<String, Object> attrs = WechatContext.getInMessageRecord()._getColumns();
        attrs.put("valid_state", WechatConst.PublicAccount.VALID_STATE_EXCESS);
        // 更新接口为已接入状态
        publicAccountService.update(attrs);
        return request.getParameter("echostr");
    }

    /**
     * 消息处理
     *
     * @throws Exception
     */
    @RequestMapping(value = "${apiPath}", produces = "text/javascript;charset=UTF-8", method = RequestMethod.POST)
    @ResponseBody
    public String responseMsg(HttpServletRequest request) {
        String res = serviceEngine.processRequest();
        LogUtil.info(LOG, "响应数据\n" + res);
        return res;
    }

}
