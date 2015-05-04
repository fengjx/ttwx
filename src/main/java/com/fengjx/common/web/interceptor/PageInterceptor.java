
package com.fengjx.common.web.interceptor;

import com.fengjx.common.mybatis.Page;

import com.fengjx.common.mybatis.PageContext;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PageInterceptor implements HandlerInterceptor {

    private static final Logger LOGGER = Logger.getLogger(PageInterceptor.class);

    public void afterCompletion(HttpServletRequest request,
            HttpServletResponse response, Object handler, Exception e)
            throws Exception {
        PageContext.remove();
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
        Page page = new Page();
        page.setPageNo(getPageNo(request));
        page.setPageSize(getPageSize(request));
        PageContext.setPage(page);
        return true;
    }

    private int getPageNo(HttpServletRequest request) {
        int pageNo = 0;
        String offset = request.getParameter("offset");
        if (StringUtils.isNotBlank(offset)) {
            pageNo = (Integer.valueOf(offset) / getPageSize(request))+1 ;
        }
        return pageNo;
    }

    private int getPageSize(HttpServletRequest request) {
        int pagesize = 10;
        String psvalue = request.getParameter("limit");
        if (StringUtils.isNoneBlank(psvalue)) {
            pagesize = Integer.valueOf(psvalue);
        }
        return pagesize;
    }

}
