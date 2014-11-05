package com.fjx.wechat.base.tools;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.fjx.common.framework.system.exception.MyRuntimeException;
import com.fjx.wechat.base.constants.WechatRespMsgtypeConstants;
import com.fjx.wechat.base.vo.resp.Article;
import com.fjx.wechat.base.vo.resp.RespNewsMessage;
import com.fjx.wechat.base.vo.resp.RespTextMessage;



/**
 * 素材工具类
 * @author fengjx xd-fjx@qq.com
 * @version MaterialUtil.java 2014年10月4日
 */
public class MaterialUtil {
	
	/**
	 * 将素材map数据转换成微信规范的xml数据
	 * (待完善)
	 * @return
	 */
	public static String data2Xml(List<Map<String, String>> materialList){
		int size = materialList.size();
		String msg_type = materialList.get(0).get("msg_type");
		if(null == materialList || size < 1){
			throw new MyRuntimeException("素材数据为空：materialList is empty");
		}
		if(null == msg_type){
			throw new MyRuntimeException("素材类型不符合规范：msg_type is null");
		}
		String respMessage = "";
		if(msg_type.equals(WechatRespMsgtypeConstants.RESP_MESSAGE_TYPE_TEXT)){//文字消息
			RespTextMessage textMessage = new RespTextMessage();
			textMessage.setToUserName("{#ToUserName#}");
			textMessage.setFromUserName("{#FromUserName#}");
			textMessage.setMsgType(WechatRespMsgtypeConstants.RESP_MESSAGE_TYPE_TEXT);
			textMessage.setFuncFlag(0);
			//消息内容
			textMessage.setContent(materialList.get(0).get("txt_content"));
			// 将文本消息对象转换成xml字符串
			respMessage = MessageUtil.textMessageToXml(textMessage);
			
		}else if(msg_type.equals(WechatRespMsgtypeConstants.RESP_MESSAGE_TYPE_NEWS)){
			// 创建图文消息
			RespNewsMessage newsMessage = new RespNewsMessage();
			newsMessage.setToUserName("{#ToUserName#}");
			newsMessage.setFromUserName("{#FromUserName#}");
			newsMessage.setMsgType(WechatRespMsgtypeConstants.RESP_MESSAGE_TYPE_NEWS);
			newsMessage.setFuncFlag(0);
			List<Article> articleList = new ArrayList<Article>();
			Article article = null;
			for(Map<String, String> map : materialList){
				article = new Article();
				article.setTitle(map.get("news_title"));
				article.setDescription(map.get("news_description"));
				article.setPicUrl(map.get("news_pic_url"));
				article.setUrl(map.get("news_url"));
				articleList.add(article);
			}
			newsMessage.setArticleCount(size);
			newsMessage.setArticles(articleList);
			respMessage = MessageUtil.newsMessageToXml(newsMessage);
		}else if(msg_type.equals(WechatRespMsgtypeConstants.RESP_MESSAGE_TYPE_MUSIC)){
			
			
		}
		return respMessage;
	}
	
	
}
