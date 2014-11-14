package com.fjx.wechat.mysdk.vo.resp;

import com.fjx.wechat.base.vo.resp.*;
import com.fjx.wechat.base.vo.resp.Music;

/**
 * 音乐消息
 * @author fengjx
 * @date 2014年1月19日
 */
public class RespMusicMessage extends com.fjx.wechat.base.vo.resp.RespBaseMessage {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8313156444450942830L;
	// 音乐
	private com.fjx.wechat.base.vo.resp.Music Music;

	public com.fjx.wechat.base.vo.resp.Music getMusic() {
		return Music;
	}

	public void setMusic(com.fjx.wechat.base.vo.resp.Music music) {
		Music = music;
	}
}