package com.zhazha.springcloud.service.impl;

import com.zhazha.springcloud.dao.PaymentDao;
import com.zhazha.springcloud.entities.Payment;
import com.zhazha.springcloud.service.PaymentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class PaymentServiceImpl implements PaymentService {
	
	@Resource
	private PaymentDao paymentDao;
	
	@Override
	public Integer create(Payment payment) {
		return paymentDao.create(payment);
	}
	
	@Override
	public Payment getPaymentById(Long id) {
		return paymentDao.getPaymentById(id);
	}
}
