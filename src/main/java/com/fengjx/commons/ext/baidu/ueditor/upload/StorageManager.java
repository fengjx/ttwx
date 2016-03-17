
package com.fengjx.commons.ext.baidu.ueditor.upload;

import com.fengjx.commons.ext.baidu.ueditor.define.AppInfo;
import com.fengjx.commons.ext.baidu.ueditor.define.BaseState;
import com.fengjx.commons.ext.baidu.ueditor.define.State;
import org.apache.commons.io.FileUtils;

import java.io.*;

public class StorageManager extends AbstractBinaryUploader implements IStorageManager {

    public static final int BUFFER_SIZE = 8192;

    public StorageManager() {
    }

    public State saveBinaryFile(byte[] data, String path) {
        File file = new File(path);
        State state = valid(file);
        if (!state.isSuccess()) {
            return state;
        }
        try {
            BufferedOutputStream bos = new BufferedOutputStream(
                    new FileOutputStream(file));
            bos.write(data);
            bos.flush();
            bos.close();
        } catch (IOException ioe) {
            return new BaseState(false, AppInfo.IO_ERROR);
        }

        state = new BaseState(true, file.getAbsolutePath());
        state.putInfo("size", data.length);
        state.putInfo("title", file.getName());
        return state;
    }

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
            // 小于或等于0表示不限制
            if (maxSize > 0 && tmpFile.length() > maxSize) {
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
    public State saveTmpFile(File tmpFile, String path) {
        State state = null;
        File targetFile = new File(path);
        if (targetFile.canWrite()) {
            return new BaseState(false, AppInfo.PERMISSION_DENIED);
        }
        try {
            FileUtils.copyFile(tmpFile, targetFile);
        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
            return new BaseState(false, AppInfo.IO_ERROR);
        }
        state = new BaseState(true);
        state.putInfo("size", targetFile.length());
        return state;
    }

    private State valid(File file) {
        File parentPath = file.getParentFile();
        if ((!parentPath.exists()) && (!parentPath.mkdirs())) {
            return new BaseState(false, AppInfo.FAILED_CREATE_FILE);
        }
        if (!parentPath.canWrite()) {
            return new BaseState(false, AppInfo.PERMISSION_DENIED);
        }
        return new BaseState(true);
    }
}
