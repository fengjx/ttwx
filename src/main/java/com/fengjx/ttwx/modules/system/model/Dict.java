
package com.fengjx.ttwx.modules.system.model;

import com.fengjx.ttwx.common.plugin.db.Mapper;
import com.fengjx.ttwx.common.plugin.db.Model;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @Created by FengJianxin on 2015/8/22.
 * @Email xd-fjx@qq.com
 */
@Mapper(table = "sys_dict")
@Component
public class Dict extends Model {

    public String getDictLabel(String value) {
        StringBuilder sql = new StringBuilder(getSelectSql("d"));
        sql.append("where d.dict_value = ?");
        return findOne(sql.toString(), value).getStr("dict_name");
    }

    /**
     * 根据字典分组获得字典列表
     *
     * @param group
     * @return
     */
    public List<Map<String, Object>> findDictList(String group) {
        StringBuilder sql = new StringBuilder(getSelectSql("d"));
        sql.append("where d.dict_value = ?");
        return findList(sql.toString(), group);
    }

}
