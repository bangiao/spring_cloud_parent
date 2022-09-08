package com.zhazha.springcloud.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.zhazha.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
public class Payment02Controller {
    @Resource
    private PaymentService paymentService;

    /**
     * 2秒之后超时
     *
     * @param id
     * @return
     * @throws InterruptedException
     */
    @HystrixCommand
    @GetMapping("/payment02/hystrix/timeout/{id}")
    public String paymentInfoTimeOut(@PathVariable("id") Integer id) throws InterruptedException {
        String result = "hello world";
        TimeUnit.SECONDS.sleep(3);
        log.info("****result: " + result);
        return result;
    }
}
