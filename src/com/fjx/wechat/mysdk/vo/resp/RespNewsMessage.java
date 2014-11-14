package com.fjx.wechat.mysdk.vo.resp;

import com.fjx.wechat.base.vo.resp.*;
import com.fjx.wechat.base.vo.resp.Article;

import java.util.List;

/**
 * 图文消息
 * @author fengjx
 * @date 2014年1月19日
 */
public class RespNewsMessage extends com.fjx.wechat.base.vo.resp.RespBaseMessage {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3623970663695539083L;
	// 图文消息个数，限制为10条以内
	private int ArticleCount;
	// 多条图文消息信息，默认第一个item为大图
	private List<com.fjx.wechat.base.vo.resp.Article> Articles;

	public int getArticleCount() {
		return ArticleCount;
	}

	public void setArticleCount(int articleCount) {
		ArticleCount = articleCount;
	}

	public List<com.fjx.wechat.base.vo.resp.Article> getArticles() {
		return Articles;
	}

	public void setArticles(List<Article> articles) {
		Articles = articles;
	}
}