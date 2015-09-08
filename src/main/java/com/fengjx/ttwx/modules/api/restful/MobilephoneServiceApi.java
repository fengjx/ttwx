
package com.fengjx.ttwx.modules.api.restful;

import com.alibaba.fastjson.JSON;
import com.fengjx.ttwx.common.utils.HttpUtil;
import com.fengjx.ttwx.common.utils.JsonUtil;
import com.fengjx.ttwx.modules.api.config.Consts;

/**
 * 手机号码归属地查询
 *
 * @doc: http://apistore.baidu.com/apiworks/servicedetail/117.html
 * @Created by fengjianxin
 * @date 2015/9/8
 */
public class MobilephoneServiceApi {

    private static final String URL = "http://apis.baidu.com/apistore/mobilephoneservice/mobilephone?tel={TEL}";

    public static JSON getMobileInfo(String tel) {
        String resStr = HttpUtil.get(URL.replace("{TEL}", tel), null, Consts.Apistore.API_KEY_MAP);
        return JsonUtil.getJSONFromString(resStr);
    }

    public static void main(String[] args) {
        System.out.println(getMobileInfo("18680502946"));
    }

}
