
package com.fengjx.commons.ext.baidu.ueditor.upload;

import com.fengjx.commons.ext.baidu.ueditor.define.AppInfo;
import com.fengjx.commons.ext.baidu.ueditor.define.BaseState;
import com.fengjx.commons.ext.baidu.ueditor.define.State;
import org.apache.commons.lang3.SystemUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

/**
 * @Created by FengJianxin on 2015/9/20.
 * @Email xd-fjx@qq.com
 */
public abstract class AbstractBinaryUploader implements IStorageManager {

    protected Logger LOG = LoggerFactory.getLogger(this.getClass());

    @Override
    public State saveFileByInputStream(InputStream is, String path, long maxSize) {
        State state = null;
        File tmpFile = getTmpFile();
        byte[] dataBuf = new byte[2048];
        BufferedInputStream bis = new BufferedInputStream(is, StorageManager.BUFFER_SIZE);
        try {
            BufferedOutputStream bos = new BufferedOutputStream(
                    new FileOutputStream(tmpFile), StorageManager.BUFFER_SIZE);
            int count = 0;
            while ((count = bis.read(dataBuf)) != -1) {
                bos.write(dataBuf, 0, count);
            }
            bos.flush();
            bos.close();
            if (tmpFile.length() > maxSize) {
                tmpFile.delete();
                return new BaseState(false, AppInfo.MAX_SIZE);
            }
            state = saveTmpFile(tmpFile, path);
            if (!state.isSuccess()) {
                tmpFile.delete();
            }
            return state;
        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
        }
        return new BaseState(false, AppInfo.IO_ERROR);
    }

    @Override
    public State saveFileByInputStream(InputStream is, String path) {
        return saveFileByInputStream(is, path, 0);
    }

    protected File getTmpFile() {
        File tmpDir = SystemUtils.getJavaIoTmpDir();
        String tmpFileName = (Math.random() * 10000 + "").replace(".", "");
        return new File(tmpDir, tmpFileName);
    }

    abstract State saveTmpFile(File tmpFile, String path);

}
