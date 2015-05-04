
package com.fengjx.wechat.base.action.interceptor;

import com.fengjx.common.utils.WebUtil;
import com.fengjx.wechat.base.model.SysUser;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AdminInterceptor implements HandlerInterceptor {
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
            Object handler, Exception e) throws Exception {
    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response,
            Object handler, ModelAndView arg3) throws Exception {
    }

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
            Object handler) throws Exception {
        HttpSession session = request.getSession();
        SysUser user = (SysUser) session.getAttribute("sys_user_login_key");
        if (null == user) {
            if (WebUtil.validAjax(request))
                request.getRequestDispatcher("/common/loginTimeoutAjax").forward(request, response);
            else {
                request.getRequestDispatcher("/common/loginTimeout").forward(request, response);
            }
            return false;
        }
        return true;
    }
}
