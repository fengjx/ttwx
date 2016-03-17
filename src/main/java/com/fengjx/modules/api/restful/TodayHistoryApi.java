
package com.fengjx.modules.api.restful;

import com.alibaba.fastjson.JSON;
import com.fengjx.commons.utils.HttpUtil;
import com.fengjx.commons.utils.JsonUtil;
import com.fengjx.modules.api.config.Consts;
import com.google.common.collect.Maps;

import java.util.Map;

/**
 * 历史上的今天查询接口
 *
 * @doc: http://apistore.baidu.com/apiworks/servicedetail/493.html
 * @Created by fengjianxin
 * @date 2015/9/8
 */
public class TodayHistoryApi {

    private static final String URL = "http://apis.baidu.com/netpopo/todayhistory/todayhistory";

    public static JSON todayhistory(int month, int day) {
        Map<String, String> param = Maps.newHashMap();
        param.put("month", month + "");
        param.put("day", day + "");
        param.put("appkey", Consts.Apistore.API_KEY);
        String resStr = HttpUtil.get(URL, param, Consts.Apistore.API_KEY_MAP);
        return JsonUtil.getJSONFromString(resStr);
    }

    public static void main(String[] args) {
        System.out.println(todayhistory(9, 8));
    }

}
