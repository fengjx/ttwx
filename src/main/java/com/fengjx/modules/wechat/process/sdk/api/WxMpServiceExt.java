package com.fengjx.modules.wechat.process.sdk.api;

import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.WxMpMassOpenIdsMessage;
import me.chanjar.weixin.mp.bean.result.WxMpMassSendResult;

/**
 * 扩展weixin MP功能，需要时候转为这个接口
 * @author dennykong
 *
 */
public interface WxMpServiceExt extends WxMpService {
	/**
	 * 发送预览消息
	 * @param message
	 * @return
	 * @throws WxErrorException
	 */
	WxMpMassSendResult massPreviewMessage(WxMpMassOpenIdsMessage message) throws WxErrorException;

}
