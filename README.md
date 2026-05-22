# 房屋出租管理系统

基于 Java Servlet + JSP 的房屋出租管理 Web 应用，实现房源管理、租客登记、租赁合同、管理员后台等完整业务流程。

## 技术栈
- Java Servlet / JSP
- MySQL 8.0
- Maven 构建
- HTML / CSS

## 数据库
4 张核心表：users、houses、tenants、contracts

详见 database_init.sql

## 功能模块
- 管理员：房源增删改、租客管理、合同查看
- 普通用户：房源浏览、个人信息维护
- 登录认证 + 角色区分

## 运行
1. 导入 database_init.sql 到 MySQL
2. 配置数据库连接
3. mvn package 生成 WAR，部署到 Tomcat
