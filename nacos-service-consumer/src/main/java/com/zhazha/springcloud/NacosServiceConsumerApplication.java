package com.zhazha.springcloud;

import com.zhazha.config.NacosConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@EnableDiscoveryClient
@SpringBootApplication
@LoadBalancerClient(name = "nacos-service-provider", configuration = NacosConfig.class)
public class NacosServiceConsumerApplication {

    @LoadBalanced
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

	public static void main(String[] args) throws Exception {
		SpringApplication.run(NacosServiceConsumerApplication.class, args);
	}

}