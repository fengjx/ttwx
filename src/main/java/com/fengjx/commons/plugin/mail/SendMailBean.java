
package com.fengjx.commons.plugin.mail;

import com.fengjx.commons.bean.ToStringBase;

import java.io.File;
import java.util.List;

/**
 * 邮件内容实体对象
 *
 * @Created by FengJianxin on 2015/8/23.
 * @Email xd-fjx@qq.com
 */
public class SendMailBean extends ToStringBase {

    public static final String TYPE_TEXT = "text"; // 文本格式
    public static final String TYPE_HTML = "html"; // html格式
    public static final String TYPE_ATTR = "attr"; // 带有附件

    private String type;
    private String toUser; // 邮件接收地址
    private String fromUser = "xd-fjx@163.com"; // 发送者别名
    private String subject; // 邮件主题
    private String content; // 邮件正文内容
    private List<File> attrFiles; // 附件

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getToUser() {
        return toUser;
    }

    public void setToUser(String toUser) {
        this.toUser = toUser;
    }

    public String getFromUser() {
        return fromUser;
    }

    public void setFromUser(String fromUser) {
        this.fromUser = fromUser;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<File> getAttrFiles() {
        return attrFiles;
    }

    public void setAttrFiles(List<File> attrFiles) {
        this.attrFiles = attrFiles;
    }

}
