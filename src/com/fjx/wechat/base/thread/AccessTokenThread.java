package com.fjx.wechat.base.thread;

import java.util.TimerTask;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
 * 定时获取微信access_token的线程
 * @author fengjx
 * 2014年5月10日
 */
public class AccessTokenThread extends TimerTask {
	
	private final Log logger = LogFactory.getLog(this.getClass());
	
	
	@Override
	public void run() {
		logger.debug("定时刷新获取微信accessToken，保持其长期有效");
		boolean flag = true;
		while(flag){
			try {
//				AccessToken accessToken = getAccessToken();
//				if (null != accessToken) {
//					flag = false;
//					logger.info("获取access_token成功，有效时长"+accessToken.getExpiresIn()+"秒 token:"+accessToken.getToken());
//				} else {
//					// 如果access_token为null，60秒后再获取
//					logger.warn("获取access_token为空");
//					Thread.sleep(30 * 1000);
//				}
			} catch (Exception e) {
				try {
					Thread.sleep(30 * 1000);
				} catch (InterruptedException e1) {
					logger.error(e1);
				}
				logger.error("获取access_token失败",e);
			}
		}
	}
	
}
