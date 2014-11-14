package com.fjx.wechat.base.web.admin.filter;

import java.io.IOException;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import com.fjx.wechat.base.context.WechatContext;
import com.fjx.wechat.base.tools.MessageUtil;
import org.apache.log4j.Logger;

/**
 * 微信请求拦截器
 * @author fengjx
 * @date 2014年2月21日
 */
public class WechatFilter implements Filter {

	private static final Logger logger = Logger.getLogger(WechatFilter.class);
	
	@Override
	public void destroy() {
	}
	
	@Override
	public void doFilter(ServletRequest req, ServletResponse resp,FilterChain china) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		//得到请求方式（POST/GET）
		String method = request.getMethod();
		logger.debug("微信请求：method="+method);
		//除校验接口外，微信以POST方式向接口提交数据
		if("POST".equals(method.toUpperCase())){
			//将参数封装到Threadlocal作为上下文调用
			WechatContext.setWechatPostMap(parsePostMap(request));
			try{
				//放行
				china.doFilter(req, resp);
			}
			finally{
				//释放进程池中的资源，防止线程回（tomcat采用进程池机制）收被其他请求调用
				WechatContext.removeAll();
			}
			return;
		}
		//放行
		china.doFilter(req, resp);
	}

	/**
	 * 将微信POST过来的json转换为map
	 * @param request
	 * @return
	 */
	private Map<String,String> parsePostMap(HttpServletRequest request) {
		Map<String,String> map = null;
		try {
			map = MessageUtil.parseXml(request);
		} catch (Exception e) {
			logger.error("解析微信请求数据出现异常",e);
		}
		return map;
	}
	
	@Override
	public void init(FilterConfig config) throws ServletException {
		
	}
	
}
