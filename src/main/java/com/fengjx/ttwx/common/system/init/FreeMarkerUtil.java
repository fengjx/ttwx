
package com.fengjx.ttwx.common.system.init;

import com.fengjx.ttwx.common.utils.MyFreemarker;

import org.slf4j.LoggerFactory;

import java.util.Map;

import javax.servlet.ServletContext;

/**
 * freemark工具类
 * 
 * @author fengjx xd-fjx@qq.com
 * @date 2014年10月15日
 */
public class FreeMarkerUtil {

    private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(FreeMarkerUtil.class);

    private static MyFreemarker myFreemarker;

    /**
     * 初始化
     * 
     * @param ftlpath
     * @throws Exception
     */
    public static void init(String ftlpath) throws Exception {
        LOG.debug("freemark初始化，ftlpath=" + ftlpath);
        myFreemarker = MyFreemarker.getInstance(ftlpath);
    }

    /**
     * 初始化
     * 
     * @param ftlpath
     * @throws Exception
     */
    public static void init(String ftlpath, String ftlEncoding) throws Exception {
        LOG.debug("freemark初始化，ftlpath=" + ftlpath + "，ftlEncoding=" + ftlEncoding);
        myFreemarker = MyFreemarker.getInstance(ftlpath, ftlEncoding);
    }

    /**
     * 初始化
     * 
     * @param context
     * @param ftlpath
     * @throws Exception
     */
    public static void init(ServletContext context, String ftlpath) throws Exception {
        LOG.debug("ServletContext freemark初始化，ftlpath=" + ftlpath);
        myFreemarker = MyFreemarker.getInstance4Servlet(context, ftlpath);
    }

    /**
     * 初始化
     * 
     * @param context
     * @param ftlpath
     * @param encoding
     * @throws Exception
     */
    public static void init(ServletContext context, String ftlpath, String encoding)
            throws Exception {
        LOG.debug("ServletContext freemark初始化，ftlpath=" + ftlpath + "，encoding=" + encoding);
        myFreemarker = MyFreemarker.getInstance4Servlet(context, ftlpath, encoding);
    }

    /**
     * 解析ftl文件，生成文件
     * 
     * @param root
     * @param templatePath
     * @param htmlPath
     * @return
     */
    @SuppressWarnings("rawtypes")
    public static String createHTML(Map root, String templatePath, String htmlPath) {
        String res = null;
        res = myFreemarker.createHTML(root, templatePath, htmlPath);
        return res;
    }

    /**
     * 解析ftl文件
     * 
     * @param root
     * @param templatePath
     * @return 返回字符串
     */
    @SuppressWarnings("rawtypes")
    public static String process(Map root, String templatePath) {
        String res = null;
        res = myFreemarker.process(root, templatePath);
        return res;
    }

}
