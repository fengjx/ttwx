import com.alibaba.druid.pool.DruidDataSource;
import generator.Generator;
import org.apache.commons.lang3.SystemUtils;

import javax.sql.DataSource;

/**
 * 在数据库表有任何变动时，运行一下 main 方法，极速响应变化进行代码重构
 */
public class CodeGenerator {

	private static final String URL = "jdbc:mysql://chwx2016.mysql.rds.aliyuncs.com:3307/mall_dev?useUnicode=true&characterEncoding=UTF-8&autoReconnect=true";
	private static final String USER = "dev";
	private static final String PWD = "rds_dev";

	public static DataSource getDataSource() {
		DruidDataSource ds = new DruidDataSource();
		ds.setUrl(URL);
		ds.setUsername(USER);
		ds.setPassword(PWD);
		return ds;
	}

	public static void main(String[] args) {
		// base model 所使用的包名
		String baseModelPackageName = "com.fengjx.modules.mall.bean.base";
		// base model 文件保存路径
		String baseOutputDir = SystemUtils.getUserDir() + "/src/main/java/com/fengjx/modules/mall/bean/base";
		System.out.println("baseOutputDir: " + baseOutputDir);

		// base model 所使用的包名
		String beanPackageName = "com.fengjx.modules.mall.bean";
		// base model 文件保存路径
		String beanOutputDir = SystemUtils.getUserDir() + "/src/main/java/com/fengjx/modules/mall/bean";
		System.out.println("beanOutputDir: " + beanOutputDir);

		// model 所使用的包名
		String modelPackageName = "com.fengjx.modules.mall.service";
		// model 文件保存路径 (MappingKit 与 DataDictionary 文件默认保存路径)
		String serviceOutputDir = SystemUtils.getUserDir() + "/src/main/java/com/fengjx/modules/mall/service";
		System.out.println("serviceOutputDir: " + serviceOutputDir);
		// 创建生成器
//        Generator gernerator = new Generator(getDataSource(), baseModelPackageName,
//                baseOutputDir, beanPackageName, beanOutputDir, modelPackageName,
//                serviceOutputDir);

		// 创建生成器
		Generator gernerator = new Generator(getDataSource(), baseModelPackageName,
				baseOutputDir);

		// 添加不需要生成的表名
		gernerator.addExcludedTable("portal_guestbook");
		gernerator.addExcludedTable("sys_dict");
		gernerator.addExcludedTable("sys_menu");
		gernerator.addExcludedTable("sys_role");
		gernerator.addExcludedTable("sys_role_menu");
		gernerator.addExcludedTable("sys_user");
		gernerator.addExcludedTable("sys_user_role");
		gernerator.addExcludedTable("wechat_ext_app");
		gernerator.addExcludedTable("wechat_ext_app_support_type");
		gernerator.addExcludedTable("wechat_material");
		gernerator.addExcludedTable("wechat_menu");
		gernerator.addExcludedTable("wechat_msg_template");
		gernerator.addExcludedTable("wechat_public_account");
		gernerator.addExcludedTable("wechat_req_msg_log");
		gernerator.addExcludedTable("wechat_resp_msg_action");
		gernerator.addExcludedTable("wechat_user_group");
		gernerator.addExcludedTable("wechat_user_info");
		gernerator.addExcludedTable("shop_cart_info");
		gernerator.addExcludedTable("shop_goods_base_info");
		gernerator.addExcludedTable("shop_goods_eval");
		gernerator.addExcludedTable("shop_goods_eval_image");
		gernerator.addExcludedTable("shop_goods_info");
		gernerator.addExcludedTable("shop_goods_info_recommend");
		gernerator.addExcludedTable("shop_goods_kv");
		gernerator.addExcludedTable("shop_order_describe");
		gernerator.addExcludedTable("shop_order_goods");
		gernerator.addExcludedTable("shop_order_info");
		gernerator.addExcludedTable("shop_size_images");
//		gernerator.addExcludedTable("shop_size_info");
		gernerator.addExcludedTable("shop_user_info");
		gernerator.addExcludedTable("shop_user_info_addr");
		gernerator.addExcludedTable("shop_user_info_pit_stat");
		gernerator.addExcludedTable("wx_user_info_generation");
		gernerator.addExcludedTable("wx_user_info_pit_detail");
		gernerator.addExcludedTable("shop_pay_wx_unifiedorder");
		gernerator.addExcludedTable("shop_order_info_log");
		gernerator.addExcludedTable("shop_banner");
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

