package com.zhazha.formlogin.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
	
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
	
	@GetMapping("")
	public String index(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Object user = authentication.getPrincipal();
		String username = "用户名为: ";
		if (user instanceof User) {
			username = ((User) user).getUsername();
		}
		model.addAttribute("username", username);
		return "index";
	}
}