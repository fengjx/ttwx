package com.fjx.common.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.net.URL;

import org.apache.commons.lang3.StringUtils;


/**
 * @author
 * @Date: 2009-5-11 Time: 13:34:10 模拟Http访问的工具类
 */
public class HttpConnectionUtil {
	
	//读取HTML文件内容
	public static String getHttpContent(String url) {
		return getHttpContent(url, "UTF-8");
	}

	/**
	 * 读取文件内容 本地形式
	 * @param url
	 * @param charSet
	 * @return
	 */
	public static String getHttpContent(String path, String charSet) {
		StringBuffer sbStr = new StringBuffer();
		try {
			File ff = new File(path);
			InputStreamReader read = new InputStreamReader(new FileInputStream(
					ff), "UTF-8");
			BufferedReader ins = new BufferedReader(read);
			String dataLine = "";
			while (null != (dataLine = ins.readLine())) {
				sbStr.append(dataLine);
			}
			ins.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		return sbStr.toString();
	}

	/**
	 * 读取编辑器内容  网络形式  需调整
	 * @param htmlURL
	 * @return
	 * @throws Exception
	 */
	public static String getHtmlContent(String htmlURL) throws Exception {
		if(StringUtils.isBlank(htmlURL)){
			return null;
		}
		URL url = null;
		String rowContent = "";
		StringBuffer htmlContent = new StringBuffer();
		url = new URL(htmlURL);
		BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream(), "utf-8"));
		while ((rowContent = in.readLine()) != null) {
			htmlContent.append(rowContent);
		}
		in.close();
		return htmlContent.toString();

	}

}