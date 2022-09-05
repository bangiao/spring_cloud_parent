package com.zhazha.springcloud.service;

import com.zhazha.springcloud.dto.CommonResult;
import com.zhazha.springcloud.entities.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(value = "consul-provider-payment")
public interface PaymentClient {
	
	@GetMapping("/payment/consul")
	String paymentInfo();
	
	@PostMapping("/payment/create")
	CommonResult create(Payment payment);
	
}
