package com.fjx.wechat.extension.api.restful;

import java.io.InputStream;
import java.util.List;

import com.fjx.wechat.mysdk.tools.WeChatUtil;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;


/**
 * 周公解梦
 * @author fengjx
 * @date 2014年2月9日
 */
public class DreamServiceApi {
	
	private static final Logger logger = Logger.getLogger(DreamServiceApi.class);
	
	//测试
	public static void main(String[] args) {
		System.out.println(explain("鬼"));
	}
	
	/**
	 * 星座今日运势
	 * @param keyword
	 * @return
	 */
	public static String explain(String keyword) {
		String res = null;
		// 组装查询地址
		String requestUrl = "http://api.uihoo.com/dream/dream.http.php?key={KEY}&format=xml";
		requestUrl = requestUrl.replace("{KEY}", keyword);
		// 查询并解析结果
		try {
			// 查询并获取返回结果
			InputStream inputStream = WeChatUtil.httpRequest(requestUrl);
			if(null != inputStream){
				res = parseMsg(inputStream);
			}
			if(null == res){
				res = ">_<，暂无相关信息。。。";
			}
		} catch (Exception e) {
			res = "周公现在很忙，请稍后再试。。。";
			logger.error("调用解梦接口异常", e);
		}
		return res;
	}
	
	
	/**
	 * 解析解梦信息
	 * 
	 * @param inputStream 解梦API返回的输入流
	 * @return Music
	 */
	@SuppressWarnings("unchecked")
	private static String parseMsg(InputStream inputStream) {
		String msg = null;
		try {
			// 使用dom4j解析xml字符串
			SAXReader reader = new SAXReader();
			Document document = reader.read(inputStream);
			// 得到xml根元素
			Element root = document.getRootElement();
			// count表示搜索到的歌曲数
			List<Element> items = root.elements("item");
			// 当搜索到的歌曲数大于0时
			if (null != items && items.size() > 0) {
				msg = "";
				for(Element e : items){
					msg += e.getTextTrim()+"\n";
				}
			}
		} catch (Exception e) {
			logger.error("解梦API调用失败",e);
		}
		return msg;
	}
	
	
	/** 
     * 星座运势操作指南 
     * @return 
     */  
    public static String getDreamUsage() {  
        StringBuffer buffer = new StringBuffer();  
        buffer.append("周公解梦操作指南").append("\n\n");  
        buffer.append("回复：解梦+梦境中的事物").append("\n");  
        buffer.append("例如：解梦女朋友").append("\n");  
        buffer.append("回复：“?”显示主菜单");  
        return buffer.toString();  
    }
	
}
