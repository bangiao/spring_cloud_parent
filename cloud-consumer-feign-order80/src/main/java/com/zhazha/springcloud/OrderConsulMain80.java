package com.zhazha.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableDiscoveryClient
// 添加注解, 启动 open feign
@EnableFeignClients
@SpringBootApplication
public class OrderConsulMain80 {
	
	public static void main(String[] args) throws Exception {
		SpringApplication.run(OrderConsulMain80.class, args);
	}
	
//	@Bean
//	@LoadBalanced
//	public RestTemplate getRestTemplate() {
//		return new RestTemplate();
//	}

}