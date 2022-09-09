package com.zhazha.springcloud.client.fallback;

import com.zhazha.springcloud.client.PaymentClient;
import org.springframework.stereotype.Component;

@Component
public class PaymentFallbackClient implements PaymentClient {
    @Override
    public String paymentInfoOk(Integer id) {
        return "====PaymentHystrixService fall back paymentInfoOk，o(╥﹏╥)o====";
    }

    @Override
    public String paymentInfoTimeOut(Integer id) throws InterruptedException {
        return "====PaymentHystrixService fall back paymentInfoTimeOut，o(╥﹏╥)o====";
    }
}
