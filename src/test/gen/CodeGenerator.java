import com.alibaba.druid.pool.DruidDataSource;
import generator.Generator;
import org.apache.commons.lang3.SystemUtils;

import javax.sql.DataSource;

/**
 * 在数据库表有任何变动时，运行一下 main 方法，极速响应变化进行代码重构
 */
public class CodeGenerator {

	private static final String URL = "jdbc:mysql://120.24.159.81:3306/ttwx-dev?useUnicode=true&characterEncoding=UTF-8&autoReconnect=true";
	private static final String USER = "dev";
	private static final String PWD = "dev";

	public static DataSource getDataSource() {
		DruidDataSource ds = new DruidDataSource();
		ds.setUrl(URL);
		ds.setUsername(USER);
		ds.setPassword(PWD);
		return ds;
	}
	
	public static void main(String[] args) {
		// base model 所使用的包名
		String baseModelPackageName = "com.fengjx.modules.mall.bean";
		// base model 文件保存路径
		String baseModelOutputDir = SystemUtils.getUserDir() + "/src/main/gen/com/fengjx/modules/mall/bean";
		// model 所使用的包名 (MappingKit 默认使用的包名)
		String modelPackageName = "com.fengjx.modules.mall.service";
		// model 文件保存路径 (MappingKit 与 DataDictionary 文件默认保存路径)
		String modelOutputDir = SystemUtils.getUserDir() + "/src/main/gen/com/fengjx/modules/mall/service";
		// 创建生成器
		Generator gernerator = new Generator(getDataSource(), baseModelPackageName, baseModelOutputDir, modelPackageName, modelOutputDir);
		// 添加不需要生成的表名
		gernerator.addExcludedTable("Wechat*");
		// 设置是否在 Model 中生成 dao 对象
		gernerator.setGenerateDaoInModel(false);
		// 设置是否生成字典文件
		gernerator.setGenerateDataDictionary(false);
		// 设置需要被移除的表名前缀用于生成modelName。例如表名 "osc_user"，移除前缀 "osc_"后生成的model名为 "User"而非 OscUser
		// gernerator.setRemovedTableNamePrefixes("t_");
		// 生成
		gernerator.generate();
	}

}

