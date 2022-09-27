package com.zhazha.springcloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class ConfigController {

    @Value("${username:}")
    private String username;

    @Value("${password:}")
    private String password;

    @GetMapping("get")
    public String get() {
    	return String.format("username = %s, password = %s\n",  username, password);
    }

}