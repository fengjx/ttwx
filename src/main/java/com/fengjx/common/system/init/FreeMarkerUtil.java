package com.fengjx.common.system.init;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletContext;

import org.apache.log4j.Logger;

import com.fengjx.common.system.exception.MyRuntimeException;
import com.fengjx.common.utils.MyFreemarker;

import freemarker.template.TemplateException;

/**
 * freemark工具类
 * @author fengjx xd-fjx@qq.com
 * @date 2014年10月15日
 */
public class FreeMarkerUtil {
	
	private static final Logger logger = Logger.getLogger(FreeMarkerUtil.class);
	
	private static MyFreemarker myFreemarker ;
	
	/**
	 * 初始化
	 * @param ftlpath
	 * @throws Exception
	 */
	public static void init(String ftlpath) throws Exception{
		logger.debug("freemark初始化，ftlpath="+ftlpath);
		myFreemarker = MyFreemarker.getInstance(ftlpath);
	}
	
	/**
	 * 初始化
	 * @param ftlpath
	 * @throws Exception
	 */
	public static void init(String ftlpath, String ftlEncoding) throws Exception{
		logger.debug("freemark初始化，ftlpath="+ftlpath+"，ftlEncoding="+ftlEncoding);
		myFreemarker = MyFreemarker.getInstance(ftlpath, ftlEncoding);
	}
	
	/**
	 * 初始化
	 * @param context
	 * @param ftlpath
	 * @throws Exception
	 */
	public static void init(ServletContext context, String ftlpath) throws Exception{
		logger.debug("ServletContext freemark初始化，ftlpath="+ftlpath);
		myFreemarker = MyFreemarker.getInstance4Servlet(context,ftlpath);
	}
	
	/**
	 * 初始化
	 * @param context
	 * @param ftlpath
	 * @param encoding
	 * @throws Exception
	 */
	public static void init(ServletContext context, String ftlpath, String encoding) throws Exception{
		logger.debug("ServletContext freemark初始化，ftlpath="+ftlpath+"，encoding="+encoding);
		myFreemarker = MyFreemarker.getInstance4Servlet(context,ftlpath,encoding);
	}
	
	/**
	 * 解析ftl文件，生成文件
	 * @param root
	 * @param templatePath
	 * @param htmlPath
	 * @return 
	 * @throws IOException
	 * @throws TemplateException
	 */
	@SuppressWarnings("rawtypes")
	public static String createHTML(Map root, String templatePath, String htmlPath ){
		String res = null;
		try {
			res =  myFreemarker.createHTML(root, templatePath, htmlPath);
		} catch (IOException e) {
			throw new MyRuntimeException(templatePath+"，文件解析错误",e);
		} catch (TemplateException e) {
			throw new MyRuntimeException(templatePath+"，文件解析错误",e);
		}
		return res;
	}
	
	/**
	 * 解析ftl文件
	 * @param root
	 * @param templatePath
	 * @return 返回字符串
	 * @throws IOException
	 * @throws TemplateException
	 */
	@SuppressWarnings("rawtypes")
	public static String process(Map root, String templatePath){
		String res = null;
		try {
			res = myFreemarker.process(root, templatePath);
		} catch (IOException e) {
			throw new MyRuntimeException(templatePath+"文件解析错误",e);
		} catch (TemplateException e) {
			throw new MyRuntimeException(templatePath+"文件解析错误",e);
		}
		return res;
	}
	
}
