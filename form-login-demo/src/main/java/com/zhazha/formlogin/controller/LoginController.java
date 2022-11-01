package com.zhazha.formlogin.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@Slf4j
public class LoginController {
	
	@GetMapping("/hello")
	public void hello(HttpServletResponse response) throws IOException {
		response.getWriter().println("hello");
	}
	
	@GetMapping("")
	public String index() {
		return "index";
	}
}