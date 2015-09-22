import me.chanjar.weixin.mp.bean.WxMpXmlOutMessage;

/**
 * @author fengjx.
 * @date：2015/5/19 0019
 */
public class Test {

    public static void main(String[] args) {
//        WxMpXmlOutTextMessage m = WxMpXmlOutMessage.TEXT().content("测试加密消息").fromUser("")
//                .toUser("").build();
//        System.out.println(m.toXml());
//        String xml = "<xml>\n" +
//                "  <MsgType><![CDATA[text]]></MsgType>\n" +
//                "  <Content><![CDATA[哈哈]]></Content>\n" +
//                "</xml>";
//        WxMpXmlOutTextMessage m2 = XStreamTransformer.fromXml(WxMpXmlOutTextMessage.class,xml);
//        System.out.println(m2.getContent());

        System.out.println(WxMpXmlOutMessage.TEXT().content("ssss").fromUser("").toUser("").build()
                .toXml());

    }
}
