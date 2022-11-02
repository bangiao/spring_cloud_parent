# 多数据源思路

多数据源就是多DataSource, 在查询表的时候需要指定查询哪个数据库, 或者在配置 DAO 时, 配置好DAO查询的数据库

# 根据配置判断步骤

```yaml
server:
  port: 8080
  http2:
    enabled: true
spring:
  application:
    name: jpa-basic
  jpa:
    database-platform: org.hibernate.dialect.MySQL8Dialect
    show-sql: true
    open-in-view: false
    properties:
      "hibernate.format_sql": true
    hibernate:
      ddl-auto: update
    database: mysql
  datasource:
    url: jdbc:mysql://127.0.0.1:3306/jpa?characterEncoding=utf8&connectTimeout=1000&socketTimeout=3000&autoReconnect=true&useUnicode=true&useSSL=false&serverTimezone=UTC
    username: root
    password: 123456
    driver-class-name: com.mysql.cj.jdbc.Driver
    type: com.alibaba.druid.pool.DruidDataSource
```

从上面的配置, 我们知道有这么几个配置需要修改:
1. 替换掉 springboot 的 spring.datasource 配置, 也就是 org.springframework.boot.autoconfigure.jdbc.DataSourceProperties 这个类
2. 替换掉 jpa 的配置 spring.jpa 配置, 也就是 org.springframework.boot.autoconfigure.orm.jpa.JpaProperties

上面就是基本配置了

还有别的需要替换

我们知道, 使用queryDSL需要使用到 EntityManager 注入到 `new JPAQueryFactory(this.entityManager)` 进行查询

分析源码看看, 可以得出 EntityManager 才是关键, 它可以决定我们使用哪个数据源