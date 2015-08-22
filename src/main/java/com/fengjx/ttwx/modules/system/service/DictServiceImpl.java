
package com.fengjx.ttwx.modules.system.service;

import com.fengjx.ttwx.common.plugin.dict.DictData;
import com.fengjx.ttwx.common.plugin.dict.DictService;
import com.fengjx.ttwx.modules.system.model.Dict;
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

/**
 * 数据字典
 *
 * @Created by FengJianxin on 2015/8/22.
 * @Email xd-fjx@qq.com
 */
public class DictServiceImpl implements DictService {

    @Autowired
    private Dict dict;

    @Override
    public String getDictLabel(String value) {
        return dict.getDictLabel(value);
    }

    @Override
    public List<DictData> findDictList(String group) {
        List<DictData> list = Lists.newArrayList();
        List<Map<String, Object>> dictList = dict.findDictList(group);
        DictData dict;
        if (CollectionUtils.isNotEmpty(dictList)) {
            for (Map<String, Object> map : dictList) {
                dict = new DictData();
                dict.setValue((String) map.get("dict_value"));
                dict.setDescription((String) map.get("dict_desc"));
                dict.setLabel((String) map.get("dict_name"));
                dict.setType((String) map.get("group_code"));
                list.add(dict);
            }
        }
        return list;
    }

}
