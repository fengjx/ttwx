
package com.fengjx.ttwx.common.plugin.log;

import javax.servlet.http.HttpServletRequest;

/**
 * 日志保存接口
 *
 * @Created by FengJianxin on 2015/10/11.
 * @Email xd-fjx@qq.com
 */
public interface ILogService {

    /**
     * 保存日志
     *
     * @param request
     * @param handler
     * @param ex
     */
    void save(HttpServletRequest request, Object handler, Exception ex);

}
