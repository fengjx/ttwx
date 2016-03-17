
package com.fengjx.commons.ext.baidu.ueditor.upload;

import java.util.HashMap;
import java.util.Map;

/**
 * StorageManager构造工厂
 *
 * @Created by FengJianxin on 2015/9/20.
 * @Email xd-fjx@qq.com
 */
public class StorageFactory {

    private static class StorageManagerHolder {

        public static final Map<String, IStorageManager> STORAGE_MANAGERMAP;

        static {
            STORAGE_MANAGERMAP = new HashMap<>();
            STORAGE_MANAGERMAP.put("local", new StorageManager());
            STORAGE_MANAGERMAP.put("qiniu", new QiNiuStorageManager());
        }

    }

    /**
     * 创建制定的文件管理器
     *
     * @param type
     * @return
     */
    public static IStorageManager createStorageManager(String type) {
        return StorageManagerHolder.STORAGE_MANAGERMAP.get(type);
    }

    /**
     * 默认文件管理器
     *
     * @return
     */
    public static IStorageManager createStorageManager() {
        return StorageManagerHolder.STORAGE_MANAGERMAP.get("local");
    }

}
