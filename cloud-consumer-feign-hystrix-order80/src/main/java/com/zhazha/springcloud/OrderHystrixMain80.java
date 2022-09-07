package com.zhazha.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableDiscoveryClient
@EnableFeignClients
@SpringBootApplication
//@EnableHystrix
@EnableCircuitBreaker
public class OrderHystrixMain80 {
	
	public static void main(String[] args) throws Exception {
		SpringApplication.run(OrderHystrixMain80.class, args);
	}
	
}