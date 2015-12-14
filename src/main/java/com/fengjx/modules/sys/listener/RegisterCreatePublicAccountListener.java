
package com.fengjx.modules.sys.listener;

import com.fengjx.commons.utils.CommonUtils;
import com.fengjx.modules.wechat.model.PublicAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

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
    @SuppressWarnings("unchecked")
    public void onApplicationEvent(RegisterEvent event) {
        Map<String, Object> userAttrs = (Map<String, Object>) event.getSource();
        Map<String, Object> attrs = PublicAccount.resetAttrs(CommonUtils.getPrimaryKey(),
                (String) userAttrs.get("id"));
        publicAccount.insert(attrs);
    }

}
