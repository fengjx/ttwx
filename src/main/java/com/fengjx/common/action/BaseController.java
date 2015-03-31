
package com.fengjx.common.action;

import com.fengjx.wechat.config.AppConfig;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 控制器基类
 * 
 * @author fengjx xd-fjx@qq.com
 * @date 2014年9月12日
 */
public class BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(BaseController.class);

    protected HttpSession getSession(HttpServletRequest request) {
        return request.getSession();
    }

    /**
     * 当用户提交数据时，使用此模板方法，比如保存，更新操作 成功统一返回code=1，失败返回code=0
     * 
     * @param callack
     * @param exceptionInfo
     * @return
     */
    protected Map<String, String> doResult(MyExecuteCallback callack,
            String exceptionInfo) {
        Map<String, String> res = new HashMap<String, String>();
        try {
            callack.execute();
            res.put("code", "1");
            res.put("msg", "操作成功");
        } catch (Exception e) {
            res.put("code", "0");
            if (StringUtils.isBlank(exceptionInfo)) {
                res.put("msg", e.getMessage());
            } else {
                res.put("msg", exceptionInfo);
            }
            LOGGER.error(exceptionInfo, e);
        }
        return res;
    }

    /**
     * 操作成功数据
     * 
     * @return
     */
    protected Map<String, String> retSuccess() {
        return retSuccess("操作成功");
    }

    protected Map<String, String> retSuccess(String msg) {
        Map<String, String> res = new HashMap<String, String>();
        res.put("code", "1");
        res.put("msg", msg);
        return res;
    }

    /**
     * 设置异常信息
     * 
     * @return
     */
    protected void setErrorMsg(HttpServletRequest request, String msg) {
        request.setAttribute(AppConfig.REQUEST_ERROE_MSG_KEY, msg);
    }

    /**
     * 校验验证码
     * 
     * @param request
     * @param valid_code
     * @return
     */
    protected Map<String, String> compareValidCode(HttpServletRequest request,
            String valid_code) {
        Map<String, String> res = new HashMap<String, String>();
        res.put("code", "1");
        res.put("msg", "验证码正确");
        String code = request.getSession().getAttribute(
                com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY)
                + "";
        LOGGER.debug("比较验证码code=" + code + " valid_code=" + valid_code);
        if (StringUtils.isBlank(code)) {
            res.put("code", "0");
            res.put("msg", "页面超时，请重试！");
        } else if (!code.equalsIgnoreCase(valid_code)) {
            res.put("code", "0");
            res.put("msg", "验证码错误！");
        }
        return res;
    }

    /**
     * 写出数据
     * 
     * @param res 输出的字符串
     * @throws Exception
     */
    protected void write(String res, HttpServletResponse response) throws Exception {
        Writer writer = null;
        try {
            res = (null == res ? "" : res);
            response.setCharacterEncoding("UTF-8");
            response.setHeader("Content-type", "text/html;charset=UTF-8");
            writer = response.getWriter();
            LOGGER.debug("输出JSON字符串：" + res);
            writer.write(res);
        } catch (IOException e) {
            LOGGER.error("输出JSON字符串异常");
            throw new Exception("write json string error");
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    LOGGER.error("关闭输出流异常,无法关闭会导致内存溢出");
                }
            }
        }
    }
}
