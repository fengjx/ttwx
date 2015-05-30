
package com.fengjx.ttwx.common.utils;

import freemarker.core.ParseException;
import freemarker.template.*;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;

/**
 * freemark处理类
 * 
 * @author fengjx xd-fjx@qq.com
 * @date 2014年10月26日
 */
public class MyFreemarker {

    private static Map<String, MyFreemarker> myFreemarkers = new HashMap<String, MyFreemarker>();
    public static final String defaultEncoding = "UTF-8";

    // 创建Freemarker配置实例
    private Configuration cfg = new Configuration();

    /**
     * 不允许其他类实例化
     * 
     * @param basePath
     */
    private MyFreemarker(String basePath) {
        new MyFreemarker(basePath, null);
    }

    /**
     * 不允许其他类实例化
     * 
     * @param basePath
     * @param encoding
     */
    private MyFreemarker(String basePath, String encoding) {
        if (null == basePath || "".equals(basePath)) {
            throw new RuntimeException("basePath为空，MyFreemarker实例化失败");
        }
        if (StringUtils.isBlank(encoding)) {
            encoding = defaultEncoding;
        }
        basePath = basePath.replaceAll("\\\\", "/");
        try {
            cfg.setDirectoryForTemplateLoading(new File(basePath));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        cfg.setDefaultEncoding(encoding);
    }

    /**
     * 不允许其他类实例化
     * 
     * @param context
     * @param basePath
     */
    private MyFreemarker(ServletContext context, String basePath) {
        new MyFreemarker(context, basePath, null);
    }

    /**
     * 不允许其他类实例化
     */
    private MyFreemarker(ServletContext context, String basePath, String encoding) {
        MyFreemarker myFreemarker = myFreemarkers.get(basePath); // 从缓存中取出来
        if (myFreemarker == null) {// 如果缓存中没有
            cfg.setServletContextForTemplateLoading(context, basePath);
            if (StringUtils.isBlank(encoding)) {
                encoding = defaultEncoding;
            }
            cfg.setDefaultEncoding(encoding);
            myFreemarkers.put(basePath, this); // 缓存到map
        }
    }

    /**
     * 获得实例 为支持不同的模板路径，此处不采用单例模式，单为了节约资源，将不同模板路径的实例缓存起来
     * 
     * @param basePath
     * @return
     */
    public static MyFreemarker getInstance(String basePath) {
        return getInstance(basePath, null);
    }

    /**
     * 获得实例
     * 
     * @param basePath freemark配置跟路径（物理路径）
     * @param encoding
     * @return
     */
    public static MyFreemarker getInstance(String basePath, String encoding) {
        MyFreemarker myFreemarker = myFreemarkers.get(basePath); // 从缓存中取出来
        if (myFreemarker == null) {// 如果缓存中没有
            if (StringUtils.isBlank(encoding)) {
                encoding = defaultEncoding;
            }
            myFreemarker = new MyFreemarker(basePath, encoding);
            myFreemarkers.put(basePath, myFreemarker); // 缓存到map
        }
        return myFreemarker;
    }

    /**
     * @param context
     * @param basePath
     * @param encoding
     * @return
     */
    public static MyFreemarker getInstance4Servlet(ServletContext context, String basePath,
            String encoding) {
        return new MyFreemarker(context, basePath, encoding);
    }

    public static MyFreemarker getInstance4Servlet(ServletContext context, String basePath) {
        return getInstance4Servlet(context, basePath, null);
    }

    /**
     * @param root
     * @param templatePath
     * @param htmlPath
     * @return 返回生成的文件路径
     */
    public String createHTML(Map root, String templatePath, String htmlPath) {
        Template template = null;
        String flag = null;
        File htmlFile = null;
        Writer out = null;
        try {
            // htmlPath = basePath+"html/"+htmlPath;
            htmlPath = htmlPath.replaceAll("\\\\", "/");
            htmlFile = new File(htmlPath);
            // out = new FileWriter(new File(basePath+"html/"+htmlPath),"");
            out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(htmlFile),
                    defaultEncoding));
            // 加载模板文件
            template = cfg.getTemplate(templatePath);
            template.setEncoding(cfg.getDefaultEncoding());
            // 生成html
            template.process(root, out);
            flag = htmlPath;
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
        return flag;
    }

    /**
     * 解析ftl文件，返回字符串
     * 
     * @param root
     * @param templatePath
     * @return
     */
    public String process(Map root, String templatePath) {
        String flag = null;
        Writer out = null;
        Template template = null;
        try {
            out = new StringWriter();
            // 加载模板文件
            template = cfg.getTemplate(templatePath);
            template.setEncoding(cfg.getDefaultEncoding());
            // 解析ftl文件
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
