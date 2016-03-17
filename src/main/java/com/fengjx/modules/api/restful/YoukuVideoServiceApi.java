
package com.fengjx.modules.api.restful;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fengjx.commons.utils.HttpUtil;
import com.fengjx.commons.utils.LogUtil;

import me.chanjar.weixin.mp.bean.WxMpXmlOutMessage;
import me.chanjar.weixin.mp.bean.WxMpXmlOutNewsMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 优酷视频搜索
 * 
 * @author fengjx
 * @date 2014年2月8日
 */
public class YoukuVideoServiceApi {

    private static final Logger LOG = LoggerFactory.getLogger(YoukuVideoServiceApi.class);

    // 测试方法
    public static void main(String[] args) {
        WxMpXmlOutNewsMessage news = searchVideo("老男孩");
        for (WxMpXmlOutNewsMessage.Item article : news.getArticles()) {
            System.out.println(article.getTitle());
            System.out.println(article.getPicUrl());
            System.out.println(article.getDescription());
            System.out.println(article.getUrl());
        }
    }

    public static WxMpXmlOutNewsMessage searchVideo(String keyword) {
        WxMpXmlOutNewsMessage news = WxMpXmlOutMessage.NEWS().build();
        // 优酷视频搜索接口
        String requestUrl = "https://openapi.youku.com/v2/searches/video/by_keyword.json?client_id=ce5c8483c6bd1c7c&keyword={KEYWORD}";
        requestUrl = requestUrl.replace("{KEYWORD}", keyword);
        // 查询并解析结果
        try {
            // 查询并获取返回结果
            String json = HttpUtil.get(requestUrl);
            // 将json字符串转换成json对象
            JSONObject jsonObject = JSONObject.parseObject(json);
            // 取出视频列表
            if (null != jsonObject.getString("total") && !"0".equals(jsonObject.getString("total"))) {
                JSONArray jsonArray = jsonObject.getJSONArray("videos");
                WxMpXmlOutNewsMessage.Item item = null;
                for (int i = 0; i < jsonArray.size() && i < 7; i++) {
                    JSONObject object = jsonArray.getJSONObject(i);
                    item = new WxMpXmlOutNewsMessage.Item();
                    item.setDescription(object.getString("title"));
                    item.setPicUrl(object.getString("thumbnail"));
                    item.setTitle(object.getString("tags"));
                    item.setUrl(object.getString("link"));
                    news.addArticle(item);
                }
            }
        } catch (Exception e) {
            LogUtil.error(LOG, "调用优酷视频查询接口异常", e);
        }
        return news;
    }

    /**
     * 视频点播使用指南
     * 
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
