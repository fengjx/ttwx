package com.fjx.wechat.base.process;



/**
 * 服务执行器
 * @author jie.hua@alipay.com
 * @version $Id: ServiceExecutor.java, v 0.1 2014-1-9 上午2:45:59 jie.hua Exp $
 */
public interface ServiceExecutor {

    /**
     * 业务执行方法
     * @param instruction
     */
    public String execute() throws Exception;

}
