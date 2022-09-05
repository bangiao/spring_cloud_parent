package com.zhazha.springcloud.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "consul-provider-payment")
public interface PaymentClient {
	
	@GetMapping("/payment/consul")
	String paymentInfo();
	
}
