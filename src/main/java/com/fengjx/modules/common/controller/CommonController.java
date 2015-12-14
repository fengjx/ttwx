
package com.fengjx.modules.common.controller;

import com.fengjx.commons.system.exception.MyException;
import com.fengjx.commons.system.exception.MyRuntimeException;
import com.fengjx.commons.utils.LogUtil;
import com.fengjx.commons.utils.WebUtil;
import com.fengjx.commons.web.BaseController;
import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

/**
 * @author fengjx.
 * @date：2015/5/17 0017
 */
@Controller("common")
@RequestMapping("/common")
public class CommonController extends BaseController {

    private static final Logger LOG = LoggerFactory.getLogger(CommonController.class);

    @Autowired
    private Producer kaptchaProducer;

    @RequestMapping("/loginTimeoutAjax")
    @ResponseBody
    public Map<String, String> loginTimeoutAjax() {
        Map<String, String> res = new HashMap<String, String>();
        res.put("code", "-1");
        res.put("msg", "登陆超时");
        return res;
    }

    @RequestMapping("/loginTimeout")
    public String loginTimeout() {
        return "redirect:/login";
    }

    @RequestMapping("/verification_code.jpg")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        response.setDateHeader("Expires", 0);
        // Set standard HTTP/1.1 no-cache headers.
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
        // Set IE extended HTTP/1.1 no-cache headers (use addHeader).
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");
        // Set standard HTTP/1.0 no-cache header.
        response.setHeader("Pragma", "no-cache");
        // return a jpeg
        response.setContentType("image/jpeg");
        // create the text for the image
        String capText = kaptchaProducer.createText();
        LogUtil.debug(LOG, "生成验证码：" + capText);
        // store the text in the session
        request.getSession().setAttribute(Constants.KAPTCHA_SESSION_KEY, capText);
        // create the image with the text
        BufferedImage bi = kaptchaProducer.createImage(capText);
        ServletOutputStream out = response.getOutputStream();
        // write the data out
        ImageIO.write(bi, "jpg", out);
        try {
            out.flush();
        } finally {
            IOUtils.closeQuietly(out);
        }
        return null;
    }

    @RequestMapping("/error")
    public String error(HttpServletRequest request) {
        String forward = "errorview";
        if (WebUtil.validAjax(request)) {
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
        Exception e = (Exception) request.getAttribute("ex");
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
        return retFail(errorMsg);
    }

}
