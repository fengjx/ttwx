package com.fjx.wechat.base.tools;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.util.JSONUtils;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSONException;
import com.fjx.wechat.base.constants.WechatApiConstants;
import com.fjx.wechat.base.vo.menu.Menu;
import com.fjx.wechat.base.vo.security.AccessToken;
import com.fjx.wechat.base.vo.security.Oauth2AccessToken;
import com.fjx.wechat.base.vo.user.UserInfo;

public class WeChatUtil {
	
	private static final Logger logger = Logger.getLogger(WeChatUtil.class);
	
	//请求方式
	public static final String TYPE_GET = "GET";
	public static final String TYPE_POST = "POST";
	
	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		//String menuJson = "{'button':[{'type':'view','name':'开通特权','url':'http://wx.qq.com/cgi-bin/mmwebwx-bin/webwxreadtemplate?t=weixin_wo_rights'},{'name':'惊喜优惠','sub_button':[{'type':'click','name':'最新优惠','key':'click_key_affordable'},{'type':'view','name':'微信沃卡','url':'http://www.10010.com/static/homepage/subjectpage/25100000115292.html'},{'type':'view','name':'存费送机','url':'http://mall.10010.com/goodsdetail/511305077069.html'},{'type':'view','name':'刮刮乐','url':'http://1.houhou.duapp.com/wechat/guaguale.html'},{'type':'view','name':'晒单赢大奖','url':'http://qq.75510010.com/act/showOrderAction.action'}]},{'name':'我的服务','sub_button':[{'type':'view','name':'附近网点','url':'http://1.houhou.duapp.com/wechat/map/index.html'},{'type':'view','name':'话费充值','url':'http://store.hk.chinaunicom.com/jsp/sys/mall/if_phone.jsp'},{'type':'view','name':'维权','url':'http://iservice.10010.com/elecService/weixin.html'},{'type':'click','name':'账户查询','key':'click_key_accountQuery'},{'type':'view','name':'业务办理','url':'http://iservice.10010.com/index_.html'}]}]}";
		//String menuJson = MenuServiceImpl.getMenuJson();
		//System.out.println(menuJson);
		//String accessToken = getAccessToken().getToken();
		//createMenu(menuJson, accessToken);
		//getAccessToken();
		
		//getOauth2AccessToken("01e21f9e35dff8e0b0d1c741a802eec5");
		//getOauth2Userinfo("0113b9d615a809db7b81a34d243da0a4");
		
//		System.out.println(getUserGroup("okLxMtw0nCyfHgbL1GzykbxtDnbA",WechatApiConstants.WECHAT_APPID, WechatApiConstants.WECHAT_APPSECRET));
	}
	
	/**
	 * 菜单创建（POST） 限100（次/天）
	 * @param menuJson
	 * @param accessToken
	 * @return
	 * 返回结果
	 * 正确时的返回JSON数据包如下：
	 * {"errcode":0,"errmsg":"ok"}
	 * 错误时的返回JSON数据包如下（示例为无效菜单名长度）：
	 * {"errcode":40018,"errmsg":"invalid button name size"}
	 * @throws Exception 
	 */
