
package com.fengjx.wechat.sdk.fastweixin.util;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.XMLEvent;

/**
 * 消息工具类 用于解析微信平台消息xml报文
 *
 * @author peiyu
 */
public final class MessageUtil {

    private static final Logger LOG = LoggerFactory.getLogger(MessageUtil.class);

    /**
     * 此类不需要实例化
     */
    private MessageUtil() {
    }

    /**
     * 解析从微信服务器来的请求，将消息或者事件返回出去
     *
     * @param request http请求对象
     * @return 微信消息或者事件Map
     */
    public static Map<String, String> parseXml(HttpServletRequest request) {
        Map<String, String> map = new HashMap<String, String>();

        InputStream inputStream = null;
        try {
            inputStream = request.getInputStream();
            XMLInputFactory factory = XMLInputFactory.newInstance();
            XMLEventReader reader = factory.createXMLEventReader(inputStream);
            while (reader.hasNext()) {
                XMLEvent event = reader.nextEvent();
                if (event.isStartElement()) {
                    String tagName = event.asStartElement().getName()
                            .toString();
                    if (!"xml".equals(tagName)) {
                        String text = reader.getElementText();
                        map.put(tagName, text);
                    }
                }
            }
        } catch (IOException e) {
            LOG.error("IO出现异常", e);
        } catch (XMLStreamException e) {
            LOG.error("XML解析出现异常", e);
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                LOG.error("IO出现异常", e);
            }
        }
        return map;
    }

    /**
     * 替换响应消息中的参数
     * @param msg
     * @param reqMsgMap
     * @return
     */
    public static String replaceMsgParam(String msg, Map<String, String> reqMsgMap){
        if(StringUtils.isBlank(msg)){
            return "";
        }
        String res = "";
        res = StringUtils.replaceEach(msg, 
                new String[]{
                    "{#ToUserName#}",
                    "{#FromUserName#}",
                    "{#CreateTime#}"
                },
                new String[]{
                    reqMsgMap.get("FromUserName"),
                    reqMsgMap.get("ToUserName"),
                    new Date().getTime()+""
                });

        return res;
    }

    /**
     * 替换响应消息中的参数(正在表达式来处理)
     * @param msg
     * @param reqMsgMap
     * @return
     */
    public static String replaceMsgByReg(String msg, Map<String, String> reqMsgMap){
        if(StringUtils.isBlank(msg)){
            return "";
        }
        String res = "";
        res = msg.replaceAll("\\<ToUserName>(.*?)\\</ToUserName>", "<ToUserName><![CDATA[" + reqMsgMap.get("FromUserName") + "]]></ToUserName>")
                .replaceAll("\\<FromUserName>(.*?)\\</FromUserName>", "<FromUserName><![CDATA[" + reqMsgMap.get("ToUserName") + "]]></FromUserName>")
                .replaceAll("\\<CreateTime>(.*?)\\</CreateTime>", "<CreateTime><![CDATA[" + new Date().getTime() + "]]></CreateTime>");
        return res;
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
