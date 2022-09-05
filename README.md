# hystrix

需要注意, 本项目使用了 openFeign , 该组件请求的默认超时时间是 1 秒钟

> 因为 低版本的 openFeign 使用的是 ribbon , ribbon 默认 1 秒钟超时

但是 application 文件有告诉我, feign 默认不使用 hystrix

`feign.hystrix.enable=false`

所以hystrix超时熔断效果不会被触发



`@EnableCircuitBreaker`通用熔断器注解, 有了这个`@EnableHystrix`注解就不需要使用了


