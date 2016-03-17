
package com.fengjx.modules.wechat.process;

import com.fengjx.commons.plugin.db.Record;
import com.fengjx.modules.wechat.constants.WechatConst;
import com.fengjx.modules.wechat.process.bean.WechatContext;
import com.fengjx.modules.wechat.process.executor.ValidExecutor;
import com.fengjx.modules.wechat.process.utils.ExecutorNameUtil;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.mp.bean.WxMpXmlMessage;
import org.apache.log4j.Logger;

/**
 * 业务动作分发器
 * 
 * @author fengjx xd-fjx@qq.com
 * @date 2014年9月8日
 */
public class Dispatcher {

    private static final Logger LOGGER = Logger.getLogger(Dispatcher.class);

    /**
     * 根据业务参数获取业务执行器
     *
     * @param inMessage
     * @return
     */
    public static String getExecutorName(WxMpXmlMessage inMessage) {
        // 消息类型
        String msgType = inMessage.getMsgType();
        Record accountRecord = WechatContext.getInMessageRecord();
        // 验证状态
        String valid_state = accountRecord.getStr("valid_state");
        if (WechatConst.PublicAccount.VALID_STATE_ACTIVATE.equals(valid_state)) {
            return ExecutorNameUtil.buildName(msgType, inMessage.getEvent());
        } else if (WechatConst.PublicAccount.VALID_STATE_EXCESS.equals(valid_state)
                && WxConsts.XML_MSG_TEXT.equals(msgType)) {// 消息类型是文本，当前账号状态是1，已配置URL到公众平台
            return ValidExecutor.EXECUTOR_NAME;
        }
        LOGGER.warn("接口未配置到公众平台，此消息不做处理");
        return null;
    }
}
