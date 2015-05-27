
package com.fengjx.ttwx.modules.common.model;

import com.fengjx.ttwx.common.db.Mapper;
import com.fengjx.ttwx.common.db.Model;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 数据字典
 *
 * @author fengjx.
 * @date：2015/5/26 0026
 */
@Mapper(table = "wechat_data_dict", id = "id")
@Component
public class DataDict extends Model {

    public List<Map<String, Object>> getGroupDict(String groupCode) {
        Map<String, Object> attrs = new HashMap<String, Object>();
        attrs.put("group_code", groupCode);
        return findList(attrs);
    }

}
