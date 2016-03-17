
package com.fengjx.modules.wechat.process.executor;

import com.fengjx.commons.plugin.db.Record;
import com.fengjx.commons.utils.LogUtil;

import me.chanjar.weixin.common.session.WxSession;
import me.chanjar.weixin.mp.api.WxMpConfigStorage;
import me.chanjar.weixin.mp.bean.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.WxMpXmlOutMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 默认消息处理器，在没有找到对应处理器时执行
 * 
 * @author fengjx
 * @date 2015-6-24
 */
public class DefaultExecutor extends BaseServiceExecutor {

    public static final String EXECUTOR_NAME = "DefaultExecutor";

    private static final Logger LOG = LoggerFactory.getLogger(DefaultExecutor.class);

    @Override
    public WxMpXmlOutMessage execute(WxMpXmlMessage inMessage, Record accountRecord,
            WxMpConfigStorage wxMpConfig, WxSession session) {
        LogUtil.info(LOG, "进入默认消息处理器fromUserName=" + inMessage.getFromUserName());
        return null;
    }

    @Override
    public String getExecutorName() {
        return DefaultExecutor.EXECUTOR_NAME;
    }

}
