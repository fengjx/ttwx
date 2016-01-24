
package com.fengjx.commons.plugin.db;

import java.io.Serializable;

/**
 * ID生成器
 *
 * @version 2016/1/23
 * @Created by FengJianxin
 * @Email xd-fjx@qq.com
 */
public interface IdGenerator {

    Serializable createId();

}
