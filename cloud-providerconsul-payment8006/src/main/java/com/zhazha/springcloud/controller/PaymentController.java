package com.zhazha.springcloud.controller;

import com.zhazha.springcloud.dto.CommonResult;
import com.zhazha.springcloud.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@Slf4j
public class PaymentController {
	@Value("${server.port}")
	private String serverPort;
	
	@GetMapping("/payment/consul")
	public String paymentInfo() {
		return "springcloud with consul: " + serverPort + "\t\t" + UUID.randomUUID().toString();
	}
	
	@PostMapping("/payment/create")
	public CommonResult create(Payment payment) {
		payment.setId(12456L);
		payment.setSerial("778899");
		return new CommonResult(200, "操作成功", payment);
	}
	
}