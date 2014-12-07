package com.fjx.wechat.mysdk.tools;

import com.fjx.common.framework.system.exception.MyException;
import org.apache.log4j.Logger;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import java.io.*;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class WeChatUtil {
	
	private static final Logger logger = Logger.getLogger(WeChatUtil.class);
	
	//请求方式
	public static final String TYPE_GET = "GET";
	public static final String TYPE_POST = "POST";
	

	/**
	 * 发起https请求并获取结果
	 * @param requestUrl 请求地址
	 * @param requestMethod 请求方式（GET、POST）
	 * @param outputStr 提交的数据(字符串)
	 * @return String
	 */
	public static String httpRequest(String requestUrl, String requestMethod, String outputStr) throws MyException {
		StringBuffer buffer = new StringBuffer();
		
		OutputStream outputStream = null;
		InputStream inputStream = null;
		InputStreamReader inputStreamReader = null;
		BufferedReader bufferedReader = null;
		HttpURLConnection httpUrlConn = null;
		
		try {
			// 创建SSLContext对象，并使用我们指定的信任管理器初始化
			TrustManager[] tm = { new MyX509TrustManager() };
			SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
			sslContext.init(null, tm, new java.security.SecureRandom());
			// 从上述SSLContext对象中得到SSLSocketFactory对象
			SSLSocketFactory ssf = sslContext.getSocketFactory();
			URL url = new URL(requestUrl);
			if(requestUrl.startsWith("https")){
				httpUrlConn = (HttpsURLConnection) url.openConnection();
				((HttpsURLConnection) httpUrlConn).setSSLSocketFactory(ssf);
			}else{
				httpUrlConn = (HttpURLConnection) url.openConnection();
			}
			httpUrlConn.setDoOutput(true);
			httpUrlConn.setDoInput(true);
			httpUrlConn.setUseCaches(false);
			// 设置请求方式（GET/POST）
			httpUrlConn.setRequestMethod(requestMethod);
			if (TYPE_GET.equalsIgnoreCase(requestMethod)){
				httpUrlConn.connect();
			}
			// 当有数据需要提交时
			if (null != outputStr) {
				outputStream = httpUrlConn.getOutputStream();
				logger.info("post 数据"+outputStr);
				// 注意编码格式，防止中文乱码
				outputStream.write(outputStr.getBytes("UTF-8"));
				outputStream.close();
			}
			// 将返回的输入流转换成字符串
			inputStream = httpUrlConn.getInputStream();
			inputStreamReader = new InputStreamReader(inputStream, "utf-8");
			bufferedReader = new BufferedReader(inputStreamReader);

			String str = null;
			while ((str = bufferedReader.readLine()) != null) {
				buffer.append(str);
			}
		} catch (ConnectException ce) {
			throw new MyException("url连接超时："+requestUrl,ce);
		} catch (Exception e) {
			throw new MyException("请求发生异常:{}",e);
		}finally{
			// 释放资源
			if(null != httpUrlConn){
				httpUrlConn.disconnect();
			}
			if(null != bufferedReader){
				try {
					bufferedReader.close();
				} catch (IOException e) {
					bufferedReader = null;
					throw new MyException("bufferedReader 关闭异常",e);
				}
			}
			if(null != inputStreamReader){
				try {
					inputStreamReader.close();
				} catch (IOException e) {
					inputStreamReader = null;
					throw new MyException("inputStreamReader 关闭异常",e);
				}
			}
			if(null != outputStream){
				try {
					outputStream.close();
				} catch (IOException e) {
					outputStream = null;
					throw new MyException("outputStream 关闭异常",e);
				}
			}
			if(null != inputStream){
				try {
					inputStream.close();
				} catch (IOException e) {
					inputStream = null;
					logger.error("inputStream 关闭异常", e);
				}
			}
		}
		return buffer.toString();
	}
	
	/**
	 * 发送http GET请求取得返回的输入流
	 * @param requestUrl 请求地址
	 * @return InputStream
	 */
	public static InputStream httpRequest(String requestUrl)throws Exception {
		InputStream inputStream = null;
		try {
			URL url = new URL(requestUrl);
			HttpURLConnection httpUrlConn = (HttpURLConnection) url.openConnection();
			httpUrlConn.setDoInput(true);
			httpUrlConn.setRequestMethod(TYPE_GET);
			httpUrlConn.connect();
			// 获得返回的输入流
			inputStream = httpUrlConn.getInputStream();
		} catch (Exception e) {
			throw new Exception(e);
		}
		return inputStream;
	}
	

}