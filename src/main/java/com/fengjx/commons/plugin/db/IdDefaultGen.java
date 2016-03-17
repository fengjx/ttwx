
package com.fengjx.commons.plugin.db;

import com.fengjx.commons.utils.CommonUtils;

import java.io.Serializable;

/**
 * 默认ID生成器
 *
 * @version 2016/1/23
 * @Created by FengJianxin
 * @Email xd-fjx@qq.com
 */
public class IdDefaultGen implements IdGenerator {
    @Override
    public Serializable createId() {
        return CommonUtils.getPrimaryKey();
    }
}
