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
 * Model 生成器
 */
public class ModelServiceGenerator {

	protected String packageTemplate =
		"%npackage %s;%n%n";
	protected String importTemplate =
		"import com.fengjx.commons.plugin.db.Model;%n"+
		"import org.springframework.stereotype.Component;%n"+
		"import %s.%s;%n%n";
	protected String classDefineTemplate =
		"/**%n" +
		" * Autu Generated .%n" +
		" */%n" +
		"@Component%n" +
		"public class %s extends Model<%s> {%n";
	protected String daoTemplate =
			"\tpublic static final %s me = new %s();%n";
	
	protected String modelPackageName;
	protected String baseModelPackageName;
	protected String modelOutputDir;
	protected boolean generateDaoInModel = true;
	
	public ModelServiceGenerator(String modelPackageName, String baseModelPackageName, String modelOutputDir) {
		if (StrUtil.isBlank(modelPackageName))
			throw new IllegalArgumentException("modelPackageName can not be blank.");
		if (modelPackageName.contains("/") || modelPackageName.contains("\\"))
			throw new IllegalArgumentException("modelPackageName error : " + modelPackageName);
		if (StrUtil.isBlank(baseModelPackageName))
			throw new IllegalArgumentException("baseModelPackageName can not be blank.");
		if (baseModelPackageName.contains("/") || baseModelPackageName.contains("\\"))
			throw new IllegalArgumentException("baseModelPackageName error : " + baseModelPackageName);
		if (StrUtil.isBlank(modelOutputDir))
			throw new IllegalArgumentException("modelOutputDir can not be blank.");
		
		this.modelPackageName = modelPackageName;
		this.baseModelPackageName = baseModelPackageName;
		this.modelOutputDir = modelOutputDir;
	}
	
	public void setGenerateDaoInModel(boolean generateDaoInModel) {
		this.generateDaoInModel = generateDaoInModel;
	}
	
	public void generate(List<TableMeta> tableMetas) {
		System.out.println("Generate model ...");
		for (TableMeta tableMeta : tableMetas)
			genModelContent(tableMeta);
		wirtToFile(tableMetas);
	}
	
	protected void genModelContent(TableMeta tableMeta) {
		StringBuilder ret = new StringBuilder();
		genPackage(ret);
		genImport(tableMeta, ret);
		genClassDefine(tableMeta, ret);
		ret.append(String.format("}%n"));
		tableMeta.serviceContent = ret.toString();
	}
	
	protected void genPackage(StringBuilder ret) {
		ret.append(String.format(packageTemplate, modelPackageName));
	}
	
	protected void genImport(TableMeta tableMeta, StringBuilder ret) {
		ret.append(String.format(importTemplate, baseModelPackageName, tableMeta.beanName));
	}
	
	protected void genClassDefine(TableMeta tableMeta, StringBuilder ret) {
		ret.append(String.format(classDefineTemplate,
				tableMeta.serviceName, tableMeta.beanName));
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
	 * 若 model 文件存在，则不生成，以免覆盖用户手写的代码
	 */
	protected void wirtToFile(TableMeta tableMeta) throws IOException {
		File dir = new File(modelOutputDir);
		if (!dir.exists())
			dir.mkdirs();
		
		String target = modelOutputDir + File.separator + tableMeta.serviceName + ".java";
		
		File file = new File(target);
		if (file.exists()) {
			return ;	// 若 Model 存在，不覆盖
		}
		
		FileWriter fw = new FileWriter(file);
		try {
			fw.write(tableMeta.serviceContent);
		}
		finally {
			fw.close();
		}
	}
}


