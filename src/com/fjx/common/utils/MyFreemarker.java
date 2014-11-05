package com.fjx.common.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;

import org.apache.commons.lang3.StringUtils;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;


/**
 * freemark处理类
 * @author fengjx xd-fjx@qq.com
 * @date 2014年10月26日
 */
public class MyFreemarker {
	
	private static Map<String, MyFreemarker> myFreemarkers = new HashMap<String, MyFreemarker>();
	public static final String defaultEncoding = "UTF-8";
	
	//创建Freemarker配置实例
	private Configuration cfg = new Configuration();
	
	/**
	 * 不允许其他类实例化
	 * @param basePath
	 * @throws Exception
	 */
	private MyFreemarker(String basePath) throws Exception{
		new MyFreemarker(basePath, null);
	}
	
	/**
	 * 不允许其他类实例化
	 * @param basePath
	 * @param encoding
	 * @throws Exception
	 */
	private MyFreemarker(String basePath, String encoding) throws Exception{
		if(null == basePath || "".equals(basePath)){
			throw new Exception ("basePath为空，MyFreemarker实例化失败");
		}
		if(StringUtils.isBlank(encoding)){
			encoding = defaultEncoding;
		}
		basePath = basePath.replaceAll("\\\\", "/");
		cfg.setDirectoryForTemplateLoading(new File(basePath));
		cfg.setDefaultEncoding(encoding);
	}
	
	/**
	 * 不允许其他类实例化
	 * @param context
	 * @param basePath
	 * @throws Exception
	 */
	private MyFreemarker(ServletContext context, String basePath) throws Exception{
		new MyFreemarker(context, basePath, null);
	}
	
	/**
	 * 不允许其他类实例化
	 */
	private MyFreemarker(ServletContext context, String basePath, String encoding) throws Exception{
		MyFreemarker myFreemarker = myFreemarkers.get(basePath); //从缓存中取出来
		if(myFreemarker == null){//如果缓存中没有
			cfg.setServletContextForTemplateLoading(context, basePath);
			if(StringUtils.isBlank(encoding)){
				encoding = defaultEncoding;
			}
			cfg.setDefaultEncoding(encoding);
			myFreemarkers.put(basePath, this);	//缓存到map
		}
	}
	
	/**
	 * 获得实例
	 * 为支持不同的模板路径，此处不采用单例模式，单为了节约资源，将不同模板路径的实例缓存起来
	 * @param _basePath
	 * @return
	 * @throws IOException
	 */
	public static MyFreemarker getInstance(String basePath)throws Exception{
		return getInstance(basePath, null);
	}
	/**
	 * 获得实例
	 * @param _basePath freemark配置跟路径（物理路径）
	 * @param _encoding
	 * @return
	 * @throws Exception
	 */
	public static MyFreemarker getInstance(String basePath, String encoding)throws Exception{
		MyFreemarker myFreemarker = myFreemarkers.get(basePath); //从缓存中取出来
		if(myFreemarker == null){//如果缓存中没有
			if(StringUtils.isBlank(encoding)){
				encoding = defaultEncoding;
			}
			myFreemarker = new MyFreemarker(basePath,encoding);
			myFreemarkers.put(basePath, myFreemarker);	//缓存到map
		}
		return myFreemarker;
	}
	
	/**
	 * 
	 * @param _basePath
	 * @param _encoding
	 * @return
	 * @throws Exception
	 */
	public static MyFreemarker getInstance4Servlet(ServletContext context, String basePath, String encoding)throws Exception{
		return new MyFreemarker(context, basePath, encoding);
	}
	
	public static MyFreemarker getInstance4Servlet(ServletContext context, String basePath)throws Exception{
		return getInstance4Servlet(context, basePath, null);
	}
	
	
	/**
	 * 
	 * @param root
	 * @param templatePath
	 * @param htmlPath
	 * @return 返回生成的文件路径
	 * @throws IOException
	 * @throws TemplateException
	 */
	public String createHTML(Map root, String templatePath, String htmlPath ) throws IOException, TemplateException{
		Template template = null;
		String flag = null;
		File htmlFile = null;
        Writer out = null;
		try {
			//htmlPath = basePath+"html/"+htmlPath;
			htmlPath = htmlPath.replaceAll("\\\\", "/");
			htmlFile = new File(htmlPath);
			//out = new FileWriter(new File(basePath+"html/"+htmlPath),"");
			out =  new BufferedWriter(new OutputStreamWriter(new FileOutputStream(htmlFile),defaultEncoding));
			//加载模板文件
			template = cfg.getTemplate(templatePath);
			template.setEncoding(cfg.getDefaultEncoding());
			//生成html
			template.process(root, out);
			flag = htmlPath;
		}finally{
			if(out != null){
				try {
					out.flush();
				} catch (IOException e) {
					throw e;
				} finally{
					out = null;
				}
			}
		}
		return flag;
	}
	
	/**
	 * 解析ftl文件，返回字符串
	 * @param root
	 * @param templatePath
	 * @return 
	 * @throws IOException
	 * @throws TemplateException
	 */
	public String process(Map root, String templatePath) throws IOException, TemplateException{
		String flag = null;
        Writer out = null;
        Template template = null;
		try {
			out = new StringWriter();
			//加载模板文件
			template = cfg.getTemplate(templatePath);
			template.setEncoding(cfg.getDefaultEncoding());
			//解析ftl文件
			template.process(root, out);
			flag = out.toString();
		}finally{
			if(out != null){
				try {
					out.flush();
				} catch (IOException e) {
					throw e;
				} finally{
					out = null;
				}
			}
		}
		return flag;
	}
	

}
