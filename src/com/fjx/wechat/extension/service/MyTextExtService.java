package com.fjx.wechat.extension.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.fjx.wechat.base.constants.WechatReqMsgtypeConstants;
import com.fjx.wechat.base.constants.WechatRespMsgtypeConstants;
import com.fjx.wechat.base.context.WechatContext;
import com.fjx.wechat.base.process.ext.TextExtService;
import com.fjx.wechat.base.tools.MessageUtil;
import com.fjx.wechat.base.vo.resp.Article;
import com.fjx.wechat.base.vo.resp.Music;
import com.fjx.wechat.base.vo.resp.RespMusicMessage;
import com.fjx.wechat.base.vo.resp.RespNewsMessage;
import com.fjx.wechat.base.vo.resp.RespTextMessage;
import com.fjx.wechat.extension.api.restful.BaiduMusicServiceApi;
import com.fjx.wechat.extension.api.restful.BaiduTranslateServiceApi;
import com.fjx.wechat.extension.api.restful.DreamServiceApi;
import com.fjx.wechat.extension.api.restful.LuckServiceApi;
import com.fjx.wechat.extension.api.restful.WeatherServiceApi;
import com.fjx.wechat.extension.api.restful.YoukuVideoServiceApi;

/**
 * 核心服务类
 * @author fengjx
 * @date 
 */
public class MyTextExtService implements TextExtService {
	
	private final Logger logger = Logger.getLogger(this.getClass());
	
	/**
	 * 处理微信发来的请求
	 * @param request
	 * @return
	 */
	
	public static void main(String[] args) {
		System.out.println(getMainUsage());
	}
	
	//主菜单
    private static String getMainUsage(){
    	// 由于href属性值必须用双引号引起，这与字符串本身的双引号冲突，所以要转义
    	StringBuffer contentMsg = new StringBuffer();  
    	contentMsg.append("使用指南").append("\n\n");  
		contentMsg.append("回复：1，查看歌曲搜索指南").append("\n");
		contentMsg.append("回复：2，查看视频搜索指南").append("\n");
		contentMsg.append("回复：3，查看星座运势指南").append("\n");
		contentMsg.append("回复：4，查看天气预报指南").append("\n");
        contentMsg.append("回复：5，查看翻译通指南").append("\n");
        contentMsg.append("回复：6，查看周公解梦指南").append("\n");
        contentMsg.append("回复：0，进入在线客服").append("\n");
        contentMsg.append("了解更多可进入我们<a href=\"http://3.fengjianxin.sinaapp.com/wechat/guanwang/index.html\">官方网站</a>  。\n更多功能敬请期待，您的支持是我们前进的最大动力").append("\n");
        contentMsg.append("如您有任何疑问、建议、吐槽，可联系QQ：466516623 或 TEL：18520226106").append("\n");
        contentMsg.append("点击这里给我<a href=\"http://fengjxblog.sinaapp.com/?page_id=31\">留言</a>");
        return contentMsg.toString();
    }

