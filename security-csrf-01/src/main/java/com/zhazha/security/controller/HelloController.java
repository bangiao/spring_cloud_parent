package com.zhazha.security.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 复现 csrf 漏洞
 */
@RestController
@Slf4j
public class HelloController {
	
	@PostMapping("/transfer")
	public void transferMoney(String name, Integer money) {
		System.out.println("name = " + name);
		System.out.println("money = " + money);
	}

}