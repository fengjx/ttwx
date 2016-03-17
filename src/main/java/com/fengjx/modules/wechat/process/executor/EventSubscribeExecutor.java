
package com.fengjx.modules.wechat.process.executor;

import com.fengjx.commons.plugin.db.Record;
import com.fengjx.commons.utils.CommonUtils;
import com.fengjx.commons.utils.LogUtil;
import com.fengjx.modules.wechat.process.utils.ExecutorNameUtil;
import com.fengjx.modules.wechat.service.WechatUserInfoService;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.session.WxSession;
import me.chanjar.weixin.mp.api.WxMpConfigStorage;
import me.chanjar.weixin.mp.bean.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.WxMpXmlOutMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 用户关注消息处理器
 * 
 * @author fengjx xd-fjx@qq.com
 * @date 2014年9月11日
 */
public class EventSubscribeExecutor extends BaseServiceExecutor {

    private static final Logger LOG = LoggerFactory.getLogger(EventSubscribeExecutor.class);

    @Autowired
    private WechatUserInfoService userInfoService;

    @Override
    public WxMpXmlOutMessage execute(WxMpXmlMessage inMessage, Record accountRecord,
            WxMpConfigStorage wxMpConfig, WxSession session) {
        LogUtil.info(LOG, "进入用户关注消息处理器fromUserName=" + inMessage.getFromUserName());
        Map<String, Object> attrs = new HashMap<>();
        attrs.put("id", CommonUtils.getPrimaryKey());
        attrs.put("openid", inMessage.getFromUserName());
        attrs.put("subscribe_time", new Date());
        attrs.put("public_account_id", accountRecord.getStr("id"));
        userInfoService.insert(attrs);
        return doAction(WxConsts.XML_MSG_EVENT, WxConsts.EVT_SUBSCRIBE, null,
                accountRecord.getStr("sys_user_id"));
    }

    @Override
    public String getExecutorName() {
        return ExecutorNameUtil.buildName(WxConsts.XML_MSG_EVENT, WxConsts.EVT_SUBSCRIBE);
    }

}
