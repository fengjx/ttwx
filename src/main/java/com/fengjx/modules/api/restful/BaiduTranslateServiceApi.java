
package com.fengjx.modules.api.restful;

import com.alibaba.fastjson.JSONObject;
import com.fengjx.commons.utils.HttpUtil;
import com.fengjx.commons.utils.JsonUtil;
import com.fengjx.commons.utils.LogUtil;
import com.fengjx.commons.utils.WebUtil;
import com.fengjx.modules.wechat.process.utils.MessageUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 百度翻译
 * http://developer.baidu.com/wiki/index.php?title=%E5%B8%AE%E5%8A%A9%E6%96%87%E6%A1%A3%E9%A6%96%E9%A1%B5/%E7%99%BE%E5%BA%A6%E7%BF%BB%E8%AF%91/%E7%BF%BB%E8%AF%91API
 *
 * @author fengjx
 * @date 2015-7-22
 */
public class BaiduTranslateServiceApi {

    private static final Logger LOG = LoggerFactory.getLogger(BaiduTranslateServiceApi.class);

    // 组装查询地址
    private static final String TRANSLATE_URL = "http://openapi.baidu.com/public/2.0/bmt/translate?client_id=VH6FEFsShPaKHZ8OhuOZf21M&q={keyWord}&from=auto&to=auto";

    // 测试
    public static void main(String[] args) {
        // 翻译结果：The network really powerful
        System.out.println(translate("The network really powerful"));
    }

    /**
     * @param source
     * @return
     */
    public static String translate(String source) {
        LogUtil.info(LOG, "百度翻译source=" + source);
        String dst = null;
        // 对参数q的值进行urlEncode utf-8编码
        String requestUrl = TRANSLATE_URL.replace("{keyWord}", WebUtil.urlEncode(source));
        // 查询并解析结果
        try {
            // 查询并获取返回结果
            String json = HttpUtil.get(requestUrl);
            // 通过Gson工具将json转换成TranslateResult对象
            JSONObject jsonObject = JsonUtil.getJSONFromString(json);
            // 取出translateResult中的译文
            dst = jsonObject.getJSONArray("trans_result").getJSONObject(0).getString("dst");
        } catch (Exception e) {
            LogUtil.error(LOG, "翻译系统异常", e);
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
