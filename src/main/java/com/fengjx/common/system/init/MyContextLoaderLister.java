package com.fengjx.common.system.init;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.ContextLoaderListener;

/**
 * 自定义监听器，扩展spring监听器的功能
 * @author fengjx
 * 2014年5月23日
 */
public class MyContextLoaderLister extends ContextLoaderListener {

	@Override
	public void contextInitialized(ServletContextEvent event) {
		ServletContext servletContext = event.getServletContext();
		SpringBeanFactoryUtil.setServletContext(servletContext);
		String ftlPath = servletContext.getInitParameter("ftlPath");
		String ftlEncoding = servletContext.getInitParameter("ftlEncoding");
		try {
			//初始化freemark工具类，设置其模板文件根路径
			//如果没有配置字符集
			if(StringUtils.isBlank(ftlEncoding)){	
				FreeMarkerUtil.init(servletContext , ftlPath);
			}else{
				FreeMarkerUtil.init(servletContext , ftlPath, ftlEncoding);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		super.contextInitialized(event);
	}

	@Override
	public void contextDestroyed(ServletContextEvent event) {
		super.contextDestroyed(event);
	}

}
