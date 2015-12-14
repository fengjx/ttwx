
package com.fengjx.commons.system.init;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;

public class SpringBeanFactoryUtil {

    private static ApplicationContext springContext;

    private static ServletContext servletContext;

    public static void setServletContext(ServletContext servletContext) {
        SpringBeanFactoryUtil.servletContext = servletContext;
    }

    private static ApplicationContext getSpringContext() {
        if (springContext == null) {
            if (servletContext != null) {
                springContext = WebApplicationContextUtils.getWebApplicationContext(servletContext);
            } else {
                springContext = ContextLoader.getCurrentWebApplicationContext();
            }
            if (springContext == null) {
                springContext = new ClassPathXmlApplicationContext("applicationContext.xml");
            }
        }
        return springContext;
    }

    @SuppressWarnings("unchecked")
    public static <T> T getBean(String beanName) {
        return (T) getSpringContext().getBean(beanName);
    }

    public static <T> T getBean(Class<T> cls) {
        return getSpringContext().getBean(cls);
    }
}
