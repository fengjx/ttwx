
package com.fengjx.commons.utils;

import com.fasterxml.uuid.EthernetAddress;
import com.fasterxml.uuid.Generators;
import com.fasterxml.uuid.impl.TimeBasedGenerator;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.SystemUtils;

import java.util.UUID;

/**
 * @author peng
 */
public final class CommonUtils {

    /**
     * 主键生成器，基于时间戳+机器地址的
     *
     * @return 主键
     */
    public static String getPrimaryKey() {
        EthernetAddress nic = EthernetAddress.fromInterface();
        TimeBasedGenerator uuidGenerator = Generators.timeBasedGenerator(nic);
        UUID uuid = uuidGenerator.generate();
        return uuid.toString().replaceAll("-", "").toLowerCase();
    }

    /**
     * 获得项目classPath
     *
     * @return
     */
    public static String getClassPath() {
        return getClassPath(null);
    }

    /**
     * 获得项目classPath
     *
     * @return
     */
    public static String getClassPath(String path) {
        if (StringUtils.isBlank(path)) {
            path = "/";
        }
        String classPath = Thread.currentThread().getContextClassLoader().getResource(path)
                .getPath();
        if (SystemUtils.IS_OS_WINDOWS) {
            classPath = classPath.substring(1, classPath.length());
        }
        return Encodes.urlDecode(classPath);
    }

}
