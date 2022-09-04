package com.zhazha.springcloud.controller;

import com.zhazha.springcloud.dto.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
@Slf4j
@RequestMapping("consumer")
public class OrderController {
	
	@Resource
	private RestTemplate restTemplate;
	
	public static final String INVOKE_URL = "http://CLOUD-PAYMENT-SERVICE";
	
	@GetMapping("payment/get/{id}")
	public CommonResult get(@PathVariable("id") Long id) {
		return restTemplate.getForObject(INVOKE_URL + "/payment/get/{id}", CommonResult.class, id);
	}
	
	@GetMapping("payment/eureka")
	public String orderEureka() {
		return "order80: " + restTemplate.getForObject(INVOKE_URL + "/payment/eureka", String.class);
	}
	
}