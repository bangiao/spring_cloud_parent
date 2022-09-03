package com.zhazha.springcloud.service;


import com.zhazha.springcloud.entities.Payment;

public interface PaymentService {
	int create(Payment payment);
	Payment getPaymentById(Long id);
}
