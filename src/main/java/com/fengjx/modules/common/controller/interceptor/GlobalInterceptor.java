
package com.fengjx.modules.common.controller.interceptor;

import com.fengjx.commons.utils.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.NamedThreadLocal;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;

/**
 * 全局拦截器
 */
public class GlobalInterceptor implements HandlerInterceptor {

    private static final Logger LOG = LoggerFactory.getLogger(GlobalInterceptor.class);

    private static final ThreadLocal<Long> startTimeThreadLocal = new NamedThreadLocal<>(
            "ThreadLocal StartTime");

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
            Object handler, Exception e) throws Exception {
        // 打印JVM信息。
        if (LOG.isDebugEnabled()) {
            long beginTime = startTimeThreadLocal.get();// 得到线程绑定的局部变量（开始时间）
            long endTime = System.currentTimeMillis(); // 2、结束时间
            LOG.debug(
                    "计时结束：{}  耗时：{}  URI: {}  最大内存: {}m  已分配内存: {}m  已分配内存中的剩余空间: {}m  最大可用内存: {}m",
                    new SimpleDateFormat("hh:mm:ss.SSS").format(endTime),
                    DateUtils.formatDateTime(endTime - beginTime), request.getRequestURI(),
                    Runtime.getRuntime().maxMemory() / 1024 / 1024,
                    Runtime.getRuntime().totalMemory() / 1024 / 1024,
                    Runtime.getRuntime().freeMemory() / 1024 / 1024,
                    (Runtime.getRuntime().maxMemory() - Runtime.getRuntime().totalMemory()
                            + Runtime.getRuntime().freeMemory()) / 1024 / 1024);
        }
    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
            ModelAndView modelAndView) throws Exception {
        if (modelAndView != null) {
            LOG.info("ViewName: " + modelAndView.getViewName());
        }
    }

    /**
     * 如果返回false 从当前拦截器往回执行所有拦截器的afterCompletion方法，再退回拦截器链 如果返回true
     * 执行下一个拦截器，直到所有拦截器都执行完毕 再运行被拦截的Controller
     * 然后进入拦截器链从最后一个拦截器往回运行所有拦截器的postHandle方法
     * 接着依旧是从最后一个拦截器往回执行所有拦截器的afterCompletion方法
     */
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
            Object handler) throws Exception {
        if (LOG.isDebugEnabled()) {
            long beginTime = System.currentTimeMillis();// 1、开始时间
            startTimeThreadLocal.set(beginTime); // 线程绑定变量（该数据只有当前请求的线程可见）
            LOG.debug("开始计时: {}  URI: {}", new SimpleDateFormat("hh:mm:ss.SSS").format(beginTime),
                    request.getRequestURI());
        }
        return true;
    }

}
