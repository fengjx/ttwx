
package com.fengjx.ttwx.modules.wechat.process.executor;

import com.fengjx.ttwx.common.plugin.db.Record;
import com.fengjx.ttwx.common.utils.LogUtil;
import com.fengjx.ttwx.modules.wechat.process.utils.ExecutorNameUtil;

import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.mp.api.WxMpConfigStorage;
import me.chanjar.weixin.mp.bean.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.WxMpXmlOutMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 菜单点击消息处理器
 *
 * @author fengjx
 * @date 2015-6-24
 */
public class MemuClickExecutor extends BaseServiceExecutor {

    private static final Logger LOG = LoggerFactory.getLogger(LocationExecutor.class);

    @Override
    public WxMpXmlOutMessage execute(WxMpXmlMessage inMessage, Record accountRecord,
            WxMpConfigStorage wxMpConfig) {
        LogUtil.info(LOG, "进入菜单点击消息处理器fromUserName=" + inMessage.getFromUserName());
        return doAction(WxConsts.XML_MSG_EVENT, WxConsts.EVT_CLICK, inMessage.getEventKey(),
                accountRecord.getStr("sys_user_id"));
    }

    @Override
    public String getExecutorName() {
        return ExecutorNameUtil.buildName(WxConsts.XML_MSG_EVENT, WxConsts.EVT_CLICK);
    }

}
