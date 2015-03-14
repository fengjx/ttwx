
package com.fjx.wechat.base.admin.interceptor;

import com.fjx.wechat.mysdk.context.WechatContext;
import com.fjx.wechat.mysdk.tools.MessageUtil;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class WechatInterceptor implements HandlerInterceptor {

    private static final Logger LOGGER = Logger.getLogger(WechatInterceptor.class);

    public void afterCompletion(HttpServletRequest request,
            HttpServletResponse response, Object handler, Exception e)
            throws Exception {
        WechatContext.removeAll();
    }

    public void postHandle(HttpServletRequest request,
            HttpServletResponse response, Object handler, ModelAndView arg3)
            throws Exception {

    }

    /**
     * 如果返回false 从当前拦截器往回执行所有拦截器的afterCompletion方法，再退回拦截器链 如果返回true
     * 执行下一个拦截器，直到所有拦截器都执行完毕 再运行被拦截的Controller
     * 然后进入拦截器链从最后一个拦截器往回运行所有拦截器的postHandle方法
     * 接着依旧是从最后一个拦截器往回执行所有拦截器的afterCompletion方法
     */
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
            Object handler) throws Exception {
        // 得到请求方式（POST/GET）
        String method = request.getMethod();
        LOGGER.debug("微信请求：method=" + method);
        // 除校验接口外，微信以POST方式向接口提交数据
        if ("POST".equals(method.toUpperCase())) {
            // 将参数封装到Threadlocal作为上下文调用
            WechatContext.setWechatPostMap(parsePostMap(request));
        }
        // 放行
        return true;
    }

    /**
     * 将微信POST过来的json转换为map
     * 
     * @param request
     * @return
     */
    private Map<String, String> parsePostMap(HttpServletRequest request) {
        Map<String, String> map = null;
        try {
            map = MessageUtil.parseXml(request);
        } catch (Exception e) {
            LOGGER.error("解析微信请求数据出现异常", e);
        }
        return map;
    }

}
