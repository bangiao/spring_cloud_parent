package com.zhazha.springcloud.controller;

import com.zhazha.springcloud.dto.CommonResult;
import com.zhazha.springcloud.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
@Slf4j
public class OrderController {
	
	public static final String PaymentSrv_URL = "http://localhost:8001";
	
	@Resource
	private RestTemplate restTemplate;
	
	/**
	 * 客户端用浏览器是get请求，但是底层实质发送post调用服务端8001
	 *
	 * @param payment
	 * @return
	 */
	@GetMapping("/payment/create")
	public CommonResult<Payment> create(Payment payment) {
		return restTemplate.postForObject(PaymentSrv_URL + "/payment/create", payment, CommonResult.class);
	}
	
	@GetMapping("/payment/get/{id}")
	public CommonResult<Payment> getPayment(@PathVariable("id") Long id) {
		return restTemplate.getForObject(PaymentSrv_URL + "/payment/get/" + id, CommonResult.class, id);
	}
	
}