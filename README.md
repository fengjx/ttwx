#关于properties加载配置
系统默认会加载resources下的app.properties的配置和env/dev下的app.properties
为了方便开放和部署,可以单独配置每个环境的properties文件,分别放在resources下env/dev or env/release or env/test 下
然后在设置一个系统环境变量ENV_MODE=dev,可以设置为操作系统环境或加java 启动参数-DENV_MODE="dev"
这样,你配置的环境相关的配置就会覆盖默认的配置