package com.zhazha.springcloud.controller;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.zhazha.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
@Slf4j
@DefaultProperties(defaultFallback = "globalFallback", commandProperties = {
        @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "4000")
})
public class PaymentController {

    private final static AtomicInteger counter = new AtomicInteger(0);

    @Value("${server.port}")
    private String port;

    @Resource
    private PaymentService paymentService;

    @GetMapping("/payment/hystrix/ok/{id}")
    public String paymentInfoOk(@PathVariable("id") Integer id) {
        String result = paymentService.paymentInfoOk(id);
        log.info("****result: " + result);
        return result;
    }

    @HystrixCommand(fallbackMethod = "fallbackPaymentInfoTimeOut", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "6000")
    })
    @GetMapping("/payment/hystrix/timeout/{id}")
    public String paymentInfoTimeOut(@PathVariable("id") Integer id) throws InterruptedException {
        String result = paymentService.paymentInfoTimeOut(id);
        log.info("****result: " + result);
        return result;
    }

    @HystrixCommand
    @GetMapping("/payment/hystrix/timeout01/{id}")
    public String paymentInfoTimeOut01(@PathVariable("id") Integer id) throws InterruptedException {
        String result = paymentService.paymentInfoTimeOut(id);
        log.info("timeout01: " + result);
        return result;
    }

    @HystrixCommand
    @GetMapping("/payment/hystrix/timeout02/{id}")
    public String paymentInfoTimeOut02(@PathVariable("id") Integer id) throws InterruptedException {
        String result = paymentService.paymentInfoTimeOut(id);
        log.info("timeout01: " + result);
        return result;
    }
    public String fallbackPaymentInfoTimeOut(Integer id) {
        return "<h1>port: " + port + "访问'/payment/hystrix/timeout'失败: " + counter.getAndIncrement() + "</h1>";
    }

    public String globalFallback() {
        return "<h1>Global port: " + port + " counter: " + counter.getAndIncrement() + "系统繁忙, 请稍后再尝试</h1>";
    }

    // ====服务熔断=====
    @GetMapping("/payment/circuit/{id}")
    public String paymentCircuitBreaker(@PathVariable("id") Integer id) {
        String result = paymentService.circuitBreaker(id);
        log.info("****result: " + result);
        return result;
    }

}
