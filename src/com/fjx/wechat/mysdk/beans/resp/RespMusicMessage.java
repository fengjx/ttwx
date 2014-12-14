package com.fjx.wechat.mysdk.beans.resp;


import com.fjx.wechat.mysdk.constants.WechatRespMsgtypeConstants;

/**
 * 音乐消息
 * @author fengjx
 * @date 2014年1月19日
 */
public class RespMusicMessage extends RespBaseMessage {

	private static final long serialVersionUID = 8313156444450942830L;
	// 音乐
	private Music Music;

	public RespMusicMessage(){
		super.setMsgType(WechatRespMsgtypeConstants.RESP_MESSAGE_TYPE_MUSIC);
	}

	public Music getMusic() {
		return Music;
	}

	public void setMusic(Music music) {
		Music = music;
	}
}