//	private static JSONObject createMenu(String menuJson, String accessToken) throws Exception {
//		String url = WeChatUtil.WECHAT_MENU_CREATE_URL.replace("#ACCESS_TOKEN#", accessToken);
//		logger.info("创建菜单 请求URL："+url);
//		JSONObject json = JSONObject.fromObject(httpRequest(url,"POST",menuJson));
//		logger.info("菜单创建返回值：{}"+json.toString());
//		return json;
//	}
	
	public static JSONObject createMenu(Menu menu,String appid, String appSecret) throws Exception {
//		AccessToken accessToken = WechatContext.getAccessToken();
		AccessToken accessToken = getAccessToken(appid, appSecret);
		
		if(null == accessToken){
			logger.error("从WechatContext中获取不到accessToken，重新获取");
			accessToken = getAccessToken(appid, appSecret);
		}
		String token = accessToken.getToken();
		String url = WechatApiConstants.WECHAT_MENU_CREATE_URL.replace("#ACCESS_TOKEN#", token);
		logger.info("创建菜单 请求URL："+url);
		JSONObject json = JSONObject.fromObject(httpRequest(url,"POST",getJson(menu)));
		logger.info("菜单创建返回值：{}"+json.toString());
		return json;
	}
	
	
	/**
	 * 获得accessToken
	 * @return json.get("access_token")+"";
	 * @throws Exception 
	 */
	public static JSONObject getAccessToken2Json(String appid, String appSecret) throws Exception{
		String url = WechatApiConstants.WECHAT_ACCESS_TOKEN_URL.replace("#APPID#", appid).replace("#APPSECRET#", appSecret);
		logger.info("获得accessToken URL："+url);
		JSONObject json = JSONObject.fromObject(httpRequest(url,"GET",null));
		logger.info("获得accessToken 返回值：{}"+json.toString());
		return json;
	}
	
	/**
	* 获取access_token
	* @param appid 凭证
	* @param appsecret 密钥
	* @return
	*/
	public static AccessToken getAccessToken(String appid, String appSecret)throws Exception {
	    AccessToken accessToken = null;
	    JSONObject jsonObject = null;
	    try {
			//从配置文件读取AppId和AppSecret（开发者凭据）
		    jsonObject = getAccessToken2Json(appid, appSecret);
		    // 如果请求成功
		    if (null != jsonObject && jsonObject.size()>0) {
		            accessToken = new AccessToken();
		            accessToken.setToken(jsonObject.getString("access_token"));
		            accessToken.setExpiresIn(jsonObject.getInt("expires_in"));
		    }
	    } catch (JSONException e) {
            accessToken = null;
            logger.error("获取token失败 errcode:{} errmsg:{}"+jsonObject.toString(),e);
            throw e;
        }
	    return accessToken;
	}
	
	/**
	 * 获取页面授权AccessToken
	 * @param code	微信转发获取的code参数，code只能使用一次，5分钟未被使用自动过
	 * @return
	 * @throws Exception
	 */
	public static Oauth2AccessToken  getOauth2AccessToken(String code,String appid, String appSecret) throws Exception{
		Oauth2AccessToken token = null;
		try {
			String url = WechatApiConstants.WECHAT_OAUTH2_ACCESS_TOKEN_URL.replace("#APPID#", appid)
													   .replace("#APPSECRET#", appSecret)
													   .replace("#CODE#", code);
			String res = httpRequest(url, TYPE_GET, null);
			logger.debug("获取Oauth2AccessToken："+res);
			JSONObject json = JSONObject.fromObject(res);
			if(null != json && null != json.get("access_token")){
				token = (Oauth2AccessToken) JSONObject.toBean(json, Oauth2AccessToken.class);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return token;
	}
	
	/**
	 * 获取页面授权ORefreshToken
	 * @param refresh_token	填写通过access_token获取到的refresh_token参数
	 * @return
	 * @throws Exception
	 */
	public static Oauth2AccessToken  getOauth2RefreshToken(String refresh_token, String appid) throws Exception{
		Oauth2AccessToken token = null;
		String url = WechatApiConstants.WECHAT_OAUTH2_REFRESH_TOKEN_URL.replace("#APPID#", appid)
												    .replace("#REFRESH_TOKEN#", refresh_token);
		String res = httpRequest(url, TYPE_GET, null);
		logger.debug("获取Oauth2AccessToken："+res);
		JSONObject json = JSONObject.fromObject(res);
		if(null != json && null != json.getString("access_token")){
			token = (Oauth2AccessToken) JSONObject.toBean(json, Oauth2AccessToken.class);
		}
		return token;
	}
	
	
	//获取页面授权获取userinfo
	public static JSONObject getOauth2UserinfoByAccessToken(Oauth2AccessToken accessToken) throws Exception{
		if(null != accessToken){	//获取到accessToken
			String url = WechatApiConstants.WECHAT_OAUTH2_USERINFO_URL.replace("#ACCESS_TOKEN#", accessToken.getAccess_token())
					   							   .replace("#OPENID#", accessToken.getOpenid());
			String res = httpRequest(url, TYPE_GET, url);
			logger.debug("获取getOauth2Userinfo："+res);
			JSONObject json = JSONObject.fromObject(res);
			return json;
		}
		return null;
	}
	
	//获取页面授权获取userinfo
	public static UserInfo getOauth2Userinfo(String code, String appid, String appSecret) throws Exception{
		UserInfo userInfo = null;
		Oauth2AccessToken accessToken = getOauth2AccessToken(code,appid,appSecret);
		if(null != accessToken){	//获取到accessToken
			userInfo = new UserInfo();
			userInfo.setScope(accessToken.getScope());
			String url = WechatApiConstants.WECHAT_OAUTH2_USERINFO_URL.replace("#ACCESS_TOKEN#", accessToken.getAccess_token())
					   							   .replace("#OPENID#", accessToken.getOpenid());
			String res = httpRequest(url, TYPE_GET, url);
			logger.debug("获取getOauth2Userinfo："+res);
			JSONObject json = JSONObject.fromObject(res);
			if(null == json){
				return null;
			}else if(null != json.get("openid")){
				userInfo = (UserInfo) JSONObject.toBean(json, UserInfo.class);
			}else{
				userInfo.setOpenid(accessToken.getOpenid());
			}
		}
		return userInfo;
	}
	
	//获取页面授权获取userinfo
	public static String getUserGroup(String openid,String appid, String appSecret) throws Exception{
		AccessToken accessToken = getAccessToken(appid, appSecret);
		String url = WechatApiConstants.WECHAT_USER_GROUPS_URL.replace("#ACCESS_TOKEN#", accessToken.getToken());
		String param = "{\"openid\":\""+openid+"\"}";
		String res = httpRequest(url, TYPE_POST, param);
		logger.debug("获取getUserGroup："+res);
		JSONObject json = JSONObject.fromObject(res);
		if(null != json && StringUtils.isNotBlank(json.getString("groupid"))){
			return json.getString("groupid");
		}
		return null;
	}
	
	public static boolean isSubscribe(String openid, String appid, String appSecret) throws Exception{
		String gid = getUserGroup(openid,appid,appSecret);
		if(StringUtils.isNotBlank(gid)){	//所在组ID不为null表示已经关注
			return true;
		}
		return false;
	}
	
	/**
	 * 发起https请求并获取结果
	 * @param requestUrl 请求地址
	 * @param requestMethod 请求方式（GET、POST）
	 * @param outputStr 提交的数据(字符串)
	 * @return String
	 */
	public static String httpRequest(String requestUrl, String requestMethod, String outputStr) throws Exception {
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
			throw new Exception("url连接超时："+requestUrl,ce);
		} catch (Exception e) {
			throw new Exception("请求发生异常:{}",e);
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
					throw new Exception("bufferedReader 关闭异常",e);
				}
			}
			if(null != inputStreamReader){
				try {
					inputStreamReader.close();
				} catch (IOException e) {
					inputStreamReader = null;
					throw new Exception("inputStreamReader 关闭异常",e);
				}
			}
			if(null != outputStream){
				try {
					outputStream.close();
				} catch (IOException e) {
					outputStream = null;
					throw new Exception("outputStream 关闭异常",e);
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
	
	/**
	 * emoji表情转换(hex -> utf-16)
	 * @param hexEmoji
	 * @return
	 */
	public static String emoji(int hexEmoji) {
		return String.valueOf(Character.toChars(hexEmoji));
	}
	
	 /**
     * 将对象转成json字符串
     * @return
	 * @throws Exception 
     */
    public static String getJson(Object object) throws Exception {
    	if(null == object){
    		return "";
    	}
    	String tmp;
    	if(JSONUtils.isArray(object)){
    		tmp = JSONArray.fromObject(object).toString();
    	}else if(JSONUtils.isObject(object)){
    		tmp = JSONObject.fromObject(object).toString();
    	}else{
    		throw new Exception("非法的json对象");
    	}
    	return tmp;
    }
    
    public static String urlEncode(String source, String chartset) throws UnsupportedEncodingException{
    	String res = source;
    	res = URLEncoder.encode(source,chartset);
    	return res;
    }
    
    /** 
     * 将微信消息中的CreateTime转换成标准格式的时间（yyyy-MM-dd HH:mm:ss） 
     * CreateTime，表示1970年1月1日0时0分0秒至消息创建时所间隔的秒数，不是毫秒数！
     * @param createTime 消息创建时间 
     * @return 
     */  
    public static String formatCreateTime(String createTime) {  
        // 将微信传入的CreateTime转换成long类型，再乘以1000
        long msgCreateTime = Long.parseLong(createTime) * 1000L;  
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
        return format.format(new Date(msgCreateTime));  
    }  
    
}