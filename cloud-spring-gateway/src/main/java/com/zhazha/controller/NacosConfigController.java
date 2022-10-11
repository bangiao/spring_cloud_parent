package com.zhazha.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试nacos，从nacos配置中心读取配置
 */
@RestController
@Slf4j
public class NacosConfigController {
	
	@Value("${user:}")
	private String username;
	
	@Value("${password:}")
	private String password;
	
	@Value("${other01:}")
	private String other01;
	
	@Value("${other02:}")
	private String other02;
	
	@GetMapping("get")
	public String get() {
		return "username: " + username + "\t password: " + password + "\t other01: " + other01 + "\t other02: " + other02;
	}

}