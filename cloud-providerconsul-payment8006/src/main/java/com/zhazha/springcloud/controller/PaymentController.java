package com.zhazha.springcloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

@RestController
@Slf4j
public class PaymentController {
	@Value("${server.port}")
	private String serverPort;

	@GetMapping("/payment/consul")
	public String paymentInfo(HttpServletRequest request) {
		String name1 = request.getHeader("name1");
		String name2 = request.getHeader("name2");
		return "springcloud with consul: " + serverPort + "\t\t" + UUID.randomUUID().toString() + "\t\t name1: " + name1 + "\t name2: " + name2;
	}
}