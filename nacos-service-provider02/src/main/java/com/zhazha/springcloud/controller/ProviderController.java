package com.zhazha.springcloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicInteger;

@RestController
@Slf4j
public class ProviderController {

    @Value("${spring.application.name}")
    private String name;

    @Value("${server.port}")
    private Integer port;

    private final AtomicInteger counter = new AtomicInteger();

    @GetMapping("sayHi")
	public String sayHi() {
		return "provider service name = "+ name + "\t port = " + port + "\t counter = " + counter.incrementAndGet();
	}
	
}