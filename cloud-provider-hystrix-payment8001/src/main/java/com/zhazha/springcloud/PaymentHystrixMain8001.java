package com.zhazha.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
// 通用熔断器注解, 有了这个下面的`EnableHystrix`注解就不需要了
@EnableCircuitBreaker
//@EnableHystrix
public class PaymentHystrixMain8001 {
	public static void main(String[] args) throws Exception {
		SpringApplication.run(PaymentHystrixMain8001.class, args);
	}
}