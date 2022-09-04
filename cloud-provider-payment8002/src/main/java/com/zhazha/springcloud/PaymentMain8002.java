package com.zhazha.springcloud;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
@MapperScan("com.zhazha.springcloud.dao")
public class PaymentMain8002 {
	
	public static void main(String[] args) throws Exception {
		SpringApplication.run(PaymentMain8002.class, args);
	}
	
}