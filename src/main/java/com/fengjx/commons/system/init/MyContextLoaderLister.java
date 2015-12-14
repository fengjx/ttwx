
package com.fengjx.commons.system.init;

import org.springframework.web.context.ContextLoaderListener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;

/**
 * 自定义监听器，扩展spring监听器的功能
 * 
 * @author fengjx 2014年5月23日
 */
public class MyContextLoaderLister extends ContextLoaderListener {

    @Override
    public void contextInitialized(ServletContextEvent event) {
        ServletContext servletContext = event.getServletContext();
        SpringBeanFactoryUtil.setServletContext(servletContext);
        super.contextInitialized(event);
    }

    @Override
    public void contextDestroyed(ServletContextEvent event) {
        super.contextDestroyed(event);
    }

}
