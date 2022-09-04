package com.zhazha.springcloud.service;

import com.zhazha.springcloud.entities.Payment;
import org.springframework.web.bind.annotation.RequestBody;

public interface PaymentService {
	
	Integer create(@RequestBody Payment payment);
	
	Payment getPaymentById(Long id);
	
}
