#关于properties加载配置
1.系统默认会加载resources下的app.properties的配置和env/dev下的app.properties
为了方便开放和部署,可以单独配置每个环境的properties文件,分别放在resources下env/dev or env/release or env/test 下
2.Maven打包和编译时会自动替换spring-config.xml的环境配置项目,默认是替换为${ENV_MODE:dev}
3.如果有特殊需求,可自行添加ENV_MODE环境变量改变默认值
  例如:
 设置一个系统环境变量ENV_MODE=release,可以设置为操作系统环境或加java 启动参数-DENV_MODE="release"
 这样,你配置的环境相关的配置就会覆盖默认的配置
 
 #eclipse 开发初始化
 1.mvn eclipse:eclipse 生成 eclipse项目
 2.修改.classpath 添加maven 引用
 <classpathentry kind="con" path="org.eclipse.m2e.MAVEN2_CLASSPATH_CONTAINER">
		<attributes>
			<attribute name="maven.pomderived" value="true"/>
			<attribute name="org.eclipse.jst.component.dependency" value="/WEB-INF/lib"/>
		</attributes>
	</classpathentry>