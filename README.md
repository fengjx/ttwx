#说明
启动项目 <br>
mvn tomcat7:run <br>

打包项目 <br>
mvn clean package

以上命令均可以使用参数来指定环境，如
mvn -Prelease clean package （生产包）
mvn -Ptest tomcat7:run （测试环境启动）

导入数据 <br>
doc/db/new_install.sql (mysql)

