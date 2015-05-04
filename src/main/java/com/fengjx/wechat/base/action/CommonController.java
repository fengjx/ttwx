
package com.fengjx.wechat.base.action;

import com.fengjx.common.utils.LoggerUtil;
import com.fengjx.common.utils.WebUtil;
import com.fengjx.common.web.action.BaseController;
import com.fengjx.wechat.config.AppConfig;
import com.google.code.kaptcha.Producer;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller("common")
@RequestMapping("/common")
public class CommonController extends BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CommonController.class);

    @Autowired
    private Producer kaptchaProducer;

    @RequestMapping("/loginTimeoutAjax")
    @ResponseBody
    public Map<String, String> loginTimeoutAjax() {
        Map res = new HashMap();
        res.put("code", "-1");
        res.put("msg", "登陆超时");
        return res;
    }

    @RequestMapping("/loginTimeout")
    public String loginTimeout() {
        return "redirect:/login";
    }

    @RequestMapping("/verification_code.jpg")
    public void handleRequest(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        response.setDateHeader("Expires", 0L);
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");
        response.setHeader("Pragma", "no-cache");
        response.setContentType("image/jpeg");
        String capText = this.kaptchaProducer.createText();
        LoggerUtil.debug(LOGGER, "生成验证码：" + capText);
        request.getSession().setAttribute("KAPTCHA_SESSION_KEY", capText);
        BufferedImage bi = this.kaptchaProducer.createImage(capText);
        ServletOutputStream out = response.getOutputStream();
        ImageIO.write(bi, "jpg", out);
        try {
            out.flush();
        } finally {
            IOUtils.closeQuietly(out);
        }
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
    public String errorView(HttpServletRequest request, Map map) {
        LoggerUtil.error(LOGGER, "request error to errorview");
        map = getErrorMap(request);
        return "common/error/error-exception";
    }

    @RequestMapping("/errorajax")
    @ResponseBody
    public Map<String, String> errorAjax(HttpServletRequest request) {
        LoggerUtil.error(LOGGER, "request error to errorajax");
        return getErrorMap(request);
    }

    private Map<String, String> getErrorMap(HttpServletRequest request) {
        Exception e = (Exception) request.getAttribute("ex");
        LoggerUtil.error(LOGGER, "request error", e);
        Map map = new HashMap();
        String errorMsg = (String) request.getAttribute("errorMsg");
        if (StringUtils.isBlank(errorMsg)) {
            errorMsg = "操作失败";
            if (AppConfig.isTest()) {
                errorMsg = e.getMessage();
            }
        }
        map.put("code", "0");
        map.put("statue", "500");
        map.put("msg", errorMsg);
        return map;
    }
}
