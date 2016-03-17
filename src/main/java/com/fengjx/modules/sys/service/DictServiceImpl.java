
package com.fengjx.modules.sys.service;

import com.fengjx.commons.plugin.dict.DictData;
import com.fengjx.commons.plugin.dict.DictService;
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
    private SysDictService dictService;

    @Override
    public String getDictLabel(String value, String group) {
        return dictService.getDictLabel(value, group);
    }

    @Override
    public String getDictValue(String label, String group) {
        return dictService.getDictValue(label, group);
    }

    @Override
    public List<DictData> findDictList(String group) {
        List<DictData> list = Lists.newArrayList();
        List<Map<String, Object>> dictList = dictService.findDictList(group);
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
