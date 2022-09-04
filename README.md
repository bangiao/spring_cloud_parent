# eureka 集群方式

![](https://yangfan-typroa.oss-cn-beijing.aliyuncs.com/image-20220123123020106.png "eureka集群原理图")

根据上面这幅图我们知道 eureka server 和 eureka provider 可以集群

## hosts 修改

目录: C:\Windows\System32\drivers\etc\hosts

```text
127.0.0.1 eureka7001.com
127.0.0.1 eureka7002.com
```

## eureka server 集群

1. 创建 `cloud-eureka-server7002` 项目, 给 `eureka server` 添加新的集群
2. **copy** `cloud-eureka-server7001`的 `pom.xml` 和 `application.yml` 配置文件
3. 修改`cloud-eureka-server7001` 和 `cloud~7002`的`application.yml`

> 这里需要注意的是, eureka server 集群的方式主要是**相互交换地址**

- `cloud-eureka-server7001`的`application.yml`:

```yml
server:
  port: 7001
eureka:
  instance:
    hostname: eureka7001.com #eureka服务端的实例名称
  client:
    #false表示不向注册中心注册自己。
    register-with-eureka: false
    #false表示自己端就是注册中心，我的职责就是维护服务实例，并不需要去检索服务
    fetch-registry: false
    service-url:
      #设置与Eureka Server交互的地址查询服务和注册服务都需要依赖这个地址。
      defaultZone: http://eureka7002.com:7002/eureka/
```

- `cloud-eureka-server7002`的`application.yml`:

```yml
server:
  port: 7002

eureka:
  instance:
    hostname: eureka7002.com #eureka服务端的实例名称
  client:
    #false表示不向注册中心注册自己。
    register-with-eureka: false
    #false表示自己端就是注册中心，我的职责就是维护服务实例，并不需要去检索服务
    fetch-registry: false
    service-url:
      #设置与Eureka Server交互的地址查询服务和注册服务都需要依赖这个地址。
      defaultZone: http://eureka7001.com:7001/eureka/
```

> 修改的地方就两个: ① port ② hostname ③ defaultZone

