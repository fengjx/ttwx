
package com.fengjx.commons.system.context;

import com.fengjx.commons.system.exception.MyRuntimeException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 讲request,response放到ThreadLocal方便普通java类调用
 * 
 * @author fengjx
 */
public class MySystemContext {

    private static ThreadLocal<HttpServletRequest> myRequest = new ThreadLocal<HttpServletRequest>();
    private static ThreadLocal<HttpServletResponse> myResponse = new ThreadLocal<HttpServletResponse>();

    /**
     * 将request、response放入容器
     * 
     * @param request
     * @param response
     */
    public static void setAll(HttpServletRequest request, HttpServletResponse response) {
        myRequest.set(request);
        myResponse.set(response);
    }

    /**
     * 获得request
     * 
     * @return
     */
    public static HttpServletRequest getMyRequest() {
        return myRequest.get();
    }

    /**
     * 获得session
     * 
     * @return
     * @throws Exception
     */
    public static HttpSession getMySession() {
        HttpServletRequest request = getMyRequest();
        if (null == request) {
            throw new MyRuntimeException("myRequest未赋值，无法获取session");
        }
        return request.getSession();
    }

    public static void setMyRequest(HttpServletRequest request) {
        myRequest.set(request);
    }

    /**
     * 获得response
     * 
     * @return
     */
    public static HttpServletResponse getMyResponse() {
        return myResponse.get();
    }

    public static void setMyRequest(HttpServletResponse response) {
        myResponse.set(response);
    }

    public static void removeMyRequest() {
        myRequest.remove();
    }

    public static void removeMyResponse() {
        myResponse.remove();
    }

    public static void remoceAll() {
        myRequest.remove();
        myResponse.remove();
    }
}
