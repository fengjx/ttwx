
package com.fengjx.commons.ext.baidu.ueditor.hunter;

import java.util.Map;

/**
 * @Created by FengJianxin on 2015/9/20.
 * @Email xd-fjx@qq.com
 */
public class FileManagerFactory {

    /**
     *
     * @param conf
     * @return
     */
    public static IFileManager createFileManager(Map<String, Object> conf) {
        IFileManager res = null;
        String storageType = (String) conf.get("storageType");
        if ("local".equals(storageType)) {
            res = new FileManager(conf);
        } else if ("qiniu".equals(storageType)) {
            res = new QiNiuFileManager(conf);
        }
        return res;
    }

}
