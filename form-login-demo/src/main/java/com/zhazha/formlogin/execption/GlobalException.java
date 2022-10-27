package com.zhazha.formlogin.execption;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice()
public class GlobalException {

	@ExceptionHandler(Exception.class)
	public String exceptionHandler(Exception e) {
		System.out.println("报错: " + e);
		return e.getMessage();
	}

}
