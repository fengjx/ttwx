
package com.fengjx.modules.wechat.process.executor;

import com.fengjx.commons.plugin.db.Record;
import com.fengjx.commons.utils.LogUtil;
import com.fengjx.modules.common.constants.MsgTemplateConstants;
import com.fengjx.modules.wechat.constants.WechatConst;
import me.chanjar.weixin.common.session.WxSession;
import me.chanjar.weixin.mp.api.WxMpConfigStorage;
import me.chanjar.weixin.mp.bean.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.WxMpXmlOutMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * 验证码消息处理器
 * 
 * @author fengjx
 * @date 2015-6-24
 */
public class ValidExecutor extends BaseServiceExecutor {

    private static final Logger LOG = LoggerFactory.getLogger(TextExecutor.class);
    public static final String EXECUTOR_NAME = "validExecutor";

    @Override
    public WxMpXmlOutMessage execute(WxMpXmlMessage inMessage, Record accountRecord,
            WxMpConfigStorage wxMpConfig, WxSession session) {
        LogUtil.info(LOG, "进入验证消息处理器fromUserName=" + inMessage.getFromUserName());

        String valid_code = accountRecord.getStr("valid_code");
        // 文字消息与验证码相同
        if (valid_code.equals(inMessage.getContent())) {
            Map<String, Object> attrs = accountRecord._getColumns();
            // 更新账号状态为激活
            attrs.put("valid_state", WechatConst.PublicAccount.VALID_STATE_ACTIVATE);
            attrs.put("account_id", inMessage.getToUserName());
            publicAccountService.update(attrs);
            return doAction(msgTemplateService
                    .getTemplateContentByKey(MsgTemplateConstants.API_VALID_SUCCESS));
        }
        return doAction(
                msgTemplateService.getTemplateContentByKey(MsgTemplateConstants.API_VALID_FAIL));
    }

    @Override
    public String getExecutorName() {
        return ValidExecutor.EXECUTOR_NAME;
    }

}
