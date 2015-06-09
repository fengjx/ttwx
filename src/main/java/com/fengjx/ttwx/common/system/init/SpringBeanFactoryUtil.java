package com.fengjx.ttwx.common.system.init;

import javax.servlet.ServletContext;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.support.WebApplicationContextUtils;

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

    public static Object getBean(String beanName) {
        return getSpringContext().getBean(beanName);
    }

    public static <T> T getBean(Class<T> cls) {
        return getSpringContext().getBean(cls);
    }
}
