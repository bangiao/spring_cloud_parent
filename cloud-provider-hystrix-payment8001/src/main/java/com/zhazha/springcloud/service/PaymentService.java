package com.zhazha.springcloud.service;

public interface PaymentService {
	/**
	 * 正常访问一切 OK
	 *
	 * @param id
	 * @return
	 */
	String paymentInfoOk(Integer id);
	
	/**
	 * 超时访问，演示降级
	 *
	 * @param id
	 * @return
	 */
	String paymentInfoTimeOut(Integer id);
}
