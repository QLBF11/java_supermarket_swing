# java_supermarket_swing

1.本人使用IDEA编译器

2.idea导入swing-supermarket项目后，先把lib文件中的druid-1.0.9.jar和mysql-connector-java-5.1.7-bin.jar两个包先Add as Library

3.配置文件druid-properties中的url是surpermarket.sql的地址，要先把1.project中Mysql数据库文件中的surpermarket.sql加载到Mysql先

4.程序入口为ui包下的CashierAdminUi,收银员信息为:登录名:zhangsan密码:123456方可登录

5.背景图的问题:要使用项目src中img2下的3.jpg和8.jpg要改为对应的绝对地址才行,只有ui包才需要背景图(CashdeskUi使用8.jpg，其余三个Ui使用3.jpg)
