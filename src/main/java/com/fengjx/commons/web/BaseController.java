
package com.fengjx.commons.web;

import com.fengjx.commons.config.AjaxTemplate;
import com.fengjx.commons.plugin.db.BaseBean;
import com.fengjx.commons.plugin.db.Injector;
import com.fengjx.commons.plugin.db.Record;
import com.fengjx.commons.system.exception.ValidateException;
import com.fengjx.commons.utils.DateUtils;
import com.fengjx.commons.utils.LogUtil;
import com.fengjx.commons.utils.WebUtil;
import com.google.common.collect.Maps;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.beans.PropertyEditorSupport;
import java.io.IOException;
import java.io.Writer;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 控制器基类
 *
 * @author fengjx xd-fjx@qq.com
 * @date 2014年9月12日
 */
public abstract class BaseController {

    private static final Logger LOG = LoggerFactory.getLogger(BaseController.class);

    /**
     * 客户端重定向
     *
     * @param url
     * @return
     */
    protected String redirect(String url) {
        return "redirect:" + url;
    }

    /**
     * 服务端重定向
     *
     * @param url
     * @return
     */
    protected String forward(String url) {
        return "forward:" + url;
    }

    /**
     * 初始化数据绑定 1. 将所有传递进来的String进行HTML编码，防止XSS攻击 2. 将字段中Date类型转换为String类型
     */
    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        // Date 类型转换
        binder.registerCustomEditor(Date.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) {
                setValue(DateUtils.parseDate(text));
            }
        });
    }

    /**
     * 获取当前请求对象
     *
     * @return
     */
    protected HttpServletRequest getRequest() {
        try {
            return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                    .getRequest();
        } catch (Exception e) {
            throw new RuntimeException("获取request失败", e);
        }
    }

    protected HttpSession getSession() {
        return getSession(getRequest());
    }

    protected HttpSession getSession(HttpServletRequest request) {
        return request.getSession();
    }

    protected <T extends BaseBean> Record getRecord(Class<T> modelCls, HttpServletRequest request) {
        try {
            return new Record(modelCls, WebUtil.getRequestParams(request));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    protected Map<String, Object> getRequestMap(HttpServletRequest request) {
        Map<String, Object> map = Maps.newHashMap();
        map.putAll(WebUtil.getRequestParams(request));
        return map;
    }

    protected Map<String, String> getParams(HttpServletRequest request) {
        return WebUtil.getRequestParams(request);
    }

    protected Map<String, Object> getNotBlankRequestMap(HttpServletRequest request) {
        Map<String, Object> map = Maps.newHashMap();
        map.putAll(WebUtil.getNotBlankRequestParams(request));
        return map;
    }

    protected Map<String, String> getNotBlankParams(HttpServletRequest request) {
        return WebUtil.getNotBlankRequestParams(request);
    }

    /**
     * Get model from http request.
     */
    public <T extends BaseBean> T getModel(Class<T> modelClass, HttpServletRequest request) {
        return Injector.injectModel(modelClass, request, false);
    }

    public <T extends BaseBean> T getModel(Class<T> modelClass, HttpServletRequest request,
            boolean skipConvertError) {
        return Injector.injectModel(modelClass, request, skipConvertError);
    }

    /**
     * Get model from http request.
     */
    public <T extends BaseBean> T getModel(Class<T> modelClass, String modelName,
            HttpServletRequest request) {
        return Injector.injectModel(modelClass, modelName, request, false);
    }

    public <T extends BaseBean> T getModel(Class<T> modelClass, String modelName,
            HttpServletRequest request, boolean skipConvertError) {
        return Injector.injectModel(modelClass, modelName, request, skipConvertError);
    }

    /**
     * 当用户提交数据时，使用此模板方法，比如保存，更新操作 成功统一返回code=1，失败返回code=0
     *
     * @param callack
     * @param exceptionInfo
     * @return
     */
    protected String doResult(MyExecuteCallback callack, String exceptionInfo) {
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
     * 构造ajax返回数据
     *
     * @return
     */
    protected String retMsg(String code, String msg) {
        return AjaxTemplate.getMsg(code, msg);
    }

    /**
     * 操作失败数据
     *
     * @return
     */
    protected String retFail() {
        return AjaxTemplate.fail();
    }

    protected String retFail(String msg) {
        return AjaxTemplate.fail(msg);
    }

    /**
     * 操作成功数据
     *
     * @return
     */
    protected String retSuccess() {
        return AjaxTemplate.success();
    }

    protected String retSuccess(String msg) {
        return AjaxTemplate.success(msg);
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

    private static final String emailAddressPattern = "\\b(^['_A-Za-z0-9-]+(\\.['_A-Za-z0-9-]+)*@([A-Za-z0-9-])+(\\.[A-Za-z0-9-]+)*((\\.[A-Za-z0-9]{2,})|(\\.[A-Za-z0-9]{2,}\\.[A-Za-z0-9]{2,}))$)\\b";

    /**
     * Validate Required.
     */
    protected void validateRequired(String field, String errorMsg) {
        String value = getRequest().getParameter(field);
        // 经测试,无输入时值为"",跳格键值为"\t",输入空格则为空格" "
        if (StringUtils.isBlank(value)) {
            addError(errorMsg);
        }
    }

    /**
     * Validate integer.
     */
    protected void validateInteger(String field, int min, int max, String errorMsg) {
        try {
            String value = getRequest().getParameter(field);
            int temp = Integer.parseInt(value);
            if (temp < min || temp > max) {
                addError(errorMsg);
            }
        } catch (Exception e) {
            addError(errorMsg);
        }
    }

    /**
     * Validate long.
     */
    protected void validateLong(String field, long min, long max, String errorMsg) {
        try {
            String value = getRequest().getParameter(field);
            long temp = Long.parseLong(value);
            if (temp < min || temp > max) {
                addError(errorMsg);
            }
        } catch (Exception e) {
            addError(errorMsg);
        }
    }

    /**
     * Validate long.
     */
    protected void validateLong(String field, String errorMsg) {
        try {
            String value = getRequest().getParameter(field);
            Long.parseLong(value);
        } catch (Exception e) {
            addError(errorMsg);
        }
    }

    /**
     * Validate double.
     */
    protected void validateDouble(String field, double min, double max, String errorMsg) {
        try {
            String value = getRequest().getParameter(field);
            double temp = Double.parseDouble(value);
            if (temp < min || temp > max) {
                addError(errorMsg);
            }
        } catch (Exception e) {
            addError(errorMsg);
        }
    }

    /**
     * Validate double.
     */
    protected void validateDouble(String field, String errorMsg) {
        try {
            String value = getRequest().getParameter(field);
            Double.parseDouble(value);
        } catch (Exception e) {
            addError(errorMsg);
        }
    }

    /**
     * Validate date.
     */
    protected void validateDate(String field, Date min, Date max, String errorMsg) {
        try {
            String value = getRequest().getParameter(field);
            // Date temp = Date.valueOf(value); 为了兼容 64位 JDK
            Date temp = new SimpleDateFormat(datePattern).parse(value);
            if (temp.before(min) || temp.after(max)) {
                addError(errorMsg);
            }
        } catch (Exception e) {
            addError(errorMsg);
        }
    }

    // TODO set in Const and config it in Constants. TypeConverter do the same
    // thing.
    private static final String datePattern = "yyyy-MM-dd";

    /**
     * Validate date. Date formate: yyyy-MM-dd
     */
    protected void validateDate(String field, String min, String max, String errorMsg) {
        // validateDate(field, Date.valueOf(min), Date.valueOf(max), errorKey,
        // errorMessage); 为了兼容 64位 JDK
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(datePattern);
            validateDate(field, sdf.parse(min), sdf.parse(max), errorMsg);
        } catch (ParseException e) {
            addError(errorMsg);
        }
    }

    /**
     * Validate equal field. Usually validate password and password again
     */
    protected void validateEqualField(String field_1, String field_2, String errorMsg) {
        String value_1 = getRequest().getParameter(field_1);
        String value_2 = getRequest().getParameter(field_2);
        if (value_1 == null || value_2 == null || (!value_1.equals(value_2))) {
            addError(errorMsg);
        }
    }

    /**
     * Validate equal string.
     */
    protected void validateEqualString(String s1, String s2, String errorMsg) {
        if (s1 == null || s2 == null || (!s1.equals(s2))) {
            addError(errorMsg);
        }
    }

    /**
     * Validate equal integer.
     */
    protected void validateEqualInteger(Integer i1, Integer i2, String errorMsg) {
        if (i1 == null || i2 == null || (i1.intValue() != i2.intValue())) {
            addError(errorMsg);
        }
    }

    /**
     * Validate email.
     */
    protected void validateEmail(String field, String errorMessage) {
        validateRegex(field, emailAddressPattern, false, errorMessage);
    }

    /**
     * Validate URL.
     */
    protected void validateUrl(String field, String errorMsg) {
        try {
            String value = getRequest().getParameter(field);
            // URL doesn't understand the https protocol, hack it
            if (value.startsWith("https://")) {
                value = "http://" + value.substring(8);
            }
            new URL(value);
        } catch (MalformedURLException e) {
            addError(errorMsg);
        }
    }

    /**
     * Validate regular expression.
     */
    protected void validateRegex(String field, String regExpression, boolean isCaseSensitive,
            String errorMsg) {
        String value = getRequest().getParameter(field);
        if (value == null) {
            addError(errorMsg);
            return;
        }
        Pattern pattern = isCaseSensitive ? Pattern.compile(regExpression)
                : Pattern.compile(regExpression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(value);
        if (!matcher.matches()) {
            addError(errorMsg);
        }
    }

    /**
     * Validate regular expression and case sensitive.
     */
    protected void validateRegex(String field, String regExpression, String errorMessage) {
        validateRegex(field, regExpression, true, errorMessage);
    }

    protected void validateString(String field, boolean notBlank, int minLen, int maxLen,
            String errorMsg) {
        String value = getRequest().getParameter(field);
        if (value == null || value.length() < minLen || value.length() > maxLen) {
            addError(errorMsg);
        } else if (notBlank && "".equals(value.trim())) {
            addError(errorMsg);
        }
    }

    /**
     * Validate string.
     */
    protected void validateString(String field, int minLen, int maxLen, String errorMessage) {
        validateString(field, true, minLen, maxLen, errorMessage);
    }

    protected void addError(String errorMsg) {
        throw new ValidateException(errorMsg);
    }

}
