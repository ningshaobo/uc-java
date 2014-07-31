uc-java
=======
ucenter java客户端代码
struts2  ucenter-api

快速使用
eclipse import maven 工程

修改配置文件：# 主要修改如下三项，其他可空
uc-java / src / main / resources / config.properties
# ================================================
# Ucenter API for JAVA
# ================================================
UC_API = http://localhost/ucenter    #此处为ucenter的地址
UC_IP = 
UC_KEY = 12345678   #此处为在ucenter 设置的应用 通信秘钥
UC_APPID = 4        #此处为在ucenter 设置的应用 ID号
UC_CONNECT =  

在web容器（如tomcat）运行，观察ucenter 的应用管理， 是否此应用连接成功。成功后，即可在本程序中 使用ucenter 的用户、密码登陆。
