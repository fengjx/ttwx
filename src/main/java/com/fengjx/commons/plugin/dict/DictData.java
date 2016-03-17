
package com.fengjx.commons.plugin.dict;

import com.fengjx.commons.bean.ToStringBase;

/**
 * 字典实体类
 *
 * @Created by FengJianxin on 2015/8/22.
 * @Email xd-fjx@qq.com
 */
public class DictData extends ToStringBase {

    private static final long serialVersionUID = 1L;

    private String value; // 数据值
    private String label; // 标签名
    private String type; // 类型
    private String description;// 描述

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
