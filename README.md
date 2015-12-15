# 天天微信平台 ttwx_v1.0.1<br />
### 免费、开源、支持二次开发扩展的微信发布平台，让你在微信开发中更专注于业务。支持多公众账号管理。<br />

qq：466516623（加Q请带备注信息）<br />
E-mail：xd-fjx@qq.com<br />
有疑问可以Q我或者给我发邮件，平时上班时间不登QQ<br />
个人公众号<br>
![简简单单](http://blog.fengjx.com/wp-content/uploads/2015/03/qrcode_for_gh_d7680c37887b_430.jpg)

### 文档：<br />
* 说明文档：doc目录下<br />
* 数据库：db目录下，建表及初始化数据<br />
* 图片上传：http://git.oschina.net/fengjx/ttwx/wikis/%E9%9D%99%E6%80%81%E8%B5%84%E6%BA%90%E6%96%87%E4%BB%B6%E7%AE%A1%E7%90%86%EF%BC%88%E5%9B%BE%E7%89%87%E4%B8%8A%E4%BC%A0%EF%BC%89
* PS：都是下班时间搞的东西，平时没多少时间弄，如果想要什么文档，或者什么不明白的地方可以联系我<br>

### 安装说明：<br />
* 1、导入数据<br />
db/mysql/db-mysql.sql<br />
* 2、修改配置文件<br />
config.properties修改环境配置<br />
db.properties修改数据库配置<br />
spring-email.xml修改成你自己的邮箱账号和密码<br />
* 3、打包部署<br />
部署tomcat或jetty，打开浏览器，访问http://yourhost:port/ttwx/<br />

### 公众号接入步骤
* 1、将授权信息（URL、TOKEN，进入后台管理，在菜单“配置授权”中查看）配置到公众平台 
* 2、从微信客户端向公众号发送验证码（系统生成，如：52731 ）
* 3、完成授权

### 界面预览<br>
http://git.oschina.net/fengjx/ttwx/wikis/%E7%95%8C%E9%9D%A2%E6%95%88%E6%9E%9C%E9%A2%84%E8%A7%88<br />


### 开发环境：<br />
* JDK1.6 + jetty1.7 + myeclipse(IntelliJ IDEA)<br />

### 开发框架：<br />
* spring3.2 + springMVC + hibernate4 + freemarker<br />

### 前端：<br />
* jquery + jquery easyui1.4 + bootstrap3 + artDialog<br />

### 设计思路：<br />
* 微信基础功能（不与任何业务系统耦合） + 插件式扩展（根据不同业务在原来系统上不修改之前代码迭代功能）<br />

### java包结构约定：<br />
* 基础功能包：com.fjx.wechat.base<br />
* 扩展功能包：com.fjx.wechat.extension<br />
* 开发SDK：com.fjx.wechat.mysdk(在jfinal-weixin基础上修改)<br />


### 统一的异常信息处理，支持一般请求和ajax请求<br />
全局同意错误码<br />
* 0：失败<br />
* 1：成功<br />
* -1：登陆超时<br />

消息处理流程<br />
![消息处理逻辑](http://blog.fengjx.com/wp-content/uploads/2015/05/消息处理流程.png)

消息处理逻辑<br />
![消息处理流程](http://blog.fengjx.com/wp-content/uploads/2015/05/消息处理逻辑.png)