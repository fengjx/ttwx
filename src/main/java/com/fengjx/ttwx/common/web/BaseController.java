
package com.fengjx.ttwx.common.web;

import com.fengjx.ttwx.common.utils.LogUtil;
import com.fengjx.ttwx.common.utils.WebUtil;

import org.apache.commons.io.IOUtils;
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

    private static final Logger LOG = LoggerFactory.getLogger(BaseController.class);

    protected HttpSession getSession(HttpServletRequest request) {
        return request.getSession();
    }

    protected Map<String, Object> getRequestMap(HttpServletRequest request) {
        Map<String, Object> map =  new HashMap<String, Object>();
        map.putAll(WebUtil.getRequestParams(request));
        return map;
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
            LogUtil.error(LOG, exceptionInfo, e);
        }
        return res;
    }

    /**
     * 操作成功数据
     *
     * @return
     */
    protected Map<String, String> retFail() {
        return retFail("请求失败");
    }

    protected Map<String, String> retFail(String msg) {
        Map<String, String> res = new HashMap<String, String>();
        res.put("code", "0");
        res.put("msg", msg);
        return res;
    }

    /**
     * 操作成功数据
     *
     * @return
     */
    protected Map<String, String> retSuccess() {
        return retSuccess("请求成功");
    }

    protected Map<String, String> retSuccess(String msg) {
        Map<String, String> res = new HashMap<String, String>();
        res.put("code", "1");
        res.put("msg", msg);
        return res;
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
        String code = getSession(request).getAttribute(
                com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY)
                + "";
        LogUtil.debug(LOG, "比较验证码code=" + code + " valid_code=" + valid_code);
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
    protected void write(String res, HttpServletResponse response) {
        Writer writer = null;
        try {
            res = (null == res ? "" : res);
            response.setCharacterEncoding("UTF-8");
            response.setHeader("Content-type", "text/html;charset=UTF-8");
            writer = response.getWriter();
            LogUtil.debug(LOG, "输出JSON字符串：" + res);
            writer.write(res);
        } catch (IOException e) {
            LogUtil.error(LOG, "输出JSON字符串异常", e);
            throw new RuntimeException("write json string error");
        } finally {
            IOUtils.closeQuietly(writer);
        }
    }
}
