package com.zhazha.springcloud.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.zhazha.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
public class PaymentController {
	@Resource
	private PaymentService paymentService;
	
	@GetMapping("/payment/hystrix/ok/{id}")
	public String paymentInfoOk(@PathVariable("id") Integer id) {
		String result = paymentService.paymentInfoOk(id);
		log.info("****result: " + result);
		return result;
	}
	
	@HystrixCommand(fallbackMethod = "fallbackPaymentInfoTimeOut")
	@GetMapping("/payment/hystrix/timeout/{id}")
	public String paymentInfoTimeOut(@PathVariable("id") Integer id) throws InterruptedException {
		String result = paymentService.paymentInfoTimeOut(id);
		log.info("****result: " + result);
		return result;
	}
	
	public String fallbackPaymentInfoTimeOut(Integer id) {
		String result = "fallback";
		log.info("****result: " + result);
		return result;
	}
}
