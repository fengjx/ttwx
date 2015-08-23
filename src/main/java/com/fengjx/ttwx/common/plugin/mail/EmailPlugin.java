
package com.fengjx.ttwx.common.plugin.mail;

import com.fengjx.ttwx.common.plugin.IPlugin;
import org.springframework.mail.javamail.JavaMailSenderImpl;

/**
 * 邮箱发送插件
 *
 * @Created by FengJianxin on 2015/8/23.
 * @Email xd-fjx@qq.com
 */
public class EmailPlugin implements IPlugin {

    private JavaMailSenderImpl sender;

    @Override
    public void start() {
        EmailUtil.init(sender);
    }

    public JavaMailSenderImpl getSender() {
        return sender;
    }

    public void setSender(JavaMailSenderImpl sender) {
        this.sender = sender;
    }
}
