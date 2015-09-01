
package com.fengjx.ttwx.modules.sys.model;

import com.fengjx.ttwx.common.plugin.db.Mapper;
import com.fengjx.ttwx.common.plugin.db.Model;

import com.fengjx.ttwx.common.plugin.db.page.AdapterPage;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.*;

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

    /**
     * 分页查询
     *
     * @param attrs
     * @return
     */
    public AdapterPage page(Map<String, String> attrs) {
        StringBuilder sql = new StringBuilder(getSelectSql("a"));
        sql.append(" where 1 = 1");
        List<Object> params = new ArrayList();
        if (StringUtils.isNoneBlank(attrs.get("group_code"))) {
            sql.append(" and group_code like CONCAT('%',?,'%')");
            params.add(attrs.get("group_code"));
        }
        if (StringUtils.isNoneBlank(attrs.get("dict_desc"))) {
            sql.append(" and dict_desc like CONCAT('%',?,'%')");
            params.add(attrs.get("dict_desc"));
        }
        sql.append(" order by in_time desc");
        return paginate(sql.toString(), params.toArray()).convert();
    }
}
