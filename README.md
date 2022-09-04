# consul 下载和启动

去consul官方去下载exe

[consul官方下载](https://www.consul.io/downloads)

然后使用 `consul agent -dev` 启动 consul

访问 https://localhost:8500 可以打开 consul 控制台

客户端服务发现也需要使用上 localhost:8500 

# 创建提供方

创建项目`cloud-providerconsul-payment8006`

添加 consul 依赖, 主要用于服务发现

```xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-consul-discovery</artifactId>
</dependency>
```

添加 application.yml
```yml
###consul服务端口号
server:
  port: 8006

spring:
  application:
    name: consul-provider-payment
  ####consul注册中心地址
  cloud:
    consul:
      host: localhost
      port: 8500
      discovery:
        #hostname: 127.0.0.1
        service-name: ${spring.application.name}
```

在启动类上添加`EnableDiscoveryClient`注解, 开启服务发现功能

`service-name: ${spring.application.name}`这个名字最终会在 consul 控制台上新建一个`Service`

# consul消费端

依赖情况一样, 直接复制就行

application.yml
```yaml
###consul服务端口号
server:
  port: 80

spring:
  application:
    name: cloud-consumer-order
  ####consul注册中心地址
  cloud:
    consul:
      host: localhost
      port: 8500
      discovery:
        #hostname: 127.0.0.1
        service-name: ${spring.application.name}
```


