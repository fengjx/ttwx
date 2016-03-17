
package com.fengjx.commons.utils;

import com.fengjx.modules.common.constants.AppConfig;
import com.google.common.base.Joiner;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * web相关工具类 Created by fengjx on 2014/11/14 0014.
 */
public final class WebUtil {

    /**
     * 获取所有request请求参数key-value
     * 
     * @param request
     * @return
     */
    public static Map<String, String> getRequestParams(HttpServletRequest request) {

        Map<String, String> params = new HashMap<>();
        if (null != request) {
            Set<String> paramsKey = request.getParameterMap().keySet();
            for (String key : paramsKey) {
                params.put(key, request.getParameter(key));
            }
        }
        return params;
    }

    /**
     * 获取所有request请求参数key-value，过滤掉空值
     *
     * @param request
     * @return
     */
    public static Map<String, String> getNotBlankRequestParams(HttpServletRequest request) {
        Map<String, String> params = new HashMap<>();
        if (null != request) {
            Set<String> paramsKey = request.getParameterMap().keySet();
            for (String key : paramsKey) {
                if (StringUtils.isNotBlank(request.getParameter(key))) {
                    params.put(key, request.getParameter(key));
                }
            }
        }
        return params;
    }

    /**
     * 获得客户端IP
     * 
     * @param request
     * @return
     */
    public static String getRealIp(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    public static String getRealIpV2(HttpServletRequest request) {
        String accessIP = request.getHeader("x-forwarded-for");
        if (null == accessIP)
            return request.getRemoteAddr();
        return accessIP;
    }

    public static String urlEncode(String source) {
        String res = source;
        try {
            res = URLEncoder.encode(source, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        return res;
    }

    /**
     * 判断是否是ajax请求
     * 
     * @param request
     * @return
     */
    public static boolean isAjax(HttpServletRequest request) {
        String flag = request.getHeader("Request-Flag");
        if (AppConfig.REQUEST_FLAG_AJAX.equals(flag)) {
            return true;
        }
        String requestType = request.getHeader("X-Requested-With");
        if ("XMLHttpRequest".equals(requestType)) {
            return true;
        }
        return false;
    }

    /**
     * 获得带参数的URL
     *
     * @param request
     * @return
     */
    public static String getUriWidthParam(HttpServletRequest request) {
        String url = request.getRequestURI();
        String method = request.getMethod();
        if ("GET".equalsIgnoreCase(method)) {
            Map<String, String> params = getRequestParams(request);
            String join = Joiner.on("&").withKeyValueSeparator("=").join(params);
            return URLTool.builderURL(url, join);
        }
        return url;
    }

    /**
     * 获取当前请求对象
     * 
     * @return
     */
    public static HttpServletRequest getRequest() {
        try {
            return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                    .getRequest();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 判断访问URI是否是静态文件请求
     * 
     * @throws Exception
     */
    public static boolean isStaticFile(String uri) {
        if (StringUtils.endsWithAny(uri, AppConfig.getStaticFiles())
                && !StringUtils.endsWithAny(uri, ".jsp")
                && !StringUtils.endsWithAny(uri, ".java")) {
            return true;
        }
        return false;
    }

    /**
     * 获得用户远程地址
     */
    public static String getRemoteAddr(HttpServletRequest request) {
        String remoteAddr = request.getHeader("X-Real-IP");
        if (StringUtils.isNotBlank(remoteAddr)) {
            remoteAddr = request.getHeader("X-Forwarded-For");
        } else if (StringUtils.isNotBlank(remoteAddr)) {
            remoteAddr = request.getHeader("Proxy-Client-IP");
        } else if (StringUtils.isNotBlank(remoteAddr)) {
            remoteAddr = request.getHeader("WL-Proxy-Client-IP");
        }
        return remoteAddr != null ? remoteAddr : request.getRemoteAddr();
    }

    /**
     * 写出数据
     *
     * @param res 输出的字符串
     * @throws Exception
     */
    public static void write(String res, HttpServletResponse response) {
        Writer writer = null;
        try {
            res = (null == res ? "" : res);
            response.setCharacterEncoding("UTF-8");
            response.setHeader("Content-type", "text/html;charset=UTF-8");
            writer = response.getWriter();
            writer.write(res);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            IOUtils.closeQuietly(writer);
        }
    }

}
