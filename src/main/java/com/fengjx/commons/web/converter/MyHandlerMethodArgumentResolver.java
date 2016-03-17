
package com.fengjx.commons.web.converter;

import com.fengjx.commons.plugin.db.BaseBean;
import com.fengjx.commons.plugin.db.Injector;
import com.fengjx.commons.plugin.db.annotation.BindBean;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * 自定义参数解析器
 *
 * @author FengJianxin
 * @version 2015-05-15
 */
public class MyHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(BindBean.class);
    }

    @SuppressWarnings("unchecked")
    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
            NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        return Injector.injectModel((Class<BaseBean>) parameter.getParameterType(),
                webRequest.getParameterMap(), false);
    }
}
