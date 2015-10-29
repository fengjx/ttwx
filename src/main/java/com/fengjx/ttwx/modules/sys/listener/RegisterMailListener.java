
package com.fengjx.ttwx.modules.sys.listener;

import com.fengjx.ttwx.common.plugin.freemarker.FreemarkerUtil;
import com.fengjx.ttwx.common.plugin.mail.EmailUtil;
import com.fengjx.ttwx.common.plugin.mail.SendMailBean;
import com.fengjx.ttwx.common.utils.AesUtil;
import com.fengjx.ttwx.common.utils.LogUtil;
import com.fengjx.ttwx.modules.common.constants.AppConfig;
import com.fengjx.ttwx.modules.common.constants.FtlFilenameConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * 用户注册发送激活邮件监听器
 *
 * @Created by fengjianxin
 * @date 2015/9/19
 */
@Component
public class RegisterMailListener implements ApplicationListener<RegisterEvent> {

    private static final Logger LOG = LoggerFactory.getLogger(RegisterMailListener.class);

    /**
     * 异步处理
     *
     * @param event
     */
    @Async
    @Override
    @SuppressWarnings("unchecked")
    public void onApplicationEvent(RegisterEvent event) {
        Map<String, Object> attrs = (Map<String, Object>) event.getSource();
        SendMailBean mail = new SendMailBean();
        mail.setType(SendMailBean.TYPE_HTML);
        mail.setToUser((String) attrs.get("email"));
        mail.setSubject("邮箱验证");
        Map<String, String> root = new HashMap();
        root.put("appName", AppConfig.APP_NAME);
        root.put("userEmail", (String) attrs.get("email"));
        root.put("validUrl", AppConfig.DOMAIN_PAGE + "/activate?ser="
                + AesUtil.encrypt((String) attrs.get("valid_uid")));
        mail.setContent(FreemarkerUtil.process(root, FtlFilenameConstants.REGISTER_VALID_MAIN));
        try {
            EmailUtil.send(mail);
        } catch (Exception e) {
            LogUtil.error(LOG, "用户注册成功，但激活邮件发送失败。userinfo:{}" + mail.toString(), e);
        }
    }
}
