
package com.fengjx.modules.wechat.process.utils;

import org.apache.commons.lang3.StringUtils;

/**
 * @author fengjx.
 * @date：2015/6/22 0022
 */
public final class ExecutorNameUtil {

    /**
     * 构造处理器名称
     *
     * @param msgType
     * @param eventType
     * @return
     */
    public static String buildName(String msgType, String eventType) {
        return msgType + "^_^" + (StringUtils.isBlank(eventType) ? "" : eventType);
    }
}
