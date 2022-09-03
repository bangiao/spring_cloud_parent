package com.zhazha.springcloud.controller;

import com.zhazha.springcloud.entities.CommonResult;
import com.zhazha.springcloud.entities.Payment;
import com.zhazha.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
public class PaymentController {
 
	@Resource
	private PaymentService paymentService;
	
	//返回给前端的结果集
	@PostMapping(value = "/payment/create")
	public CommonResult create(Payment payment) {
		Integer result = paymentService.create(payment);
		log.info("******插入结果：" + result);
		if (result > 0) {
			return new CommonResult(200, "插入数据库成功", result);
		} else {
			return new CommonResult(444, "插入数据库失败", null);
		}
	}
	
	@GetMapping(value = "/payment/get/{id}")
	public CommonResult getPaymentByID(@PathVariable("id") Long id) {
		Payment payment = paymentService.getPaymentById(id);
		log.info("******插入结果：" + payment);
		if (payment != null) {
			return new CommonResult(200, "查询成功", payment);
		} else {
			return new CommonResult(444, "没有查询记录", null);
		}
	}
}
