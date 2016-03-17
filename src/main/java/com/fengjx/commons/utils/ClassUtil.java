
package com.fengjx.commons.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.net.JarURLConnection;
import java.net.URL;
import java.util.Enumeration;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * 用于获取指定包名下的所有类名.<br/>
 * 并可设置是否遍历该包名下的子包的类名.<br/>
 * 并可通过Annotation(内注)来过滤，避免一些内部类的干扰.<br/>
 * 
 * @author Sodino E-mail:sodino@qq.com
 * @version Time：2014年2月10日 下午3:55:59
 */
public class ClassUtil {

    private static final Logger LOG = LoggerFactory.getLogger(ClassUtil.class);

    /**
     * 通过包名获得包含指定注解的class
     *
     * @param pkgName 包名
     * @param isRecursive 是否遍历子包
     * @param annotation 对应注解
     * @return
     */
    public static Set<Class<?>> getClasses(String pkgName, boolean isRecursive,
            Class<? extends Annotation> annotation) throws IOException, ClassNotFoundException {
        Set<Class<?>> classSet = new LinkedHashSet<Class<?>>();
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        // 按文件的形式去查找
        String strFile = pkgName.replaceAll("\\.", "/");
        Enumeration<URL> urls = loader.getResources(strFile);
        while (urls.hasMoreElements()) {
            URL url = urls.nextElement();
            if (url != null) {
                String protocol = url.getProtocol();
                String pkgPath = Encodes.urlDecode(url.getPath());
                LOG.debug("protocol:" + protocol + " path:" + pkgPath);
                if ("file".equals(protocol)) {
                    // 本地自己可见的代码
                    findClassName(classSet, pkgName, pkgPath, isRecursive, annotation);
                } else if ("jar".equals(protocol)) {
                    // 引用第三方jar的代码
                    findClassName(classSet, pkgName, url, isRecursive, annotation);
                }
            }
        }
        return classSet;
    }

    private static void findClassName(Set<Class<?>> classSet, String pkgName, String pkgPath,
            boolean isRecursive, Class<? extends Annotation> annotation)
                    throws ClassNotFoundException {
        if (classSet == null) {
            return;
        }
        File[] files = filterClassFiles(pkgPath);// 过滤出.class文件及文件夹
        LOG.debug("files:" + ((files == null) ? "null" : "length=" + files.length));
        if (files != null) {
            for (File f : files) {
                String fileName = f.getName();
                if (f.isFile()) {
                    // .class 文件的情况
                    String clazzName = getClassName(pkgName, fileName);
                    addClassName(classSet, clazzName, annotation);
                } else {
                    // 文件夹的情况
                    if (isRecursive) {
                        // 需要继续查找该文件夹/包名下的类
                        String subPkgName = pkgName + "." + fileName;
                        String subPkgPath = pkgPath + "/" + fileName;
                        findClassName(classSet, subPkgName, subPkgPath, true, annotation);
                    }
                }
            }
        }
    }

    /**
     * 第三方Jar类库的引用。<br/>
     * 
     * @throws IOException
     */
    private static void findClassName(Set<Class<?>> classSet, String pkgName, URL url,
            boolean isRecursive, Class<? extends Annotation> annotation)
                    throws IOException, ClassNotFoundException {
        JarURLConnection jarURLConnection = (JarURLConnection) url.openConnection();
        JarFile jarFile = jarURLConnection.getJarFile();
        LOG.debug("jarFile:" + jarFile.getName());
        Enumeration<JarEntry> jarEntries = jarFile.entries();
        while (jarEntries.hasMoreElements()) {
            JarEntry jarEntry = jarEntries.nextElement();
            String jarEntryName = jarEntry.getName(); // 类似：sun/security/internal/interfaces/TlsMasterSecret.class
            String clazzName = jarEntryName.replace("/", ".");
            int endIndex = clazzName.lastIndexOf(".");
            String prefix = null;
            if (endIndex > 0) {
                String prefix_name = clazzName.substring(0, endIndex);
                endIndex = prefix_name.lastIndexOf(".");
                if (endIndex > 0) {
                    prefix = prefix_name.substring(0, endIndex);
                }
            }
            if (prefix != null && jarEntryName.endsWith(".class")) {
                if (prefix.equals(pkgName)) {
                    LOG.debug("jar entryName:" + jarEntryName);
                    addClassName(classSet, clazzName, annotation);
                } else if (isRecursive && prefix.startsWith(pkgName)) {
                    // 遍历子包名：子类
                    LOG.debug("jar entryName:" + jarEntryName + " isRecursive:" + isRecursive);
                    addClassName(classSet, clazzName, annotation);
                }
            }
        }
    }

    private static File[] filterClassFiles(String pkgPath) {
        if (pkgPath == null) {
            return null;
        }
        // 接收 .class 文件 或 类文件夹
        return new File(pkgPath).listFiles(new FileFilter() {
            @Override
            public boolean accept(File file) {
                return (file.isFile() && file.getName().endsWith(".class")) || file.isDirectory();
            }
        });
    }

    private static String getClassName(String pkgName, String fileName) {
        int endIndex = fileName.lastIndexOf(".");
        String clazz = null;
        if (endIndex >= 0) {
            clazz = fileName.substring(0, endIndex);
        }
        String clazzName = null;
        if (clazz != null) {
            clazzName = pkgName + "." + clazz;
        }
        return clazzName;
    }

    private static void addClassName(Set<Class<?>> classSet, String clazzName,
            Class<? extends Annotation> annotation) throws ClassNotFoundException {
        if (classSet != null && clazzName != null) {
            Class<?> clazz = null;
            clazz = Class.forName(clazzName);
            if (clazz != null) {
                if (annotation == null) {
                    classSet.add(clazz);
                    LOG.debug("add:" + clazz);
                } else if (clazz.isAnnotationPresent(annotation)) {
                    classSet.add(clazz);
                    LOG.debug("add annotation:" + clazz);
                }
            }
        }
    }
}
