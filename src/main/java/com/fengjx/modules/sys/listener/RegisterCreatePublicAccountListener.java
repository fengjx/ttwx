
package com.fengjx.modules.sys.listener;

import com.fengjx.commons.plugin.db.Record;
import com.fengjx.commons.utils.CommonUtils;
import com.fengjx.modules.wechat.service.WechatPublicAccountService;
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
    private WechatPublicAccountService publicAccountService;

    /**
     * 异步处理
     *
     * @param event
     */
    @Async
    @Override
    @SuppressWarnings("unchecked")
    public void onApplicationEvent(RegisterEvent event) {
        Record record = (Record) event.getSource();
        Map<String, Object> attrs = publicAccountService.resetAttrs(CommonUtils.getPrimaryKey(), record.getStr("id"));
        publicAccountService.insert(attrs);
    }

}
