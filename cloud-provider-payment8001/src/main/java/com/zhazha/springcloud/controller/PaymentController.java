package com.zhazha.springcloud.controller;

import com.zhazha.springcloud.dto.CommonResult;
import com.zhazha.springcloud.entities.Payment;
import com.zhazha.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.UUID;

@RestController
@Slf4j
@RequestMapping("payment")
public class PaymentController {
	
	@Resource
	private PaymentService paymentService;
	
	@PostMapping(value = "create")
	public CommonResult create(@RequestBody Payment payment) {
		Integer result = paymentService.create(payment);
		log.info("******插入结果：" + result);
		if (result > 0) {
			return new CommonResult(200, "插入数据库成功", result);
		} else {
			return new CommonResult(444, "插入数据库失败", null);
		}
	}
	
	@GetMapping(value = "get/{id}")
	public CommonResult getPaymentById(@PathVariable("id") Long id) {
		Payment payment = paymentService.getPaymentById(id);
		log.info("******插入结果：" + payment);
		if (payment != null) {
			return new CommonResult(200, "查询成功", payment);
		} else {
			return new CommonResult(444, "没有查询记录", null);
		}
	}
	
	@Value("${server.port}")
	private String eurekaPort;
	
	@GetMapping("eureka")
	public String paymentEureka() {
		return "springcloud with eureka: " + eurekaPort + "\t" + UUID.randomUUID();
	}
	
}