
package com.fengjx.commons.ext.baidu.ueditor.upload;

import com.fengjx.commons.ext.baidu.ueditor.define.AppInfo;
import com.fengjx.commons.ext.baidu.ueditor.define.BaseState;
import com.fengjx.commons.ext.baidu.ueditor.PathFormat;
import com.fengjx.commons.ext.baidu.ueditor.define.FileType;
import com.fengjx.commons.ext.baidu.ueditor.define.State;
import org.apache.commons.codec.binary.Base64;

import java.util.Map;

public final class Base64Uploader {

    public static State save(String content, Map<String, Object> conf) {

        byte[] data = decode(content);

        long maxSize = ((Long) conf.get("maxSize")).longValue();

        if (!validSize(data, maxSize)) {
            return new BaseState(false, AppInfo.MAX_SIZE);
        }

        String suffix = FileType.getSuffix("JPG");

        String savePath = PathFormat.parse((String) conf.get("savePath"),
                (String) conf.get("filename"));

        savePath = savePath + suffix;
        String physicalPath = conf.get("rootPath") + savePath;

        // 从配置文件读取文件存储方式
        IStorageManager storageManager = StorageFactory.createStorageManager((String) conf
                .get("storageType"));
        State storageState = storageManager.saveBinaryFile(data, physicalPath);

        if (storageState.isSuccess()) {
            storageState.putInfo("url", PathFormat.format(savePath));
            storageState.putInfo("type", suffix);
            storageState.putInfo("original", "");
        }

        return storageState;
    }

    private static byte[] decode(String content) {
        return Base64.decodeBase64(content);
    }

    private static boolean validSize(byte[] data, long length) {
        return data.length <= length;
    }

}
