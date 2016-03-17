
package com.fengjx.commons.ext.baidu.ueditor.upload;

import com.fengjx.commons.ext.baidu.ueditor.define.State;

import java.io.InputStream;

/**
 * 文件管理器
 *
 * @Created by FengJianxin on 2015/9/20.
 * @Email xd-fjx@qq.com
 */
public interface IStorageManager {

    State saveBinaryFile(byte[] data, String path);

    State saveFileByInputStream(InputStream is, String path, long maxSize);

    State saveFileByInputStream(InputStream is, String path);

}
