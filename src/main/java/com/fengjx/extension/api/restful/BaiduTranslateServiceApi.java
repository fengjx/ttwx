
package com.fengjx.extension.api.restful;

import com.alibaba.fastjson.JSONObject;
import com.fengjx.common.utils.HttpUtil;
import com.fengjx.common.utils.JsonUtil;
import com.fengjx.wechat.sdk.fastweixin.util.MessageUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;

/**
 * @author fengjx
 * @date 2014年2月8日
 */
public class BaiduTranslateServiceApi {

    private static final Logger LOGGER = LoggerFactory.getLogger(BaiduTranslateServiceApi.class);

    // 测试
    public static void main(String[] args) {
        // 翻译结果：The network really powerful
        System.out.println(translate("The network really powerful"));
    }

    /**
     * utf编码
     * 
     * @param source
     * @return
     */
    public static String urlEncodeUTF8(String source) {
        String result = source;
        try {
            result = java.net.URLEncoder.encode(source, "utf-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    /**
     * @param source
     * @return
     */
    public static String translate(String source) {
        LOGGER.info("百度翻译source=" + source);
        String dst = null;
        // 组装查询地址
        String requestUrl = "http://openapi.baidu.com/public/2.0/bmt/translate?client_id=GW2V8O7GAKocy3dvKPbfYssm&q={keyWord}&from=auto&to=auto";
        // 对参数q的值进行urlEncode utf-8编码
        requestUrl = requestUrl.replace("{keyWord}", urlEncodeUTF8(source));
        // 查询并解析结果
        try {
            // 查询并获取返回结果
            String json = HttpUtil.get(requestUrl);
            // 通过Gson工具将json转换成TranslateResult对象
            JSONObject jsonObject = JsonUtil.getJSONFromString(json);
            // 取出translateResult中的译文
            dst = jsonObject.getJSONArray("trans_result").getJSONObject(0).getString("dst");
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
        if (null == dst) {
            dst = "翻译系统异常，请稍候尝试！";
        }
        return dst;
    }

    /**
     * 翻译通使用指南
     * 
     * @return
     */
    public static String getTranslateUsage() {
        StringBuffer buffer = new StringBuffer();
        buffer.append(MessageUtil.emoji(0xe148)).append("翻译译通使用指南").append("\n\n");
        buffer.append("目前支持以下翻译方向：").append("\n");
        buffer.append("    中 -> 英").append("\n");
        buffer.append("    英 -> 中").append("\n");
        buffer.append("    日 -> 中").append("\n\n");
        buffer.append("使用示例：").append("\n");
        buffer.append("    翻译你是我的唯一").append("\n");
        buffer.append("    翻译I miss you").append("\n");
        buffer.append("    翻译さようなら").append("\n\n");
        buffer.append("回复：“?”显示主菜单");
        return buffer.toString();
    }

}
