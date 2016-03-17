
package com.fengjx.modules.wechat.process.executor;

import com.fengjx.modules.wechat.process.Dispatcher;
import com.fengjx.modules.wechat.process.ServiceExecutor;

import me.chanjar.weixin.mp.bean.WxMpXmlMessage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 服务执行器工厂
 * 
 * @author jie.hua@alipay.com
 * @version $Id: InServiceExecutorFactory.java, v 0.1 2014-1-6 下午9:46:12 jie.hua
 *          Exp $
 */
public class ServiceExecutorFactory {

    /**
     * 服务映射
     */
    private Map<String, ServiceExecutor> executorMaps = new HashMap<>();

    /**
     * 根据name查询服务执行器
     * 
     * @param name
     * @return
     */
    public ServiceExecutor getExecutorByName(String name) {
        return executorMaps.get(name);
    }

    public ServiceExecutor getExecutorByName(WxMpXmlMessage inMessage) {
        String executorName = Dispatcher.getExecutorName(inMessage);
        return getExecutorByName(executorName);
    }

    /**
     * 注入设置服务执行器
     * 
     * @param executors
     */
    public void setExecutorList(List<BaseServiceExecutor> executors) {
        if (executors == null || executors.isEmpty()) {
            return;
        }
        for (BaseServiceExecutor executor : executors) {
            executorMaps.put(executor.getExecutorName(), executor);
        }
    }

}
