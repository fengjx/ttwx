
package com.fengjx.wechat.base.action.interceptor;

import com.fengjx.common.utils.LoggerUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class WechatInterceptor implements HandlerInterceptor {
    private static final Logger LOGGER = LoggerFactory.getLogger(WechatInterceptor.class);

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
            Object handler, Exception e) throws Exception {
    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response,
            Object handler, ModelAndView arg3) throws Exception {
    }

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
            Object handler) throws Exception {
        String method = request.getMethod();
        LoggerUtil.debug(LOGGER, "微信请求：method=" + method);

        if ("POST".equals(method.toUpperCase()))
            ;
        return true;
    }

    private Map<String, String> parsePostMap(HttpServletRequest request)    {
        Map map = null;
        return map;
    }
}
