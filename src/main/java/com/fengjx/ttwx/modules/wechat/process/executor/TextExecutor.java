
package com.fengjx.ttwx.modules.wechat.process.executor;

import com.fengjx.ttwx.common.plugin.db.Record;
import com.fengjx.ttwx.common.utils.LogUtil;
import com.fengjx.ttwx.modules.wechat.process.utils.ExecutorNameUtil;

import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.mp.api.WxMpConfigStorage;
import me.chanjar.weixin.mp.bean.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.WxMpXmlOutMessage;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 文本消息处理器
 *
 * @author fengjx
 * @date 2015-6-24
 */
public class TextExecutor extends BaseServiceExecutor {

    private static final Logger LOG = LoggerFactory.getLogger(TextExecutor.class);

    @Override
    public WxMpXmlOutMessage execute(WxMpXmlMessage inMessage, Record accountRecord,
            WxMpConfigStorage wxMpConfig) {
        LogUtil.info(LOG, "进入文本消息处理器fromUserName=" + inMessage.getFromUserName());
        Record actionRecord = respMsgAction.loadMsgAction(null, WxConsts.XML_MSG_TEXT, null,
                inMessage.getContent(), accountRecord.getStr("sys_user_id"));
        // 没有找到匹配规则
        if (null == actionRecord || actionRecord.isEmpty()) {
            String res = tulingHandel(inMessage);
            if (StringUtils.isNotBlank(res)) { // 如果有数据则直接返回
                return doAction(res);
            }
        }
        return doAction(actionRecord);
    }

    @Override
    public String getExecutorName() {
        return ExecutorNameUtil.buildName(WxConsts.XML_MSG_TEXT, null);
    }

    public String tulingHandel(WxMpXmlMessage inMessage) {
        return null;
    }
}
