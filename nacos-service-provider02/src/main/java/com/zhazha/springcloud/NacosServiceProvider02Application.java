package com.zhazha.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class NacosServiceProvider02Application {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(NacosServiceProvider02Application.class, args);
	}

}