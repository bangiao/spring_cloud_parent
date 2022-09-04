package com.zhazha.springcloud;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@MapperScan(value = "com.zhazha.springcloud.dao")
@SpringBootApplication
public class PaymentMain8001 {
	
	public static void main(String[] args) throws Exception {
		SpringApplication.run(PaymentMain8001.class, args);
	}
	
}