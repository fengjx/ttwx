package com.fjx.wechat.extension.api.restful;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.fjx.wechat.base.tools.WeChatUtil;
import com.fjx.wechat.base.vo.resp.Article;


/**
 * 优酷视频搜索
 * @author fengjx
 * @date 2014年2月8日
 */
public class YoukuVideoServiceApi {
	
	private static final Logger logger = Logger.getLogger(YoukuVideoServiceApi.class);
	
	// 测试方法
	public static void main(String[] args) {
		List<Article> articles = searchVideo("老男孩");
		for(Article article : articles){
			System.out.println(article.getTitle());
			System.out.println(article.getPicUrl());
			System.out.println(article.getDescription());
			System.out.println(article.getUrl());
		}
	}
	
	public static List<Article> searchVideo(String keyword) {
		List<Article> articles = null;
		// 优酷视频搜索接口
		String requestUrl = "https://openapi.youku.com/v2/searches/video/by_keyword.json?client_id=ce5c8483c6bd1c7c&keyword={KEYWORD}";
		requestUrl = requestUrl.replace("{KEYWORD}", keyword);
		
		// 查询并解析结果
		try {
			// 查询并获取返回结果
			String json = WeChatUtil.httpRequest(requestUrl,WeChatUtil.TYPE_GET,null);
			// 将json字符串转换成json对象
			JSONObject jsonObject = JSONObject.fromObject(json);
			// 取出视频列表
			if(null != jsonObject.getString("total") && !"0".equals(jsonObject.getString("total"))){
				JSONArray jsonArray =  jsonObject.getJSONArray("videos");
				articles = new ArrayList<Article>();
				Article article = null;
				for(int i = 0; i < jsonArray.size() && i<7; i++){
					JSONObject object = jsonArray.getJSONObject(i);
					article = new Article();
					article.setDescription(object.getString("title"));
					article.setPicUrl(object.getString("thumbnail"));
					article.setTitle(object.getString("tags"));
					article.setUrl(object.getString("link"));
					articles.add(article);
				}
			}
		} catch (Exception e) {
			logger.error("调用优酷视频查询接口异常",e);
		}
		return articles;
	}
	
	/** 
     * 视频点播使用指南 
     * @return 
     */  
    public static String getVideoUsage() {  
        StringBuffer buffer = new StringBuffer();  
        buffer.append("视频点播操作指南").append("\n\n");  
        buffer.append("回复：视频+关键词").append("\n");  
        buffer.append("例如：视频NBA").append("\n");  
        buffer.append("回复：“?”显示主菜单");  
        return buffer.toString();  
    }
	
}
