
package com.fengjx.commons.plugin.dict;

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
     * @param group
     * @return
     */
    public static String getDictLabel(String value, String group) {
        return service.getDictLabel(value, group);
    }

    /**
     * 根据指点标签和分组获得字典值
     *
     * @param label
     * @param group
     * @return
     */
    public static String getDictValue(String label, String group) {
        return service.getDictValue(label, group);
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
