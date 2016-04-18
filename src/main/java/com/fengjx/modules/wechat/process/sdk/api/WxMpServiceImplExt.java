
package com.fengjx.modules.wechat.process.sdk.api;

import me.chanjar.weixin.common.bean.result.WxError;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.common.util.http.SimplePostRequestExecutor;
import me.chanjar.weixin.mp.api.WxMpServiceImpl;
import me.chanjar.weixin.mp.bean.WxMpMassOpenIdsMessage;
import me.chanjar.weixin.mp.bean.result.WxMpMassSendResult;

public class WxMpServiceImplExt extends WxMpServiceImpl implements WxMpServiceExt {

    @Override
    public WxMpMassSendResult massPreviewMessage(WxMpMassOpenIdsMessage message)
            throws WxErrorException {
        if (message.getToUsers().size() > 1) {
            WxError wxError = new WxError();
            wxError.setErrorMsg("预览消息 to User只能有一个");
            throw new WxErrorException(wxError);
        }
        // https://api.weixin.qq.com/cgi-bin/message/mass/preview?access_token=ACCESS_TOKEN
        String url = "https://api.weixin.qq.com/cgi-bin/message/mass/preview";
        String json = message.toJson();
        // 预览只支持一个to User,去掉toUser list的[]
        json = json.replace("[", "").replace("]", "");

        String responseContent = execute(new SimplePostRequestExecutor(), url, json);
        return WxMpMassSendResult.fromJson(responseContent);
    }





}
