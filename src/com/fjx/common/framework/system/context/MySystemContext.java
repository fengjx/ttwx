package com.fjx.common.framework.system.context;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fjx.common.framework.system.exception.MyRuntimeException;
import com.fjx.wechat.base.admin.entity.SysUserEntity;
import com.fjx.wechat.config.AppConfig;



/**
 * 讲request,response放到ThreadLocal方便普通java类调用
 * @author fengjx
 * 
 */
public class MySystemContext {
	
	private static ThreadLocal<HttpServletRequest> myRequest = new ThreadLocal<HttpServletRequest>();
	private static ThreadLocal<HttpServletResponse> myResponse = new ThreadLocal<HttpServletResponse>();
	
	
	/**
	 * 获取当前登录用户
	 * @return
	 * @throws Exception
	 */
	public static  SysUserEntity getLoginUser() {
		return (SysUserEntity) getMySession().getAttribute(AppConfig.LOGIN_FLAG);
	}
	
	/**
	 * 将request、response放入容器
	 * @param request
	 * @param response
	 */
	public static void setAll(HttpServletRequest request,HttpServletResponse response){
		myRequest.set(request);
		myResponse.set(response);
	}
	
	/**
	 * 获得request
	 * @return
	 */
	public static HttpServletRequest getMyRequest(){
		return myRequest.get();
	}
	
	/**
	 * 获得session
	 * @return
	 * @throws Exception
	 */
	public static HttpSession getMySession(){
		HttpServletRequest request = getMyRequest();
		if(null == request){
			throw new MyRuntimeException("myRequest未赋值，无法获取session");
		}
		return request.getSession();
	}
	
	
	public static void setMyRequest(HttpServletRequest request){
		myRequest.set(request);
	}
	
	/**
	 * 获得response
	 * @return
	 */
	public static HttpServletResponse getMyResponse(){
		return myResponse.get();
	}
	
	public static void setMyRequest(HttpServletResponse response){
		myResponse.set(response);
	}
	
	public static void removeMyRequest(){
		myRequest.remove();
	}
	
	public static void removeMyResponse(){
		myResponse.remove();
	}
	
	public static void remoceAll(){
		myRequest.remove();
		myResponse.remove();
	}
}
