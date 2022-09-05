package com.zhazha.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class PaymentMain8002 {
	
	public static void main(String[] args) throws Exception {
		SpringApplication.run(PaymentMain8002.class, args);
	}
	
}