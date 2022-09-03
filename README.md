# springcloud_study_parent

微服务相关知识学习主分支

# 结构相关

单机版的 Eureka server 配置详情

cloud-eureka-server7001 是eureka 的服务端

和 nacos 一样, 但 nacos 只需要下载 github server 端就好, eureka 却需要自己创建一个server项目

# 注解

eureka 整合 springcloud 需要在启动类上使用到注解 `@EnableEurekaServer`

主要作用还是标记, 标记一个项目为 Eureka 的 server 项目

# application配置

```yml
#false表示不向注册中心注册自己。
register-with-eureka: false
#false表示自己端就是注册中心，我的职责就是维护服务实例，并不需要去检索服务
fetch-registry: false
```
需要注意, eureka server 本身就是一个 server, 所以不需要注册

同时也不需要去检索任何服务
