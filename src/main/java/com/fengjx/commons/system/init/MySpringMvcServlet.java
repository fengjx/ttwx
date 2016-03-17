
package com.fengjx.commons.system.init;

import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 自定义spring mvc如后servlet，功能扩展
 * 
 * @author fengjx xd-fjx@qq.com
 * @version MySpringMvcServlet.java 2014年10月6日
 */
public class MySpringMvcServlet extends DispatcherServlet {

    private static final long serialVersionUID = -6360879269342200719L;

    @Override
    protected void doService(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        super.doService(request, response);
    }

}
