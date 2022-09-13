package com.zhazha.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class PaymentMain8007 {
	
	public static void main(String[] args) throws Exception {
		SpringApplication.run(PaymentMain8007.class, args);
	}
	
}