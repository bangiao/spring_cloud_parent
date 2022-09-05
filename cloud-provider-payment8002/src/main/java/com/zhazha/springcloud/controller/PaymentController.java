package com.zhazha.springcloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@Slf4j
public class PaymentController {
	@Value("${server.port}")
	private String serverPort;
	
	@GetMapping("/payment")
	public String paymentInfo() {
		return "springcloud: " + serverPort + "\t\t" + UUID.randomUUID();
	}
}