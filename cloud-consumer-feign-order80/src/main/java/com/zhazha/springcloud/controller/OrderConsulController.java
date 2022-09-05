package com.zhazha.springcloud.controller;

import com.zhazha.springcloud.dto.CommonResult;
import com.zhazha.springcloud.entities.Payment;
import com.zhazha.springcloud.service.PaymentClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class OrderConsulController {

//    public static final String INVOKE_URL = "http://consul-provider-payment";
//
//    @Resource
//    private RestTemplate restTemplate;
    
    @Resource
    private PaymentClient paymentClient;

    @GetMapping(value = "/consumer/payment/consul")
    public String paymentInfo() {
//        String result = restTemplate.getForObject(INVOKE_URL + "/payment/consul", String.class);
//        System.out.println("消费者调用支付服务(consule)--->result:" + result);
//        return result;
        return paymentClient.paymentInfo();
    }
    
    @GetMapping("/consumer/payment/create")
    public CommonResult create(Payment payment) {
    	return paymentClient.create(payment);
    }
    
}