	@Override
	public String execute() {
		String respMessage = null;
		try {
			// xml请求解析
			//Map<String, String> requestMap = MessageUtil.parseXml(request);
			Map<String, String> requestMap = WechatContext.getWechatPostMap();

			// 发送方帐号（open_id）
			String fromUserName = requestMap.get("FromUserName");
			// 公众帐号
			String toUserName = requestMap.get("ToUserName");
			// 消息类型
			String msgType = requestMap.get("MsgType");

			RespTextMessage textMessage = new RespTextMessage();
			textMessage.setToUserName(fromUserName);
			textMessage.setFromUserName(toUserName);
			textMessage.setCreateTime(new Date().getTime());
			textMessage.setMsgType(WechatRespMsgtypeConstants.RESP_MESSAGE_TYPE_TEXT);
			textMessage.setFuncFlag(0);
			
			logger.info("消息类型："+msgType);
			// 文本消息
			if (msgType.equals(WechatReqMsgtypeConstants.REQ_MSG_TYPE_TEXT)) {
				// 接收用户发送的文本消息内容
				String content = requestMap.get("Content");
				// 创建图文消息
				RespNewsMessage newsMessage = new RespNewsMessage();
				newsMessage.setToUserName(fromUserName);
				newsMessage.setFromUserName(toUserName);
				newsMessage.setCreateTime(new Date().getTime());
				newsMessage.setMsgType(WechatRespMsgtypeConstants.RESP_MESSAGE_TYPE_NEWS);
				newsMessage.setFuncFlag(0);

				List<Article> articleList = new ArrayList<Article>();
				if (content.startsWith("歌曲")) {
					// 文本消息内容  
			        String respContent = null;
                    // 将歌曲2个字及歌曲后面的+、空格、-等特殊符号去掉  
                    String keyWord = content.replaceAll("^歌曲[\\+ ~!@#%^-_=]?", "").trim();  
                    // 如果歌曲名称为空  
                    if ("".equals(keyWord)) {  
                        respContent = BaiduMusicServiceApi.getMusicUsage();  
                    } else {  
                        String[] kwArr = keyWord.split("@");  
                        // 歌曲名称  
                        String musicTitle = kwArr[0];  
                        // 演唱者默认为空  
                        String musicAuthor = "";  
                        if (2 == kwArr.length){
                        	musicAuthor = kwArr[1];  
                        }
                        // 搜索音乐  
                        Music music = BaiduMusicServiceApi.searchMusic(musicTitle, musicAuthor);  
                        // 未搜索到音乐  
                        if (null == music) {  
                            respContent = "对不起，没有找到你想听的歌曲<" + musicTitle + ">。";  
                        } else {  
                            // 音乐消息  
                            RespMusicMessage musicMessage = new RespMusicMessage();  
                            musicMessage.setToUserName(fromUserName);  
                            musicMessage.setFromUserName(toUserName);  
                            musicMessage.setCreateTime(new Date().getTime());  
                            musicMessage.setMsgType(WechatRespMsgtypeConstants.RESP_MESSAGE_TYPE_MUSIC);  
                            musicMessage.setMusic(music);  
                            respMessage = MessageUtil.musicMessageToXml(musicMessage);  
                        }
                        //没有搜索结果
                        if (null != respContent){
                        	textMessage.setContent(respContent);  
                            respMessage = MessageUtil.textMessageToXml(textMessage);  
                        }
                    }  
                }
				//翻译
				else if (content.startsWith("翻译")) {
                    String keyWord = content.replaceAll("^翻译", "").trim();
                    logger.info("翻译："+keyWord);
                    if ("".equals(keyWord)) {
                    	//返回使用指南
                        textMessage.setContent(BaiduTranslateServiceApi.getTranslateUsage());  
                    } else {  
                        textMessage.setContent(BaiduTranslateServiceApi.translate(keyWord));  
                    }
                    respMessage = MessageUtil.textMessageToXml(textMessage);
                }
				//搜索视频（优酷）
				else if (content.startsWith("视频")) {
                    String keyWord = content.replaceAll("^视频", "").trim();
                    logger.debug("搜索视频："+keyWord);
                    String textContent = null;
                    if ("".equals(keyWord)) {  
                    	textContent = YoukuVideoServiceApi.getVideoUsage();
                    } else {
                    	articleList = YoukuVideoServiceApi.searchVideo(keyWord);
                    	if(null != articleList && articleList.size() > 0){
                    		newsMessage.setArticleCount(articleList.size());
        					newsMessage.setArticles(articleList);
        					respMessage = MessageUtil.newsMessageToXml(newsMessage);
                    	}else{
                    		textContent = "没有搜索结果...";
                    	}
                    }
                    //没有搜索结果
                    if (null != textContent){
                    	textMessage.setContent(textContent);  
                        respMessage = MessageUtil.textMessageToXml(textMessage);  
                    }
                }
				//星座运势
				else if (content.startsWith("运势")) {
                    String keyWord = content.replaceAll("^运势", "").trim();
                    logger.info("星座："+keyWord);
                    if ("".equals(keyWord)) {
                    	//返回使用指南
                        textMessage.setContent(LuckServiceApi.getLuckUsage());  
                    } else {  
                        textMessage.setContent(LuckServiceApi.luckyDay(keyWord));  
                    }
                    respMessage = MessageUtil.textMessageToXml(textMessage);
                }
				//天气预报
				else if (content.startsWith("天气")) {
                    String keyWord = content.replaceAll("^天气", "").trim();
                    logger.info("天气查询："+keyWord);
                    if ("".equals(keyWord)) {
                    	//返回使用指南
                        textMessage.setContent(WeatherServiceApi.getWeatherUsage());  
                    } else {
                        textMessage.setContent(WeatherServiceApi.queryhWeather(keyWord));  
                    }
                    respMessage = MessageUtil.textMessageToXml(textMessage);
                }
				//周公解梦
				else if (content.startsWith("解梦")) {
                    String keyWord = content.replaceAll("^解梦", "").trim();
                    logger.info("解梦："+keyWord);
                    if ("".equals(keyWord)) {
                    	//返回使用指南
                        textMessage.setContent(DreamServiceApi.getDreamUsage());  
                    } else {  
                        textMessage.setContent(DreamServiceApi.explain(keyWord));  
                    }
                    respMessage = MessageUtil.textMessageToXml(textMessage);
                }
            	else if (content.startsWith("留言")) {
                    textMessage.setContent("<a href=\"http://fengjxblog.sinaapp.com/?page_id=31\">留言</a>");  
                    respMessage = MessageUtil.textMessageToXml(textMessage);
                }
            	else if("game".equals(content)) {
					textMessage.setContent("进入<a href=\"http://3.fengjianxin.sinaapp.com/wechat/games/fishjoy/index.html\">捕鱼达人</a>");  
                    respMessage = MessageUtil.textMessageToXml(textMessage);  
				}
			}
		} catch (Exception e) {
			logger.error("执行自定义文本消息扩展处理发生异常",e);
		}
		return respMessage;
	}
	
}