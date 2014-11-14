package com.fjx.wechat.mysdk.process.in.executor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fjx.wechat.mysdk.process.in.Dispatcher;
import org.apache.log4j.Logger;

/**
 * 
 * @author jie.hua@alipay.com
 * @version $Id: InServiceExecutorFactory.java, v 0.1 2014-1-6 下午9:46:12 jie.hua Exp $
 */
public class InServiceExecutorFactory {
	
	private static final Logger logger = Logger.getLogger(InServiceExecutorFactory.class);
	
    /**
     * 服务映射
     */
    private Map<String, InServiceExecutor> executorMaps = new HashMap<String, InServiceExecutor>();

    /**
     * 根据name查询服务执行器
     * 
     * @param name
     * @return
     */
    public InServiceExecutor getExecutorByName(String name) {
        return executorMaps.get(name);

    }


    /**
     * 根据name查询服务执行器
     * @param requestMap
     * @return
     */
    public InServiceExecutor getExecutorByName(Map<String, String> requestMap) {
    	String executorName = Dispatcher.getExecutorName(requestMap);
    	logger.info("executorName = "+executorName);
        return executorMaps.get(executorName);
    }

    /**
     *  设置服务执行器
     * @param executors
     */
    public void setExecutorList(List<InServiceExecutor> executors) {
        if (executors == null || executors.isEmpty()) {
            return;
        }
        for (InServiceExecutor executor : executors) {
            executorMaps.put(executor.getExecutorName(), executor);
        }
    }

}
