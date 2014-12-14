package com.fjx.wechat.mysdk.beans.resp;

import com.fjx.wechat.mysdk.constants.WechatRespMsgtypeConstants;

import java.util.List;

/**
 * 图文消息
 * @author fengjx
 * @date 2014年1月19日
 */
public class RespNewsMessage extends RespBaseMessage {
	
	private static final long serialVersionUID = -3623970663695539083L;

	// 图文消息个数，限制为10条以内
	private int ArticleCount;
	// 多条图文消息信息，默认第一个item为大图
	private List<Article> Articles;

	public RespNewsMessage(){
		super.setMsgType(WechatRespMsgtypeConstants.RESP_MESSAGE_TYPE_NEWS);
	}


	public int getArticleCount() {
		return ArticleCount;
	}

	public void setArticleCount(int articleCount) {
		ArticleCount = articleCount;
	}

	public List<Article> getArticles() {
		return Articles;
	}

	public void setArticles(List<Article> articles) {
		Articles = articles;
	}
}