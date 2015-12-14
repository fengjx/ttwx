
package com.fengjx.commons.ext.baidu.ueditor.hunter;

import com.fengjx.commons.ext.baidu.ueditor.define.State;

/**
 * 文件管理器接口
 *
 * @Created by FengJianxin on 2015/9/20.
 * @Email xd-fjx@qq.com
 */
public interface IFileManager {

    /**
     * 查询文件列表
     *
     * @param index
     * @return
     */
    State listFile(int index);

}
