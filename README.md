## 客户积分管理系统


> 采用Java语言开发,基于MySQL数据库;具有性能优异、简单实用、安全稳定、支持全文检索的特点。

# 项目结构介绍
## java:
#### database 数据库文件
#### action 控制层
#### dubbo.service service层，与数据Mapper打交道
#### entity 实体类（bean）
#### exception 异常类
#### Mapper 数据库
#### utils 工具类
##### 日期工具类，正则工具类，字符工具类

## resource
 #### jdbc-basic.properties 数据库配置文件
 #### log4j2.xml 日志配置文件
 #### spring-config-mvc.xml SpringMVC配置文件
 #### spring-config-mvc-test.xml testSpringMVC配置文件
 
## test:
  #### 单元测试
 
  所有test需要继承BaseTest类，实现spring配置


war包地址
https://lazyo-my.sharepoint.com/personal/app_lanyo_win/_layouts/15/guestaccess.aspx?folderid=102c23de44455437e8ea19b1f748be9b7&authkey=AdRaQ_lroJBC1FEZKjT4Kbo