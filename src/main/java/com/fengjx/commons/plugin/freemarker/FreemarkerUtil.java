
package com.fengjx.commons.plugin.freemarker;

import freemarker.core.ParseException;
import freemarker.template.*;
import org.apache.commons.io.IOUtils;

import java.io.*;
import java.util.Map;

/**
 * Freemarker工具类
 *
 * @Created by FengJianxin on 2015/8/22.
 * @Email xd-fjx@qq.com
 */
public class FreemarkerUtil {

    private static Configuration cfg;

    protected static void init(Configuration cfg) {
        FreemarkerUtil.cfg = cfg;
    }

    /**
     * @param root
     * @param templatePath
     * @param htmlPath
     */
    public static String createHTML(Map root, String templatePath, String htmlPath) {
        Template template = null;
        File htmlFile = null;
        Writer out = null;
        try {
            htmlPath = htmlPath.replaceAll("\\\\", "/");
            htmlFile = new File(htmlPath);
            out = new BufferedWriter(
                    new OutputStreamWriter(new FileOutputStream(htmlFile), "UTF-8"));
            template = cfg.getTemplate(templatePath);
            template.process(root, out);
            return htmlPath;
        } catch (MalformedTemplateNameException e) {
            throw new RuntimeException(e);
        } catch (TemplateException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        } catch (TemplateNotFoundException e) {
            throw new RuntimeException(e);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            IOUtils.closeQuietly(out);
        }

    }

    /**
     * @param root
     * @param templatePath
     * @return
     */
    public static String process(Map root, String templatePath) {
        String flag = null;
        Writer out = null;
        Template template = null;
        try {
            out = new StringWriter();
            template = cfg.getTemplate(templatePath);
            template.process(root, out);
            flag = out.toString();
        } catch (MalformedTemplateNameException e) {
            throw new RuntimeException(e);
        } catch (TemplateException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        } catch (TemplateNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            IOUtils.closeQuietly(out);
        }
        return flag;
    }

}
