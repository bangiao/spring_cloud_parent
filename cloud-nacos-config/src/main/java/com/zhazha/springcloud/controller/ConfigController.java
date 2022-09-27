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

    @Value("${other01:}")
    private String other01;

    @Value("${other02:}")
    private String other02;

    @GetMapping("get")
    public String get() {
    	return String.format("username = %s, password = %s, other01 = %s, other02 = %s\n",  username, password, other01, other02);
    }



}