
package com.fjx.wechat.base.admin.action;

import com.fjx.common.action.BaseController;
import com.fjx.common.utils.PasswordUtil;
import com.fjx.wechat.base.admin.entity.WechatPublicAccountEntity;
import com.fjx.wechat.base.admin.service.WechatPublicAccountService;
import com.fjx.wechat.mysdk.context.WechatContext;
import com.fjx.wechat.mysdk.process.in.InServiceEngine;
import com.fjx.wechat.mysdk.tools.SignUtil;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 微信请求网关
 * 
 * @author fengjx
 * @date
 */
@Controller
public class WechatApiAction extends BaseController {

    @Autowired
    private InServiceEngine inServiceEngine;
    @Autowired
    private WechatPublicAccountService wechatPublicAccountService;

    /**
     * 接口认证
     * 
     * @param signature 微信加密签名
     * @param timestamp 时间戳
     * @param nonce 随机数
     * @param echostr 随机字符串
     */
    @RequestMapping(value = "/wechat/api", method = RequestMethod.GET)
    @ResponseBody
    public String valid(String signature, String timestamp, String nonce, String echostr,
            String ticket) {
        logger.info("微信接口认证请求[signature=" + signature + " timestamp=" + timestamp + " nonce="
                + nonce + " echostr=" + echostr + " ticket=" + ticket + "]");
        // 通过检验signature对请求进行校验，若校验成功则原样返回echostr，表示接入成功，否则接入失败
        String _ticket = PasswordUtil.decode(ticket);
        String token = null;
        if (StringUtils.isNotBlank(_ticket)) {
            WechatPublicAccountEntity accountEntity = wechatPublicAccountService
                    .getWechatPublicAccountByTicket(_ticket);
            if (null != accountEntity) {
                token = accountEntity.getToken();
                if (SignUtil.checkSignature(token, signature, timestamp, nonce)) {
                    // 更新设置状态
                    accountEntity.setValid_state(WechatPublicAccountEntity.VALID_STATE_EXCESS);
                    wechatPublicAccountService.update(accountEntity);
                    return echostr;
                }
            }
        }
        return "error for valid--> signature=" + signature + " timestamp=" + timestamp + " nonce="
                + nonce + " echostr=" + echostr + " myToken=" + token;
    }

    /**
     * 消息处理
     * 
     * @throws Exception
     */
    @RequestMapping(value = "/wechat/api", method = RequestMethod.POST)
    public void responseMsg(HttpServletRequest request, HttpServletResponse response, String ticket)
            throws Exception {
        logger.info("微信POST消息请求处理");
        if (StringUtils.isBlank(ticket)) {
            logger.info("请求无效，ticket为空");
            write("", response);
            return;
        }
        String _ticket = PasswordUtil.decode(ticket);
        WechatPublicAccountEntity accountEntity = wechatPublicAccountService
                .getWechatPublicAccountByTicket(_ticket);
        if (null == accountEntity) {
            logger.info("请求无效，accountEntity为空");
            write("", response);
            return;
        }
        // 将参数封装到Threadlocal作为上下文调用
        WechatContext.setPublicAccount(accountEntity);
        // 调用核心业务类接收消息、处理消息
        String respMessage = inServiceEngine.processRequest();
        logger.info("微信POST消息请求处理响应==>\n" + respMessage);
        write(respMessage, response);
    }

}
