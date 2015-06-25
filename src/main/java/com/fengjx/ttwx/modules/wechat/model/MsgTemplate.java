
package com.fengjx.ttwx.modules.wechat.model;

import com.fengjx.ttwx.common.plugin.db.Mapper;
import com.fengjx.ttwx.common.plugin.db.Model;
import com.fengjx.ttwx.common.plugin.db.Record;

import org.springframework.stereotype.Component;

/**
 * @author fengjx.
 * @date：2015/6/23 0023
 */
@Mapper(table = "wechat_msg_template")
@Component
public class MsgTemplate extends Model {

    public Record getMsgTemplateByKey(String key) {
        StringBuilder sql = new StringBuilder(getSelectSql("m"));
        sql.append(" where m.msg_key = ?");
        return findOne(sql.toString(), key);
    }

    public String getTemplateContentByKey(String key) {
        return getMsgTemplateByKey(key).getStr("msg_content");
    }

}
