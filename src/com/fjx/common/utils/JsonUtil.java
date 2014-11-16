package com.fjx.common.utils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.util.JSONUtils;

/**
 * json工具类
 * Created by fengjx on 2014/11/15 0015.
 */
public class JsonUtil {


    /**
     * 将对象转成json字符串
     * @return
     * @throws Exception
     */
    public static String getJson(Object object) {
        if(null == object){
            return "";
        }
        String tmp;
        if(JSONUtils.isArray(object)){
            tmp = JSONArray.fromObject(object).toString();
        }else if(JSONUtils.isObject(object)){
            tmp = JSONObject.fromObject(object).toString();
        }else{
            throw new IllegalArgumentException("非法的json对象");
        }
        return tmp;
    }

}
