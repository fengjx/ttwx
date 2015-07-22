
package com.fengjx.ttwx.modules.wechat.process.ext.impl;

import com.fengjx.ttwx.common.plugin.db.Record;
import com.fengjx.ttwx.common.utils.LogUtil;
import com.fengjx.ttwx.modules.api.restful.WeatherServiceApi;
import com.fengjx.ttwx.modules.wechat.process.ext.ExtService;

import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.session.WxSession;
import me.chanjar.weixin.mp.api.WxMpConfigStorage;
import me.chanjar.weixin.mp.bean.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.WxMpXmlOutTextMessage;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * 天气查询接口
 *
 * @author fengjx.
 * @date：2015/7/20 0020
 */
@Service("weather")
public class WeatherExtService implements ExtService {

    private static final Logger LOG = LoggerFactory.getLogger(WeatherExtService.class);

    /**
     * @param inMessage 微信消息
     * @param accountRecord 公众平台信息
     * @param wxMpConfig 公众平台配置
     * @param session 微信session
     * @return
     */
    @Override
    public String execute(WxMpXmlMessage inMessage, Record accountRecord,
            WxMpConfigStorage wxMpConfig, WxSession session) {
        if (!WxConsts.XML_MSG_LOCATION.equals(inMessage.getMsgType())) {
            throw new RuntimeException("请求的消息类型不支持该接口");
        }
        // 接收用户发送的文本消息内容
        Double Location_X = inMessage.getLocationX();
        Double Location_Y = inMessage.getLocationY();
        String label = inMessage.getLabel();
        String keyWord = Location_Y + "," + Location_X;
        LogUtil.info(LOG, "天气查询：" + keyWord + ", label=" + label);

        WxMpXmlOutTextMessage txt = new WxMpXmlOutTextMessage();
        txt.setContent(WeatherServiceApi.queryhWeather(keyWord));
        txt.setCreateTime(System.currentTimeMillis());
        txt.setFromUserName("");
        txt.setToUserName("");
        return txt.toXml();
    }
}
