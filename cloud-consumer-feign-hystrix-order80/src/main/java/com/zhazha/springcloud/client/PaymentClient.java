package com.zhazha.springcloud.client;

import com.zhazha.springcloud.client.fallback.PaymentFallbackClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "consul-provider-payment", fallback = PaymentFallbackClient.class)
public interface PaymentClient {
	
	@GetMapping("/payment/hystrix/ok/{id}")
	public String paymentInfoOk(@PathVariable("id") Integer id);
	
	@GetMapping("/payment/hystrix/timeout/{id}")
	public String paymentInfoTimeOut(@PathVariable("id") Integer id) throws InterruptedException;
	
}
