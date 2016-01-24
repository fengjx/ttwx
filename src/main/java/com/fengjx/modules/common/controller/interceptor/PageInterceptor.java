
package com.fengjx.modules.common.controller.interceptor;

import com.fengjx.commons.utils.LogUtil;
import com.fengjx.commons.web.page.PageContext;
import com.fengjx.commons.web.page.adapter.BootstrapPage;
import com.fengjx.commons.web.page.adapter.JqGridPage;
import com.fengjx.modules.common.constants.AppConfig;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * URL拦截
 *
 * 分页拦截器
 */
public class PageInterceptor implements HandlerInterceptor {

    private static final Logger LOG = LoggerFactory.getLogger(PageInterceptor.class);

    public void afterCompletion(HttpServletRequest request,
            HttpServletResponse response, Object handler, Exception e)
            throws Exception {

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
        if (JqGridPage.ADAPTER_PAGE_NAME.equalsIgnoreCase(AppConfig.ADAPTER_PAGE_NAME)) {
            parseJqGridPage(request, response);
        } else if (BootstrapPage.ADAPTER_PAGE_NAME.equalsIgnoreCase(AppConfig.ADAPTER_PAGE_NAME)) {
            parseDataTablePage(request, response);
        }
        return true;
    }

    private void parseJqGridPage(HttpServletRequest request, HttpServletResponse response) {
        String rowsStr = request.getParameter("rows");
        // 默认每页10条
        int pageSize = 10;
        if (StringUtils.isNotBlank(rowsStr)) {
            pageSize = Integer.valueOf(rowsStr);
        }
        String pageStr = request.getParameter("page");
        int pageNumber = 1;
        if (StringUtils.isNotBlank(pageStr)) {
            pageNumber = Integer.valueOf(pageStr);
        }
        setPage(pageSize, pageNumber);
    }

    private void parseDataTablePage(HttpServletRequest request, HttpServletResponse response) {
        String limitStr = request.getParameter("limit");
        // 默认每页10条
        int pageSize = 10;
        if (StringUtils.isNotBlank(limitStr)) {
            pageSize = Integer.valueOf(limitStr);
        }
        String offsetStr = request.getParameter("offset");
        int pageNumber = 1;
        if (StringUtils.isNotBlank(offsetStr)) {
            pageNumber = (Integer.valueOf(offsetStr) / pageSize) + 1;
        }
        setPage(pageSize, pageNumber);
    }

    private void setPage(int pageSize, int pageNumber) {
        LogUtil.debug(LOG, "page params pageSize=" + pageSize + " pageNumber=" + pageNumber);
        PageContext.setPageSize(pageSize);
        PageContext.setPageNumber(pageNumber);
    }

}
