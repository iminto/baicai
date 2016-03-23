# baicaiP2P
基于SpringMVC的p2p互联网金融借贷系统   
技术栈：JDK7+tomcat 7+MySQL 5.5+Redis+SpringMVC+beetl+fastjson

##技术特点
    基于Java中最流行的springMVC构建，没有使用mybatis或Hibernate作为数据层ORM，而是使用了jdbcTemplate,bcORM(系统中自己实现的简易ORM层)以及beetlSQL（计划中），达到极简开发，随心所欲，不受约束的数据层。   
    使用redis来存放热点数据，使用无session模式开发部署。
    本项目来自于对一个完整老项目的重构，实际运行中的项目是有的。所以这个新的重构后的项目目前还不完善，很多重构的代码和页面都没有放上去，甚至数据库表都只有部分。我只是利用业务时间，偶尔更新下。所以此项目的代码仅作为基础架构的参考。
    
##运行方法
    本项目是标准的mvn项目
  ```xml
  mvn clean install 
  mvn tomcat7:run
```
