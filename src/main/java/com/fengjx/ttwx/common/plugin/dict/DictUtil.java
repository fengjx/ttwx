
package com.fengjx.ttwx.common.plugin.dict;

import java.util.List;

/**
 * 数据字典工具
 *
 * @Created by FengJianxin on 2015/8/22.
 * @Email xd-fjx@qq.com
 */
public final class DictUtil {

    private static DictService service;

    protected static void init(DictService service) {
        DictUtil.service = service;
    }

    /**
     * 根据字典值获得字典名称
     *
     * @param value
     * @return
     */
    String getDictLabel(String value) {
        return service.getDictLabel(value);
    }

    /**
     * 根据字典分组获得字典列表
     *
     * @param group
     * @return
     */
    public static List<DictData> findDictList(String group) {
        return service.findDictList(group);
    }

}
