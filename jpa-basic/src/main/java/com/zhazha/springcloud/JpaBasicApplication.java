package com.zhazha.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EntityScan(value = {"com.zhazha.springcloud.entity"})
@EnableJpaRepositories(value = {"com.zhazha.springcloud.repository"})
@SpringBootApplication
public class JpaBasicApplication {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(JpaBasicApplication.class, args);
    }

}