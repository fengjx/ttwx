package com.fjx.common.framework.system.init;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.DispatcherServlet;

import com.fjx.common.framework.system.context.MySystemContext;
import com.fjx.common.framework.system.pagination.PaginationContext;


/**
 * 自定义spring mvc如后servlet，功能扩展
 * @author fengjx xd-fjx@qq.com
 * @version MySpringMvcServlet.java 2014年10月6日
 */
public class MySpringMvcServlet extends DispatcherServlet {

	private static final long serialVersionUID = -6360879269342200719L;
	
	private static final String PAGE_SIZE_NAME = "pageSize";
	private String page_url = "";
	
	
	@Override
	protected void doService(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		//将request，response放到MySystemContext，方便在javaBean中调用
		MySystemContext.setAll(request, response);
		String uri = request.getRequestURI();
		logger.debug("访问地址："+uri);
		//正则表达式匹配，参数在web.xml配置
		if(uri.matches(page_url)){		//分页请求
			logger.debug("easyui组件分页参数 -- page:" + request.getParameter("page")+";  rows: " + request.getParameter("rows"));
			PaginationContext.setPagesize(getPageSize(request));
			PaginationContext.setOffset(getOffset(request));
			try{
				super.doService(request, response);
			}
			finally{
				PaginationContext.removeAll();
			}
			return;
		}
		super.doService(request, response);
	}
	
	
	private int getOffset(HttpServletRequest request) {
		int offset = 0;
		int pageNow = 0;
		
		String pageNowStr = request.getParameter("page");
		if (pageNowStr != null && !pageNowStr.equals("")){
			pageNow = Integer.valueOf(pageNowStr);
			offset = (pageNow - 1)*PaginationContext.getPagesize();
			//offset = pageNow - 1;
			
		}
		logger.debug("offset： "+offset);
		return offset;
	}

	private int getPageSize(HttpServletRequest request) {
		int pagesize = 10;
		//首先判断request中是否有pagesize参数，如果有这个参数，证明客户端正在请求改变每页显示的行数
		String psvalue = request.getParameter("rows");
		if(psvalue != null && !psvalue.trim().equals("")){
			Integer ps = 0;
			try {
				ps = Integer.parseInt(psvalue);
			} catch (Exception e) {
			}
			if(ps != 0){
				request.getSession().setAttribute(PAGE_SIZE_NAME, ps);
			}
		}
		
		//判断当前session中是否有pagesize的值ֵ
		Integer ps = (Integer)request.getSession().getAttribute(PAGE_SIZE_NAME);
		if(ps == null){
			request.getSession().setAttribute(PAGE_SIZE_NAME, pagesize);
		} else {
			pagesize = ps;
		}
		logger.debug("pagesize： "+pagesize);
		return pagesize;
	}
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		page_url = config.getInitParameter("page_url");
		super.init(config);
	}
	
}
