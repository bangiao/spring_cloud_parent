package com.zhazha.springcloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class IndexController {
	
	@GetMapping("rewritePath")
	public String rewritePath() {
		return "index rewritePath";
	}
	
}