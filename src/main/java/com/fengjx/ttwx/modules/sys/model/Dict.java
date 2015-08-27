
package com.fengjx.ttwx.modules.sys.model;

import com.fengjx.ttwx.common.plugin.db.Mapper;
import com.fengjx.ttwx.common.plugin.db.Model;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Created by FengJianxin on 2015/8/22.
 * @Email xd-fjx@qq.com
 */
@Mapper(table = "sys_dict")
@Component
public class Dict extends Model {

    /**
     * 根据字典值获得字典名称
     *
     * @param value
     * @param group
     * @return
     */
    public String getDictLabel(String value, String group) {
        Map<String, Object> attrs = new HashMap();
        attrs.put("dict_value", value);
        attrs.put("group_code", group);
        return findOne(attrs).getStr("dict_name");
    }

    /**
     * 根据指点标签和分组获得字典值
     *
     * @param label
     * @param group
     * @return
     */
    public String getDictValue(String label, String group) {
        Map<String, Object> attrs = new HashMap();
        attrs.put("dict_name", label);
        attrs.put("group_code", group);
        return findOne(attrs).getStr("dict_name");
    }

    /**
     * 根据字典分组获得字典列表
     *
     * @param group
     * @return
     */
    public List<Map<String, Object>> findDictList(String group) {
        Map<String, Object> attrs = new HashMap();
        attrs.put("group_code", group);
        return findList(attrs);
    }

    public void saveOrUpdate(Map<String, Object> attrs) {
        String id = (String) attrs.get("id");
        attrs.put("in_time", new Date());
        if (StringUtils.isBlank(id)) {
            insert(attrs);
        } else {
            update(attrs);
        }
    }

}
