import com.fengjx.ttwx.common.utils.AesUtil;

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
        System.out.println(AesUtil.encrypt("7badacff41ba11e5a5523c15c2d1423c"));
        System.out.println(AesUtil.decrypt("9c6a3fd47288ca2ca1ec214b413d6f9f9f1bb068a3b5e6c8698b25955ef95ef52d00d8f88961c2f26eb9fc3073609ec2"));
    }
}
