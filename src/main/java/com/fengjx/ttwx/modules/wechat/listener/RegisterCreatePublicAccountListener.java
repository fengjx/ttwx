
package com.fengjx.ttwx.modules.wechat.listener;

import com.fengjx.ttwx.common.utils.AesUtil;
import com.fengjx.ttwx.common.utils.CommonUtils;
import com.fengjx.ttwx.modules.common.constants.AppConfig;
import com.fengjx.ttwx.modules.wechat.model.PublicAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 用户注册创建公众账号关联配置
 *
 * @Created by fengjianxin
 * @date 2015/9/19
 */
@Component
public class RegisterCreatePublicAccountListener implements ApplicationListener<RegisterEvent> {

    @Autowired
    private PublicAccount publicAccount;

    /**
     * 异步处理
     *
     * @param event
     */
    @Async
    @Override
    public void onApplicationEvent(RegisterEvent event) {
        Map<String, Object> userAttrs = (Map<String, Object>) event.getSource();
        Map<String, Object> attrs = new HashMap();
        attrs.put("sys_user_id", userAttrs.get("id"));
        String id = CommonUtils.getPrimaryKey();
        String token = CommonUtils.getPrimaryKey();
        String ticket = CommonUtils.getPrimaryKey();
        attrs.put("id", id);
        attrs.put("in_time", new Date());
        attrs.put("token", token);
        attrs.put("ticket", ticket);
        attrs.put("url",
                AppConfig.DOMAIN_PAGE + AppConfig.API_PATH + "?ticket=" + AesUtil.encrypt(ticket));
        attrs.put("valid_code", CommonUtils.getRandomNum(5));
        attrs.put("valid_state", PublicAccount.VALID_STATE_NONACTIVATED);
        publicAccount.insert(attrs);
    }

}
