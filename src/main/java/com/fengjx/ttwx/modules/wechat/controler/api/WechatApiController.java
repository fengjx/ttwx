
package com.fengjx.ttwx.modules.wechat.controler.api;

import com.fengjx.ttwx.common.utils.LogUtil;
import com.fengjx.ttwx.modules.common.controler.MyController;
import com.fengjx.ttwx.modules.wechat.process.ServiceEngine;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * 微信消息请求网关
 *
 * @author fengjx.
 * @date：2015/6/21 0021
 */
@Controller
public class WechatApiController extends MyController {

    private static final Logger LOG = LoggerFactory.getLogger(WechatApiController.class);

    @Autowired
    private ServiceEngine serviceEngine;

    /**
     * 接口认证
     */
    @RequestMapping(value = "/wechat/api", method = RequestMethod.GET)
    @ResponseBody
    public String valid(HttpServletRequest request) {
        // 拦截器里已经做了消息签名校验，这里直接返回就可以了
        return request.getParameter("echostr");
    }

    /**
     * 消息处理
     *
     * @throws Exception
     */
    @RequestMapping(value = "/wechat/api", produces = "text/html;charset=UTF-8", method = RequestMethod.POST)
    @ResponseBody
    public String responseMsg(HttpServletRequest request) {
        String res = serviceEngine.processRequest();
        LogUtil.info(LOG, "响应数据\n" + res);
        return res;
    }

}
