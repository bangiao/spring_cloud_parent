package com.zhazha.springcloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
@Slf4j
@RequestMapping("provider")
public class MapRequestHeaderController {
	
	@GetMapping("order")
	public String order(@RequestHeader(value = "Blue", required = false) String blue, @RequestHeader(value = "X-Request-Red", required = false) String xRequestRed) {
		System.err.println("blue: " + blue);
		System.err.println("X-Request-Red: " + xRequestRed);
		return "order —— blue：" + blue + "\t\t X-Request-Red: " + xRequestRed;
	}
	
	@GetMapping("setResponseHeader")
	public String setResponseHeader(HttpServletResponse response, String username) {
		System.err.println("username: " + username);
		response.addHeader("X-Response-Red", "1234");
		return "X-Response-Red";
	}
	
	@GetMapping("rewritePath")
	public String reWritePath() {
		return "rewritePath";
	}
	
}