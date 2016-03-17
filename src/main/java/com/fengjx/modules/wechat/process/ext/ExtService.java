
package com.fengjx.modules.wechat.process.ext;

import com.fengjx.commons.plugin.db.Record;

import me.chanjar.weixin.common.session.WxSession;
import me.chanjar.weixin.mp.api.WxMpConfigStorage;
import me.chanjar.weixin.mp.bean.WxMpXmlMessage;

/**
 * 业务扩展接口
 *
 * @author fengjx.
 * @date：2015/6/23 0023
 */
public interface ExtService {

    /**
     * @param inMessage 微信消息
     * @param accountRecord 公众平台信息
     * @param wxMpConfig 公众平台配置
     * @param session 微信session
     * @return
     */
    String execute(WxMpXmlMessage inMessage, Record accountRecord,
            WxMpConfigStorage wxMpConfig, WxSession session);

}
