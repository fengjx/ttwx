package com.fjx.wechat.mysdk.tools;

import org.apache.commons.lang3.StringUtils;

/**
 * 服务相关工具类
 * @author jie.hua@alipay.com
 * @version $Id: ServiceTools.java, v 0.1 2014-1-6 下午4:44:19 jie.hua Exp $
 */
public final class NameTool {

    /**
     * 分隔符
     */
    public static final String SEPARATOR = "_";

    /**
     * 构建微信->商户 service名称
     * 
     * @param msgType
     * @param eventType
     * @return
     */
    public static String buildInServiceName(String msgType, String eventType) {
        return StringUtils.defaultIfBlank(msgType, StringUtils.EMPTY) + SEPARATOR
               + StringUtils.defaultIfBlank(eventType, StringUtils.EMPTY);
    }

    /**
     * 根据appId和appSecret生成key
     * @return
     */
    public static String createApiConfigKey(String appId, String appSecret){
        return appId + "_" + appSecret;
    }

}
