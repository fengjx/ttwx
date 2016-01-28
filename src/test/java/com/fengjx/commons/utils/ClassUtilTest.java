
package com.fengjx.commons.utils;

import com.fengjx.commons.plugin.db.annotation.Mapper;

import org.junit.Test;

import java.io.IOException;
import java.util.Set;

/**
 * @author fengjx.
 * @date：2015/6/19 0019
 */
public class ClassUtilTest {

    @Test
    public void test() throws IOException, ClassNotFoundException {
        // 标识是否要遍历该包路径下子包的类名
        boolean recursive = true;
        // 指定的包名
        String pkg = "com.fengjx.ttwx.modules.wechat.model";
        Set<Class<?>> set = null;
        // 增加 author.class的过滤项，即可只选出ClassTestDemo
        set = ClassUtil.getClasses(pkg, recursive, Mapper.class);

        for (Class<?> cls : set) {
            System.out.println("==>" + cls.getName());
        }
    }

}
