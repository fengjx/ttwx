package com.fjx.wechat.mysdk.thread;

import com.fjx.wechat.mysdk.api.ApiConfig;
import com.fjx.wechat.mysdk.context.ApiConfigContext;
import org.apache.log4j.Logger;

import java.util.*;


/**
 * 定时获取微信access_token的线程
 * @author fengjx
 * 2014年5月10日
 */
public class AccessTokenTask {

	private final Logger logger = Logger.getLogger(this.getClass());

	//超过这个时间的缓存配置将清理
	private Long overTime = new Long(7200 * 2);

	public void doTask() {
		logger.debug("AccessTokenTask启动，定时清理过期的accesstoken");
		Map<String, ApiConfig> map = ApiConfigContext.getApiConfigMap();
		Iterator<Map.Entry<String, ApiConfig>> it = map.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<String, ApiConfig> entry = it.next();
			ApiConfig config = entry.getValue();
			Long expiredTime = config.getAccessToken().getExpiredTime();
			if(System.currentTimeMillis() - expiredTime > overTime){
				logger.info("accesstoken清理：删除ApiConfigContext key=" +entry.getKey());
				ApiConfigContext.remove(entry.getKey());
			}
		}
	}

}
