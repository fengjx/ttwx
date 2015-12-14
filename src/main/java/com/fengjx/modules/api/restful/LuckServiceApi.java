
package com.fengjx.modules.api.restful;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fengjx.commons.utils.HttpUtil;
import com.fengjx.commons.utils.LogUtil;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * 每日星座运势
 * 
 * @author fengjx 接口说明查看：http://api.uihoo.com/demo/astro_day.shtml#body_top
 */
public class LuckServiceApi {

    private static final Logger LOG = LoggerFactory.getLogger(LuckServiceApi.class);

    private static Map<String, String> xingzhuo = new HashMap<String, String>();
    private static String[] xingzhuoArr = new String[12];

    static {
        xingzhuo.put("白羊", "0");
        xingzhuo.put("金牛", "1");
        xingzhuo.put("双子", "2");
        xingzhuo.put("巨蟹", "3");
        xingzhuo.put("狮子", "4");
        xingzhuo.put("处女", "5");
        xingzhuo.put("天秤", "6");
        xingzhuo.put("天蝎", "7");
        xingzhuo.put("射手", "8");
        xingzhuo.put("摩羯", "9");
        xingzhuo.put("水瓶", "10");
        xingzhuo.put("双鱼", "11");

        xingzhuoArr[0] = "白羊";
        xingzhuoArr[1] = "金牛";
        xingzhuoArr[2] = "双子";
        xingzhuoArr[3] = "巨蟹";
        xingzhuoArr[4] = "狮子";
        xingzhuoArr[5] = "处女";
        xingzhuoArr[6] = "天秤";
        xingzhuoArr[7] = "天蝎";
        xingzhuoArr[8] = "射手";
        xingzhuoArr[9] = "摩羯";
        xingzhuoArr[10] = "水瓶";
        xingzhuoArr[11] = "双鱼";

    }

    // 测试
    public static void main(String[] args) {
        System.out.println(luckyDay("狮子"));
    }

    /**
     * 星座今日运势
     * 
     * @param xingzhuoName
     * @return
     */
    public static String luckyDay(String xingzhuoName) {
        String id = null;
        // 如果是数字
        if (StringUtils.isNumeric(xingzhuoName)) {
            id = xingzhuoName;
        } else {
            id = xingzhuo.get(xingzhuoName);
        }
        String res = "请确认星座名称是否正确（例：狮子座只需要输入狮子）";
        // 组装查询地址
        String requestUrl = "http://api.uihoo.com/astro/astro.http.php?fun=day&id={ID}&format=json";

        if (null != id) {
            requestUrl = requestUrl.replace("{ID}", id);
            System.out.println(requestUrl);
            // 查询并解析结果
            try {
                // 查询并获取返回结果
                String json = HttpUtil.get(requestUrl);
                System.out.println("json result {} " + json);
                JSONArray jsonArray = JSONArray.parseArray(json);
                if (null != jsonArray && jsonArray.size() > 0) {
                    String title, rank, value;
                    res = xingzhuoArr[Integer.valueOf(id)] + "座今日运势\r\n";
                    res += "日期：" + jsonArray.getString(11) + "\r\n";
                    for (int i = 0; i < jsonArray.size() - 2; i++) {
                        res += "==============\r\n";
                        JSONObject object = jsonArray.getJSONObject(i);
                        title = object.getString("title");
                        rank = object.getString("rank");
                        value = object.getString("value");
                        if (null != title && !"".equals(title)) {
                            res += title + "：";
                        }
                        if (null != rank && !"".equals(rank) && !"0".equals(rank)) {
                            res += rank + "\r\n";
                        }
                        if (null != value && !"".equals(value)) {
                            res += value + "\r\n";
                        }
                    }
                }
            } catch (Exception e) {
                res = "算命先生在睡觉，请稍后再试。。。";
                LogUtil.error(LOG, "调用星座运势接口出现异常", e);
            }
        }
        return res;
    }

    /**
     * 星座运势操作指南
     * 
     * @return
     */
    public static String getLuckUsage() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("星座运势操作指南").append("\n\n");
        buffer.append("回复：运势+星座(或者编号)").append("\n");
        buffer.append("例如：运势狮子(运势4)").append("\n");
        buffer.append("回复：“?”显示主菜单").append("\n");
        buffer.append("星座编号对照表：").append("\n");
        buffer.append("0 - 白羊").append("\n");
        buffer.append("1 - 金牛").append("\n");
        buffer.append("2 - 双子").append("\n");
        buffer.append("3 - 巨蟹").append("\n");
        buffer.append("4 - 狮子").append("\n");
        buffer.append("5 - 处女").append("\n");
        buffer.append("6 - 天秤").append("\n");
        buffer.append("8 - 射手").append("\n");
        buffer.append("9 - 摩羯").append("\n");
        buffer.append("10 - 水瓶").append("\n");
        buffer.append("11 - 双鱼").append("\n");
        return buffer.toString();
    }

}
