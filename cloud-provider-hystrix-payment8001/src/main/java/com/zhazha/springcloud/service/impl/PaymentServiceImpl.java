package com.zhazha.springcloud.service.impl;

import com.zhazha.springcloud.service.PaymentService;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class PaymentServiceImpl implements PaymentService {
	@Override
	public String paymentInfoOk(Integer id) {
		return "线程池:" + Thread.currentThread().getName() + "paymentInfo_OK,id: " + id + "\t" + "O(∩_∩)O";
	}
	
	@Override
	public String paymentInfoTimeOut(Integer id) {
		long seconds = 5;
		try {
			TimeUnit.SECONDS.sleep(seconds);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return "线程池:" + Thread.currentThread().getName() + "paymentInfo_TimeOut,id: " + id + "\t" + "O(∩_∩)O，耗费" + seconds + "秒";
	}
}
