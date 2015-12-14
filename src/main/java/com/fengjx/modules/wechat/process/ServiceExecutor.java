
package com.fengjx.modules.wechat.process;

import com.fengjx.commons.plugin.db.Record;

import me.chanjar.weixin.common.session.WxSession;
import me.chanjar.weixin.mp.api.WxMpConfigStorage;
import me.chanjar.weixin.mp.bean.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.WxMpXmlOutMessage;

/**
 * 服务执行器
 */
public interface ServiceExecutor {

    /**
     * 业务执行方法
     *
     * @param inMessage
     * @param accountRecord
     * @param wxMpConfig
     * @param session
     * @return
     */
    WxMpXmlOutMessage execute(WxMpXmlMessage inMessage, Record accountRecord,
            WxMpConfigStorage wxMpConfig, WxSession session);

}
