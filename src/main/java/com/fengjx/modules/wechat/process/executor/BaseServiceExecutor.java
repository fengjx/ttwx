
package com.fengjx.modules.wechat.process.executor;

import com.fengjx.commons.plugin.db.Record;
import com.fengjx.commons.system.init.SpringBeanFactoryUtil;
import com.fengjx.commons.utils.LogUtil;
import com.fengjx.modules.common.constants.MsgTemplateConstants;
import com.fengjx.modules.wechat.bean.WechatRespMsgAction;
import com.fengjx.modules.wechat.process.ServiceExecutor;
import com.fengjx.modules.wechat.process.ServiceExecutorNameWire;
import com.fengjx.modules.wechat.process.bean.WechatContext;
import com.fengjx.modules.wechat.process.ext.ExtService;
import com.fengjx.modules.wechat.process.utils.MessageUtil;
import com.fengjx.modules.wechat.process.utils.WxMpUtil;
import com.fengjx.modules.wechat.service.WechatMsgTemplateService;
import com.fengjx.modules.wechat.service.WechatPublicAccountService;
import com.fengjx.modules.wechat.service.WechatRespMsgActionService;
import me.chanjar.weixin.mp.bean.WxMpXmlOutMessage;
import me.chanjar.weixin.mp.util.xml.XStreamTransformer;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

/**
 * 业务执行器基类
 */
public abstract class BaseServiceExecutor implements ServiceExecutor, ServiceExecutorNameWire {

    private static final Logger LOG = LoggerFactory.getLogger(BaseServiceExecutor.class);

    @Autowired
    protected WechatRespMsgActionService msgActionService;
    @Autowired
    protected WechatPublicAccountService publicAccountService;
    @Autowired
    protected WechatMsgTemplateService msgTemplateService;

    /**
     * 执行消息动作
     * 
     * @param req_type 请求类型
     * @param event_type 事件类型
     * @param key_word 关键字/key
     * @param userId
     */
    protected WxMpXmlOutMessage doAction(String req_type, String event_type, String key_word,
            String userId) {
        Record actionRecord = msgActionService.loadMsgAction(null, req_type,
                event_type, key_word, userId);
        // 没有找到匹配规则
        if (actionRecord.isEmpty()) {
            // 返回默认回复消息
            actionRecord = msgActionService.loadMsgAction(MsgTemplateConstants.WECHAT_DEFAULT_MSG,
                    null, null, null, userId);
        }
        return doAction(actionRecord);
    }

    /**
     * 执行消息动作
     *
     * @param actionRecord
     * @return
     */
    protected WxMpXmlOutMessage doAction(Record actionRecord) {
        // 没有匹配到消息则返回空字符串，不做响应
        if (null == actionRecord || actionRecord.isEmpty()) {
            // return FreeMarkerUtil.process(null,
            // FtlFilenameConstants.WECHAT_DEFAULT_MSG);
            return null;
        }
        String res = null;
        String actionType = actionRecord.getStr("action_type");
        if (WechatRespMsgAction.ACTION_TYPE_MATERIAL.equals(actionType)) { // 从素材取数据
            res = actionRecord.getStr("xml_data");
        } else if (WechatRespMsgAction.ACTION_TYPE_API.equals(actionType)) { // 从接口返回数据
            res = busiappHandle(actionRecord.getStr("bean_name"));
        }
        return doAction(res);
    }

    /**
     * 执行消息动作
     * 
     * @param xmlMsg
     * @return
     */
    protected WxMpXmlOutMessage doAction(String xmlMsg) {
        if (StringUtils.isBlank(xmlMsg)) {
            return null;
        }
        xmlMsg = xmlMsg.replaceAll("\\<CreateTime>(.*?)\\</CreateTime>",
                "<CreateTime><![CDATA[" + new Date().getTime() + "]]></CreateTime>");
        return (WxMpXmlOutMessage) XStreamTransformer.fromXml(
                WxMpUtil.getXmlOutMsgType(MessageUtil.parseMsgType(xmlMsg)), xmlMsg);
        // 替换参数
        // respMessage = MessageUtil.replaceMsgByReg(respMessage,
        // WechatContext.getWechatPostMap());
        // return respMessage;
    }

    /**
     * 业务扩展处理
     *
     * @param beanName
     * @return
     */
    protected String busiappHandle(String beanName) {
        // 从spring中拿到业务bean
        ExtService ext = SpringBeanFactoryUtil.getBean(beanName);
        String res = ext.execute(WechatContext.getInMessage(), WechatContext.getInMessageRecord(),
                WechatContext.getWxMpConfigStorage(), WechatContext.getWxsession());
        LogUtil.debug(LOG, "beanName：" + beanName + " execute");
        return res;
    }

}
