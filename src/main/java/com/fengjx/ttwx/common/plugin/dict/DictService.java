package com.fengjx.ttwx.common.plugin.dict;

import java.util.List;

/**
 * 数据字段查询服务
 * <p/>
 * Created by FengJianxin on 2015/8/22.
 * Email xd-fjx@qq.com
 */
public interface DictService {


    /**
     * 根据字典值获得字典名称
     *
     * @param value
     * @return
     */
    String getDictLabel(String value);


    /**
     * 根据字典分组获得字典列表
     *
     * @param group
     * @return
     */
    List<DictData> findDictList(String group);


}
