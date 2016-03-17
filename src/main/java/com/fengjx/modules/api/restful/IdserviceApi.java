
package com.fengjx.modules.api.restful;

import com.alibaba.fastjson.JSON;
import com.fengjx.commons.utils.HttpUtil;
import com.fengjx.commons.utils.JsonUtil;
import com.fengjx.modules.api.config.Consts;

/**
 * 身份证查询
 * 
 * @doc: http://apistore.baidu.com/apiworks/servicedetail/113.html
 * @Created by fengjx
 * @date 2015/9/8.
 */
public class IdserviceApi {

    private static final String URL = "http://apis.baidu.com/apistore/idservice/id?id={ID}";

    /**
     * 查询身份证信息
     * @param id 身份证号码
     * @return
     */
    public static JSON getUserInfo(String id) {
        String resStr = HttpUtil.get(URL.replace("{ID}", id), null, Consts.Apistore.API_KEY_MAP);
        return JsonUtil.getJSONFromString(resStr);
    }

    public static void main(String[] args) {
        System.out.println(getUserInfo("45022219890728163X"));
    }

}
