
package com.fengjx.commons.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * json操作工具类
 * 
 * @author peiyu
 */
public final class JsonUtil {

    /**
     * 默认json格式化方式
     */
    public static final SerializerFeature[] DEFAULT_FORMAT = {
            SerializerFeature.WriteDateUseDateFormat, SerializerFeature.WriteEnumUsingToString,
            SerializerFeature.WriteNonStringKeyAsString, SerializerFeature.QuoteFieldNames,
            SerializerFeature.SkipTransientField, SerializerFeature.WriteNullStringAsEmpty,
            SerializerFeature.SortField, SerializerFeature.PrettyFormat,
            SerializerFeature.DisableCircularReferenceDetect
    };

    private JsonUtil() {
    }

    /**
     * 从json获取指定key的字符串
     * 
     * @param json json字符串
     * @param key 字符串的key
     * @return 指定key的值
     */
    public static Object getStringFromJSONObject(final String json, final String key) {
        Validate.notNull(json, "json is null");
        return JSON.parseObject(json).getString(key);
    }

    /**
     * 将字符串转换成JSON字符串
     * 
     * @param jsonString json字符串
     * @return 转换成的json对象
     */
    public static JSONObject getJSONFromString(final String jsonString) {
        if (StringUtils.isBlank(jsonString)) {
            return new JSONObject();
        }
        return JSON.parseObject(jsonString);
    }

    /**
     * 将json字符串，转换成指定java bean
     * 
     * @param jsonStr json串对象
     * @param beanClass 指定的bean
     * @param <T> 任意bean的类型
     * @return 转换后的java bean对象
     */
    public static <T> T toBean(String jsonStr, Class<T> beanClass) {
        Validate.notNull(jsonStr, "jsonStr is null");
        JSONObject jo = JSON.parseObject(jsonStr);
        jo.put(JSON.DEFAULT_TYPE_KEY, beanClass.getName());
        return JSON.parseObject(jo.toJSONString(), beanClass);
    }

    /**
     * 将json对象，转换成指定java bean
     * 
     * @param jsonObj
     * @param beanClass
     * @param <T>
     * @return
     */
    public static <T> T toBean(JSONObject jsonObj, Class<T> beanClass) {
        Validate.notNull(jsonObj, "jsonObj is null");
        jsonObj.put(JSON.DEFAULT_TYPE_KEY, beanClass.getName());
        return JSON.parseObject(jsonObj.toJSONString(), beanClass);
    }

    /**
     * @param obj 需要转换的java bean
     * @param <T> 入参对象类型泛型
     * @return 对应的json字符串
     */
    public static <T> String toJson(T obj) {
        Validate.notNull(obj, "obj is null");
        return JSON.toJSONString(obj, DEFAULT_FORMAT);
    }

    /**
     * 通过Map生成一个json字符串
     * 
     * @param map 需要转换的map
     * @return json串
     */
    public static String toJson(Map<String, Object> map) {
        Validate.notNull(map, "map is null");
        return JSON.toJSONString(map, DEFAULT_FORMAT);
    }

    /**
     * 美化传入的json,使得该json字符串容易查看
     * 
     * @param jsonString 需要处理的json串
     * @return 美化后的json串
     */
    public static String prettyFormatJson(String jsonString) {
        Validate.notNull(jsonString, "jsonString is null");
        return JSON.toJSONString(getJSONFromString(jsonString), true);
    }

    /**
     * 将传入的json字符串转换成Map
     * 
     * @param jsonString 需要处理的json串
     * @return 对应的map
     */
    public static Map<String, Object> toMap(String jsonString) {
        Validate.notNull(jsonString, "jsonString is null");
        return getJSONFromString(jsonString);
    }

    /**
     * json字符串转换list
     *
     * @param jsonStr
     * @return
     */
    public static List<Map<String, Object>> parseJSON2List(String jsonStr) {
        JSONArray jsonArr = JSON.parseArray(jsonStr);
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        for (int i = 0, l = jsonArr.size(); i < l; i++) {
            list.add(toMap(jsonArr.getString(i)));
        }
        return list;
    }

}
