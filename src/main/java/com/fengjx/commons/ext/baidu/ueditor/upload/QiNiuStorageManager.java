
package com.fengjx.commons.ext.baidu.ueditor.upload;

import com.fengjx.commons.ext.baidu.ueditor.define.AppInfo;
import com.fengjx.commons.ext.baidu.ueditor.define.BaseState;
import com.fengjx.commons.ext.baidu.ueditor.define.State;
import com.fengjx.commons.ext.qiniu.QiNiuUti;

import java.io.*;

/**
 * 七牛云存储文件管理器
 *
 * @Created by FengJianxin on 2015/9/20.
 * @Email xd-fjx@qq.com
 */
public class QiNiuStorageManager extends AbstractBinaryUploader {

    @Override
    public State saveBinaryFile(byte[] data, String path) {
        return null;
    }

    @Override
    public State saveTmpFile(File tmpFile, String path) {
        State state = null;
        try {
            QiNiuUti.uploadFile(tmpFile, path, true);
        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
            return new BaseState(false, AppInfo.IO_ERROR);
        }
        state = new BaseState(true);
        state.putInfo("size", tmpFile.length());
        return state;
    }

}
