package com.zhazha.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

@EnableHystrixDashboard
@SpringBootApplication
public class HystrixDashboardMain9001 {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(HystrixDashboardMain9001.class, args);
	}

}