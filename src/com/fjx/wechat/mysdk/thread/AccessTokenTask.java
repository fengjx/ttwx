
package com.fjx.wechat.mysdk.thread;

import com.fjx.wechat.mysdk.api.ApiConfig;
import com.fjx.wechat.mysdk.context.ApiConfigContext;

import org.apache.log4j.Logger;

import java.util.Iterator;
import java.util.Map;

/**
 * 定时获取微信access_token的线程
 * 
 * @author fengjx
 * @date 2014年5月10日
 */
public class AccessTokenTask {

    private static final Logger logger = Logger.getLogger(AccessTokenTask.class);

    public void doTask() {
        logger.debug("AccessTokenTask启动，定时清理过期的accesstoken");
        Map<String, ApiConfig> map = ApiConfigContext.getApiConfigMap();
        Iterator<Map.Entry<String, ApiConfig>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, ApiConfig> entry = it.next();
            ApiConfig config = entry.getValue();
			boolean available = config.getAccessToken().isAvailable();
			if(!available){
                logger.info("accesstoken清理：删除ApiConfigContext key=" + entry.getKey());
                ApiConfigContext.remove(entry.getKey());
			}
        }
    }
}
