
package com.fengjx.modules.common.servlet;

import com.fengjx.commons.ext.baidu.ueditor.ActionEnter;
import com.fengjx.commons.utils.LogUtil;
import com.fengjx.modules.common.constants.AppConfig;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;

/**
 * 百度编辑器上传
 *
 * @Created by FengJianxin on 2015/9/19.
 * @Email xd-fjx@qq.com
 */
public class UeditorServlet extends HttpServlet {

    private static final Logger LOG = LoggerFactory.getLogger(UeditorServlet.class);

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String rootPath = AppConfig.STATIC_PATH;
        write(new ActionEnter(req, rootPath).exec(), resp);
    }

    /**
     * 写出数据
     *
     * @param res 输出的字符串
     * @throws Exception
     */
    private void write(String res, HttpServletResponse response) {
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
