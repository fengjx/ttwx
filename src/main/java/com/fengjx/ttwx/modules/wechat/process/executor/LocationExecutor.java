
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
 * 地理位置消息处理器
 * 
 * @author fengjx xd-fjx@qq.com
 * @date 2014年9月11日
 */
public class LocationExecutor extends BaseServiceExecutor {

    private static final Logger LOG = LoggerFactory.getLogger(LocationExecutor.class);

    @Override
    public WxMpXmlOutMessage execute(WxMpXmlMessage inMessage, Record accountRecord,
            WxMpConfigStorage wxMpConfig) {
        LogUtil.info(LOG, "进入地理位置消息处理器fromUserName=" + inMessage.getFromUserName());
        return doAction(WxConsts.XML_MSG_LOCATION, null, null, accountRecord.getStr("sys_user_id"));
    }

    @Override
    public String getExecutorName() {
        return ExecutorNameUtil.buildName(WxConsts.XML_MSG_LOCATION, null);
    }

}
