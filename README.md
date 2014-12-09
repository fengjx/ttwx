#ttwx V1.0<br />
作者：阿豆<br />
qq：466516623（加Q请带备注信息）<br />
E-mail：xd-fjx@qq.com<br />
有疑问可以Q我或者给我发邮件，平时上班时间不登QQ<br />

说明：免费、开源、支持二次开发扩展的微信发布平台，让你在微信开发中更专注于业务。<br />

文档：<br />
说明文档：doc目录下<br />
数据库：db目录下，建表及初始化数据<br />

演示地址：http://ttwx.sinaapp.com(免费的SAE有时不太稳定)<br />

开发环境：<br />
JDK1.6 + jetty1.7 + myeclipse(IntelliJ IDEA)<br />

开发框架：<br />
spring3.2 + springMVC + hibernate4 + freemarker<br />

前端：<br />
jquery + jquery easyui1.4 + bootstrap3 + artDialog<br />

设计思路：<br />
微信基础功能（不与任何业务系统耦合） + 插件式扩展（根据不同业务在原来系统上不修改之前代码迭代功能）<br />

java包结构：<br />
基础功能包：com.fjx.wechat.base<br />
扩展功能包：com.fjx.wechat.extension<br />
开发SDK：com.fjx.wechat.mysdk(在jfinal-weixin基础上修改)<br />


统一的异常信息处理，支持一般请求和ajax请求<br />
全局同意错误码<br />
0：失败<br />
1：成功<br />
-1：登陆超时<br />

消息处理逻辑<br />
![消息处理逻辑](http://fengjxblog-fjxstorage.stor.sinaapp.com/ttwx/%E6%B6%88%E6%81%AF%E5%A4%84%E7%90%86%E6%B5%81%E7%A8%8B.png)

<br />
![消息处理流程](http://fengjxblog-fjxstorage.stor.sinaapp.com/ttwx/%E6%B6%88%E6%81%AF%E5%A4%84%E7%90%86%E9%80%BB%E8%BE%91.png)