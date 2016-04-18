
package com.fengjx.modules.wechat.process.utils;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 消息处理工具类
 * 
 * @author fengjx
 * @date 2014年1月19日
 */
public final class MessageUtil {

    /**
     * 解析微信发来的请求（XML）
     * 
     * @return
     */
    @SuppressWarnings("unchecked")
    public static Map<String, String> parseXml(InputStream inputStream) {
        // 将解析结果存储在HashMap中
        Map<String, String> map = new HashMap<String, String>();
        try {
            if (null != inputStream) {
                // 读取输入流
                SAXReader reader = new SAXReader();
                Document document = reader.read(inputStream);
                // 得到xml根元素
                Element root = document.getRootElement();
                // 得到根元素的所有子节点
                List<Element> elementList = root.elements();
                // 遍历所有子节点
                for (Element e : elementList) {
                    map.put(e.getName(), e.getText());
                }
                map.put("xml", document.asXML());
            }
        } catch (DocumentException e) {
            throw new RuntimeException(e);
        } finally {
            IOUtils.closeQuietly(inputStream);
        }
        return map;
    }

    /**
     * xml字符串转Map
     *
     * @param xmlStr
     * @return
     */
    @SuppressWarnings("unchecked")
    public static Map<String, String> parseXml(String xmlStr) {
        // 将解析结果存储在HashMap中
        Map<String, String> map = new HashMap<String, String>();
        if (StringUtils.isNotBlank(xmlStr)) {
            try {
                // 读取输入流
                Document document = DocumentHelper.parseText(xmlStr);
                ;
                // 得到xml根元素
                Element root = document.getRootElement();
                // 得到根元素的所有子节点
                List<Element> elementList = root.elements();
                // 遍历所有子节点
                for (Element e : elementList) {
                    map.put(e.getName(), e.getText());
                }
                map.put("xml", document.asXML());
            } catch (DocumentException e) {
                throw new RuntimeException(e);
            }
        }
        return map;
    }

    /**
     * 扩展xstream，使其支持CDATA块
     * 
     * @date 2014年1月19日
     */
    private static XStream xstream = new XStream(new XppDriver() {
        @Override
        public HierarchicalStreamWriter createWriter(Writer out) {
            return new PrettyPrintWriter(out) {
                // 对所有xml节点的转换都增加CDATA标记
                boolean cdata = true;

                public void startNode(String name, Class clazz) {
                    super.startNode(name);
                }

                protected void writeText(QuickWriter writer, String text) {
                    if (cdata) {
                        writer.write("<![CDATA[");
                        writer.write(text);
                        writer.write("]]>");
                    } else {
                        writer.write(text);
                    }
                }
            };
        }
    });

    /**
     * 计算采用utf-8编码方式时字符串所占字节数
     * 
     * @param content
     * @return
     */
    public static int getByteSize(String content) {
        int size = 0;
        if (null != content) {
            try {
                // 汉字采用utf-8编码时占3个字节
                size = content.getBytes("utf-8").length;
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException(e);
            }
        }
        return size;
    }

    /**
     * emoji表情转换(hex -> utf-16)
     * 
     * @param hexEmoji
     * @return
     */
    public static String emoji(int hexEmoji) {
        return String.valueOf(Character.toChars(hexEmoji));
    }

    /**
     * 将微信消息中的CreateTime转换成标准格式的时间（yyyy-MM-dd HH:mm:ss）
     * CreateTime，表示1970年1月1日0时0分0秒至消息创建时所间隔的秒数，不是毫秒数！
     * 
     * @param createTime 消息创建时间
     * @return
     */
    public static String formatCreateTime(String createTime) {
        // 将微信传入的CreateTime转换成long类型，再乘以1000
        long msgCreateTime = Long.parseLong(createTime) * 1000L;
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(new Date(msgCreateTime));
    }

    /**
     * 从xml响应报文中解析消息类型
     *
     * @param xml
     * @return
     */
    public static String parseMsgType(String xml) {
        // <MsgType><![CDATA[text]]></MsgType>
        Pattern p = Pattern
                .compile("<MsgType><!\\[CDATA\\[(.*?)\\]\\]></MsgType>|(<MsgType>(.*?)</MsgType>)");
        Matcher m = p.matcher(xml);
        if (m.find()) {
            return m.group(1);
        }
        throw new RuntimeException("msgType unkonwn");
    }

    public static boolean IsNumeric(String str) {
        return str.matches("\\d *");
    }

    public static String mapToXml(Map<String, String> params) {
        String xml = "<xml>";
        for (Map.Entry<String, String> entry : params.entrySet()) {
            String key = entry.getKey();
            String val = entry.getValue();
            if (IsNumeric(val)) {
                xml += "<" + key + ">" + val + "</" + key + ">";

            } else
                xml += "<" + key + "><![CDATA[" + val + "]]></" + key + ">";
        }

        xml += "</xml>";
        return xml;
    }

    public static void main(String[] args) {
        System.out.println(emoji(0x1F6B9));
    }

}
