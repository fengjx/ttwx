
package com.fengjx.ttwx.common.web;

import com.fengjx.ttwx.common.plugin.db.Model;
import com.fengjx.ttwx.common.plugin.db.ParamHelper;
import com.fengjx.ttwx.common.utils.LogUtil;
import com.fengjx.ttwx.common.utils.WebUtil;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.Writer;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

/**
 * 控制器基类
 *
 * @author fengjx xd-fjx@qq.com
 * @date 2014年9月12日
 */
public abstract class BaseController {

    private static final Logger LOG = LoggerFactory.getLogger(BaseController.class);

    protected HttpSession getSession(HttpServletRequest request) {
        return request.getSession();
    }

    protected ParamHelper getParamHelper(HttpServletRequest request) {
        return new ParamHelper().fromMap(WebUtil.getRequestParams(request));
    }

    protected <T extends Model> ParamHelper getParamHelper(Class<T> modelCls,
            HttpServletRequest request) {
        try {
            return new ParamHelper().fromMap(modelCls, WebUtil.getRequestParams(request));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    protected Map<String, Object> getRequestMap(HttpServletRequest request) {
        Map<String, Object> map = new HashMap();
        map.putAll(WebUtil.getRequestParams(request));
        return map;
    }

    protected Map<String, String> getParams(HttpServletRequest request) {
        return WebUtil.getRequestParams(request);
    }

    protected Map<String, Object> getNotBlankRequestMap(HttpServletRequest request) {
        Map<String, Object> map = new HashMap();
        map.putAll(WebUtil.getNotBlankRequestParams(request));
        return map;
    }

    protected Map<String, String> getNotBlankParams(HttpServletRequest request) {
        return WebUtil.getNotBlankRequestParams(request);
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
        try {
            callack.execute();
            return retSuccess();
        } catch (Exception e) {
            LogUtil.error(LOG, exceptionInfo, e);
            if (StringUtils.isBlank(exceptionInfo)) {
                return retFail();
            } else {
                return retFail(exceptionInfo);
            }
        }
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
        Map<String, String> res = new HashMap();
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
        } finally {
            IOUtils.closeQuietly(writer);
        }
    }
}
