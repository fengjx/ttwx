package com.fjx.wechat.extension.api.tuling.client;

import com.fjx.wechat.extension.api.tuling.vo.MsgConvert;
import com.fjx.wechat.extension.api.tuling.vo.req.RequestBean;
import com.fjx.wechat.extension.api.tuling.vo.resp.BaseRespBean;
import com.fjx.wechat.mysdk.tools.HttpUtil;
import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * 接口申请地址
 * http://tuling123.com/
 * 请求方式
 * Api 地址： http://www.tuling123.com/openapi/api
 * 请求方式： http get
 * 数据格式： json
 * 图灵机器人API
 * Created by fengjx.
 * Date：2014/12/13 0013
 */
public class TulingApiClient {

    private static final String API_KEY = "790125ff1b12295b6f914e0b2688b429";
    public static final String URL = "http://www.tuling123.com/openapi/api?key=790125ff1b12295b6f914e0b2688b429";

    public static BaseRespBean call2Bean(RequestBean request){
        return MsgConvert.strJson2Bean(call2String(request));
    }

    public static String call2String(RequestBean request){
        Map<String,String> param = null;
        try {
            param = BeanUtils.describe(request);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
        return HttpUtil.get(URL,param);
    }

    public static String call2WechatMsg(RequestBean request){
        return MsgConvert.toWechatMsg(call2Bean(request));
    }


}
