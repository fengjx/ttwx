
package com.fengjx.ttwx.modules.sys.controller.admin.interceptor;

import com.fengjx.ttwx.common.utils.CookieUtils;
import com.fengjx.ttwx.common.utils.WebUtil;
import com.fengjx.ttwx.modules.common.constants.AppConfig;
import com.fengjx.ttwx.modules.wechat.entity.SysUserEntity;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AdminInterceptor implements HandlerInterceptor {

    private static final String LAST_URI = "last_uri";

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
            Object handler, Exception e) throws Exception {

    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response,
            Object handler,
            ModelAndView arg3) throws Exception {

    }

    /**
     * 如果返回false 从当前拦截器往回执行所有拦截器的afterCompletion方法，再退回拦截器链 如果返回true
     * 执行下一个拦截器，直到所有拦截器都执行完毕 再运行被拦截的Controller
     * 然后进入拦截器链从最后一个拦截器往回运行所有拦截器的postHandle方法
     * 接着依旧是从最后一个拦截器往回执行所有拦截器的afterCompletion方法
     */
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
            Object handler) throws Exception {
        HttpSession session = request.getSession();
        SysUserEntity user = (SysUserEntity) session.getAttribute(AppConfig.LOGIN_FLAG);
        // 登陆超时
        if (null == user && !WebUtil.validAjax(request)) {
            CookieUtils.setCookie(response, LAST_URI,
                    WebUtil.getUriWidthParam(request).replace(request.getContextPath(), ""), 60 * 60);
            // 如果是ajax请求
            if (WebUtil.validAjax(request)) {
                request.getRequestDispatcher("/common/loginTimeoutAjax").forward(request, response);
            } else {
                request.getRequestDispatcher("/common/loginTimeout").forward(request, response);
            }
            return false;
        }
        return true;
    }

}
