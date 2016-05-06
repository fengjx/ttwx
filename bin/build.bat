@echo 正式发布版本打包

cd ..
@mvn -Prelease clean package
@pause