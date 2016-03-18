
package com.fengjx.modules.wechat.model;

import com.fengjx.modules.wechat.bean.WechatExtApp;
import com.fengjx.modules.wechat.service.WechatExtAppService;
import me.chanjar.weixin.common.api.WxConsts;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.Map;

/**
 * @Created by FengJianxin on 2015/9/4.
 * @Email xd-fjx@qq.com
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring.xml")
public class ExtAppTest {

    @Autowired
    private WechatExtAppService wechatExtAppService;

    @Test
    public void testSaveExtApi() {
        Map<String, Object> attrs = new HashMap();
        attrs.put("id", "1");
        attrs.put("name", "天气预报2");
        WechatExtApp record = null;
        record = new WechatExtApp();
        record.setColumns(attrs);
        String[] msgTypes = new String[] {
                WxConsts.XML_MSG_EVENT, WxConsts.XML_MSG_LOCATION, WxConsts.XML_MSG_TEXT
        };
        String[] eventTypes = new String[] {
                WxConsts.EVT_CLICK, WxConsts.EVT_LOCATION
        };
        wechatExtAppService.saveExtApi(record, msgTypes, eventTypes);
    }

}
