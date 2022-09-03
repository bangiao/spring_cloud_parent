# springcloud_study_parent

微服务相关知识学习主分支

# 创建一个 eureka server

单机版的 Eureka server 配置详情

cloud-eureka-server7001 是eureka 的服务端

和 nacos 一样, 但 nacos 只需要下载 github server 端就好, eureka 却需要自己创建一个server项目

maven 包

`spring-cloud-starter-netflix-eureka-server`

注解

eureka 整合 `springcloud` 需要在启动类上使用到注解 `@EnableEurekaServer`

主要作用还是标记, 标记一个项目为 Eureka 的 server 项目

application配置

```yml
#false表示不向注册中心注册自己。
register-with-eureka: false
#false表示自己端就是注册中心，我的职责就是维护服务实例，并不需要去检索服务
fetch-registry: false
```
需要注意, `eureka server` 本身就是一个 `server`, 所以不需要注册

同时也不需要去检索任何服务


# 创建一个注册项目

`cloud-provider-payment8001`

maven 包

`spring-cloud-starter-netflix-eureka-client`

```yml
#表示是否将自己注册进EurekaServer默认为true。
register-with-eureka: true
#是否从EurekaServer抓取已有的注册信息，默认为true。单节点无所谓，集群必须设置为true才能配合ribbon使用负载均衡
fetchRegistry: true
```

添加注解

`@EnableEurekaClient`

标记该项目为 `client`

项目在这里连接上了 server:
```yml
defaultZone: http://localhost:7001/eureka
```

# 创建一个消费端(客户端)

maven:

```xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
</dependency>
```

注解: `@EnableEurekaClient`

注册到 eureka server 注册中心: `defaultZone: http://localhost:7001/eureka`

现在按照步骤启动项目:
- server7001
- payment8001
- order80

分别启动项目