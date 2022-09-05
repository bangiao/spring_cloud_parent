package com.zhazha.springcloud.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("consul-provider-payment")
public interface PaymentClient {
	
	@GetMapping("/payment/hystrix/ok/{id}")
	public String paymentInfoOk(@PathVariable("id") Integer id);
	
	@GetMapping("/payment/hystrix/timeout/{id}")
	public String paymentInfoTimeOut(@PathVariable("id") Integer id) throws InterruptedException;
	
}
