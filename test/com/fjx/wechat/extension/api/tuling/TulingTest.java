package com.fjx.wechat.extension.api.tuling;

import com.fjx.wechat.extension.api.tuling.client.TulingApiClient;
import com.fjx.wechat.extension.api.tuling.vo.req.RequestBean;
import com.fjx.wechat.extension.api.tuling.vo.resp.BaseRespBean;
import org.junit.Test;

/**
 * 酒店类
 * Created by fengjx.
 * Date：2014/12/13 0013
 */
public class TulingTest {


    @Test
    public void testCall2String(){
        RequestBean req = new RequestBean();
        req.setUserid("fengjx123");
        req.setInfo("团购");
        String res = TulingApiClient.call2String(req);
        System.out.println(res);
    }

    @Test
    public void testCall2Bean(){
        RequestBean req = new RequestBean();
        req.setUserid("fengjx123");
        req.setInfo("你好");
        BaseRespBean res = TulingApiClient.call2Bean(req);
        System.out.println(res);
    }


    @Test
    public void testCall2WechatMsg(){
        RequestBean req = new RequestBean();
        req.setUserid("fengjx123");
        req.setInfo("你是傻逼吗");
        String res = TulingApiClient.call2WechatMsg(req);
        System.out.println(res);
    }

}
