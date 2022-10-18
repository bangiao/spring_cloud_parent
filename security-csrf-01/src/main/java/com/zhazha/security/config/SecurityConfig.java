package com.zhazha.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
	
	@Bean
	public SecurityFilterChain configure(HttpSecurity http) throws Exception {
//		http.authorizeRequests().anyRequest().authenticated()
//				.and()
//				.formLogin()
//				.and()
//				.csrf()
//				.disable();
//		return http.build();
		http.csrf().disable();
		return http.build();
	}
	
}
