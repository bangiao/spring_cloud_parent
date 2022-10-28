# 注意这是个多分支项目

## 装备工作

1. 创建 jpa 数据库
   `create database IF NOT EXISTS jpa;`
2. 创建表
```sql
DROP TABLE if exists items;
create table items
(
id int primary key auto_increment,
cart_id int
);
DROP TABLE if exists cart;
create table cart
(
cart_id int primary key auto_increment
);
```

3. 创建项目, 并添加相关依赖
4. 添加application.yml相关依赖
5. 创建启动类

