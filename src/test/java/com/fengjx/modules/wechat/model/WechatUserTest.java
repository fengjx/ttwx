
package com.fengjx.modules.wechat.model;

import com.fengjx.commons.utils.JsonUtil;

import com.fengjx.modules.wechat.service.WechatPublicAccountService;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpUserList;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 微信用户测试
 *
 * @author fengjx.
 * @date：2015/7/23 0023
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring.xml")
public class WechatUserTest {

    @Autowired
    private WechatPublicAccountService publicAccountService;

    @Test
    public void testQueryUser() {
        String userid = "93f75794fc6e11e480826036dd68230b";
        WxMpService wxMpService = publicAccountService.getWxMpService(userid);
        try {
            WxMpUserList userList = wxMpService.userList(null);
            System.out.println(JsonUtil.toJson(userList));
        } catch (WxErrorException e) {
            e.printStackTrace();
        }
    }

}
