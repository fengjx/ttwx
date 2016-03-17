
package com.fengjx.modules.common.controller;

import com.fengjx.commons.system.exception.MyException;
import com.fengjx.commons.system.exception.MyRuntimeException;
import com.fengjx.commons.utils.LogUtil;
import com.fengjx.commons.utils.WebUtil;
import com.fengjx.commons.web.BaseController;
import com.fengjx.modules.common.constants.AppConfig;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author fengjx.
 * @date：2015/5/17 0017
 */
@Controller("common")
@RequestMapping("/common")
public class CommonController extends BaseController {

    private static final Logger LOG = LoggerFactory.getLogger(CommonController.class);

    @RequestMapping("/error")
    public String error(HttpServletRequest request) {
        String forward = "errorview";
        if (WebUtil.isAjax(request)) {
            forward = "errorajax";
        }
        return "forward:/common/" + forward;
    }

    @RequestMapping("/errorview")
    public String errorView(HttpServletRequest request, Model model) {
        LogUtil.error(LOG, "request error to errorview");
        Map<String, String> errorMap = getErrorMap(request);
        errorMap.putAll(WebUtil.getRequestParams(request));
        model.addAllAttributes(errorMap);
        model.addAttribute("errorMsg", errorMap.get("msg"));
        return WebUtil.getUriWidthParam(request).replace(request.getContextPath(), "");
    }

    @RequestMapping("/errorajax")
    @ResponseBody
    public Map<String, String> errorAjax(HttpServletRequest request) {
        LogUtil.error(LOG, "request error to errorajax");
        return getErrorMap(request);
    }

    /**
     * 获得错误信息，封装到map
     *
     * @param request
     * @return
     */
    private Map<String, String> getErrorMap(HttpServletRequest request) {
        Exception e = (Exception) request.getAttribute(AppConfig.REQUEST_ERROE_MSG_KEY);
        String errorMsg = "请求失败";
        // 自定义异常
        if (e instanceof MyRuntimeException || e instanceof MyException) {
            if (StringUtils.isNotBlank(e.getMessage())) {
                errorMsg = e.getMessage();
            }
            LogUtil.warn(LOG, e.getMessage());
        } else {
            LogUtil.error(LOG, "request error", e);
        }
        Map<String, String> res = Maps.newHashMap();
        res.put("code", "0");
        res.put("msg", errorMsg);
        return res;
    }

}
