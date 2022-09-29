package com.zhazha.springcloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@Slf4j
public class ConsumerController {

    private final RestTemplate restTemplate;

    public static final String URL = "http://nacos-service-provider";

    @Autowired
    public ConsumerController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("consumer/sayHi")
    public String sayHi() {
        return restTemplate.getForObject(URL + "/sayHi", String.class);
    }

}