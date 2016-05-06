## 天天微信平台
- 文档有时间再补上，有疑问联系，QQ:466516623，Email:xd-fjx@qq.com
- 因为只有我一个人维护，所以关于项目的维护，得看我工作时间忙不忙了

我的个人微信
![](http://fengjx-cdn.oss-cn-shenzhen.aliyuncs.com/my/images/wechat.png =300)



### 技术栈



<br>
启动项目 <br>
mvn tomcat7:run <br>

打包项目 <br>
mvn clean package

以上命令均可以使用参数来指定环境，如
mvn -Prelease clean package （生产包）
mvn -Ptest tomcat7:run （测试环境启动）

导入数据 <br>
doc/db/new_install.sql (mysql)

