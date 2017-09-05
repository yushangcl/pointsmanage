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



#<div style='color:red'>2017-09-04:</div>

##bug：

1、增加消费记录的时候，手机号码没有带过来，导致无法保存

2、增加兑换记录时，参数错误，新增operMode应该是add 但是传入的是update

##业务：
1.“增加消费记录和兑换记录的使用习惯是在客户电话中输入号码查询到该客户，再增加该客户的消费记录或者兑换记录”这句话我的理解是
    比如在客户信息业务，查询到客户信息，这时候应可以进行对客户的消费金额或者积分进行修改（按钮直接跳转到对应页面），而不是再需要到其他两个页面进行输入用户信息再操作
    这样的话，用户用起来会比较繁琐一点，当然以上只是我个人的意见

2.删除功能，删除的时候不能删除原来的记录时对的，但是需要用什么东西来表示一下，状态或者已删除的标记，这样用户看起来不会那么奇怪，以后查看记录也不会对应不上    
    
