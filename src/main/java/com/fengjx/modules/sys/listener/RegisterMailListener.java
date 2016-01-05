
package com.fengjx.modules.sys.listener;

import com.fengjx.commons.plugin.db.Record;
import com.fengjx.commons.plugin.freemarker.FreemarkerUtil;
import com.fengjx.commons.plugin.mail.EmailUtil;
import com.fengjx.commons.plugin.mail.SendMailBean;
import com.fengjx.commons.utils.AesUtil;
import com.fengjx.commons.utils.LogUtil;
import com.fengjx.modules.common.constants.AppConfig;
import com.fengjx.modules.common.constants.FtlFilenameConstants;
import com.fengjx.modules.sys.entity.SysUserEntity;
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
        Record record = (Record) event.getSource();
        // 如果是管理员添加的用户，并且状态已经激活了就不在发送激活邮件
        if (SysUserEntity.IS_ALIVE.equals(record.getStr("is_valid"))) {
            return;
        }
        SendMailBean mail = new SendMailBean();
        mail.setType(SendMailBean.TYPE_HTML);
        mail.setToUser(record.getStr("email"));
        mail.setSubject("邮箱验证");
        Map<String, String> root = new HashMap();
        root.put("appName", AppConfig.APP_NAME);
        root.put("userEmail", record.getStr("email"));
        root.put("username", record.getStr("username"));
        root.put("decrypPwd", record.getStr("decrypPwd"));
        root.put("validUrl", AppConfig.DOMAIN_PAGE + "/activate?ser="
                + AesUtil.encrypt(record.getStr("valid_uid")));
        mail.setContent(FreemarkerUtil.process(root, FtlFilenameConstants.REGISTER_VALID_MAIN));
        try {
            EmailUtil.send(mail);
        } catch (Exception e) {
            LogUtil.error(LOG, "用户注册成功，但激活邮件发送失败。userinfo:{}" + mail, e);
        }
    }
}
