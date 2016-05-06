/**
 * Copyright (c) 2011-2016, James Zhan 詹波 (jfinal@126.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package generator;

import com.fengjx.commons.utils.StrUtil;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * Base model 生成器
 */
public class BeanGenerator {

    protected String packageTemplate = "%npackage %s;%n%n";
    protected String importTemplate =
            "import %s.%s;%n" +
            "import com.fengjx.commons.plugin.db.annotation.Mapper;%n%n";
    protected String classDefineTemplate = "/**%n" +
            " * Autu Generated.%n" +
            " */%n" +
            "@Mapper(table=\"%s\", id = \"%s\")%n" +
            "@SuppressWarnings(\"serial\")%n" +
            "public class %s extends %s {%n%n";

    protected String baseBeanPackageName;
    protected String beanPackageName;
    protected String beanOutputDir;

    public BeanGenerator(String beanPackageName, String baseBeanPackageName, String beanOutputDir) {
        if (StrUtil.isBlank(beanPackageName))
            throw new IllegalArgumentException("beanPackageName can not be blank.");
        if (beanPackageName.contains("/") || beanPackageName.contains("\\"))
            throw new IllegalArgumentException("beanPackageName error : " + beanPackageName);

        if (StrUtil.isBlank(baseBeanPackageName))
            throw new IllegalArgumentException("baseBeanPackageName can not be blank.");
        if (baseBeanPackageName.contains("/") || baseBeanPackageName.contains("\\"))
            throw new IllegalArgumentException(
                    "baseBeanPackageName error : " + baseBeanPackageName);

        if (StrUtil.isBlank(beanOutputDir))
            throw new IllegalArgumentException("beanOutputDir can not be blank.");

        this.beanPackageName = beanPackageName;
        this.baseBeanPackageName = baseBeanPackageName;
        this.beanOutputDir = beanOutputDir;
    }

    public void generate(List<TableMeta> tableMetas) {
        System.out.println("Generate bean ...");
        for (TableMeta tableMeta : tableMetas)
			genBeanContent(tableMeta);
        wirtToFile(tableMetas);
    }

    protected void genBeanContent(TableMeta tableMeta) {
        StringBuilder ret = new StringBuilder();
        genPackage(ret);
        genImport(tableMeta, ret);
        genClassDefine(tableMeta, ret);
        ret.append(String.format("}%n"));
        tableMeta.beanContent = ret.toString();
    }

    protected void genPackage(StringBuilder ret) {
        ret.append(String.format(packageTemplate, beanPackageName));
    }

    protected void genImport(TableMeta tableMeta, StringBuilder ret) {
        ret.append(String.format(importTemplate, baseBeanPackageName, tableMeta.baseBeanName));
    }

    protected void genClassDefine(TableMeta tableMeta, StringBuilder ret) {
        ret.append(String.format(classDefineTemplate,
                tableMeta.name, tableMeta.primaryKey, tableMeta.beanName,
                tableMeta.baseBeanName));
    }

    protected void wirtToFile(List<TableMeta> tableMetas) {
        try {
            for (TableMeta tableMeta : tableMetas)
                wirtToFile(tableMeta);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * base model 覆盖写入
     */
    protected void wirtToFile(TableMeta tableMeta) throws IOException {
        File dir = new File(beanOutputDir);
        if (!dir.exists())
            dir.mkdirs();

        String target = beanOutputDir + File.separator + tableMeta.beanName + ".java";
        FileWriter fw = new FileWriter(target);
        try {
            fw.write(tableMeta.beanContent);
        } finally {
            fw.close();
        }
    }
}
