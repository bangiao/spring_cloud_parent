package com.zhazha.springcloud.controller;

import com.zhazha.springcloud.client.PaymentClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
public class OrderHystrixController {
    
    @Resource
    private PaymentClient paymentClient;
    
    @GetMapping("/consumer/payment/hystrix/ok/{id}")
    public String paymentInfoOk(@PathVariable("id") Integer id) {
        return paymentClient.paymentInfoOk(id);
    }
    
    @GetMapping("/consumer/payment/hystrix/timeout/{id}")
    public String paymentInfoTimeOut(@PathVariable("id") Integer id) throws InterruptedException {
        return paymentClient.paymentInfoTimeOut(id);
    }
}