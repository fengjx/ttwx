
package com.fengjx.modules.wechat.service;

import com.fengjx.commons.plugin.db.Model;
import com.fengjx.commons.plugin.db.Record;
import org.springframework.stereotype.Component;
import com.fengjx.modules.wechat.bean.WechatMsgTemplate;

/**
 * Autu Generated .
 */
@Component
public class WechatMsgTemplateService extends Model<WechatMsgTemplate> {

    public Record getMsgTemplateByKey(String key) {
        StringBuilder sql = new StringBuilder(getSelectSql("m"));
        sql.append(" where m.msg_key = ?");
        return findOne(sql.toString(), key);
    }

    public String getTemplateContentByKey(String key) {
        return getMsgTemplateByKey(key).getStr("msg_content");
    }

}
