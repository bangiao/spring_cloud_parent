package com.zhazha.springcloud.service.impl;

import com.zhazha.springcloud.dao.PaymentDao;
import com.zhazha.springcloud.entities.Payment;
import com.zhazha.springcloud.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService {
    @Autowired
    private PaymentDao paymentDao;
    @Override    
    public int create(Payment payment) {
        return paymentDao.create(payment);    
    }    
    @Override    
    public Payment getPaymentById(Long id) {
        return paymentDao.getPaymentById(id);
    }
}
