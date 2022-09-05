# openFeign

1. 添加 openfeign 依赖到 order80
2. 使用`@FeignClient`注解标记 service 接口
3. 添加注解到启动类`@EnableFeignClients`

> 注意：CommonResult create(@RequestBody Payment payment); openFeign默认的传参方式就是JSON传参（@RequestBody），因此定义接口的时候可以可加可不加.

设置 openFeign超时

```yml
feign:
  client:
    config:
      ## default 设置的全局超时时间，指定服务名称可以设置单个服务的超时时间
      default:
        connectTimeout: 5000
        readTimeout: 5000
        loggerLevel: basic
```

还可以为 单独的 service 设置超时功能

```yml
feign:
  client:
    config:
      ## default 设置的全局超时时间，指定服务名称可以设置单个服务的超时时间
      default:
        connectTimeout: 5000
        readTimeout: 5000
        loggerLevel: basic
      cloud-order-service:
        connectTimeout: 50000
        readTimeout: 50000
        loggerLevel: basic
```

给`cloud-order-service`提供超时服务

openFeign的日志级别如下：
- NONE：默认的，不显示任何日志;
- BASIC：仅记录请求方法、URL、响应状态码及执行时间;
- HEADERS：除了BASIC中定义的信息之外，还有请求和响应的头信息;
- FULL：除了HEADERS中定义的信息之外，还有请求和响应的正文及元数据。