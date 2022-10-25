package com.zhazha.formlogin.config;

import lombok.SneakyThrows;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SpringSecurity {
	
	/**
	 * 这样会在mysql数据库中 insert 数据
	 * @param dataSource
	 * @return
	 */
//	@Bean
//	public UserDetailsManager user(DataSource dataSource) {
//		UserDetails user = User.builder()
//				.username("user")
//				.password("{bcrypt}$2a$10$jkd6FSDPgdkRI4H/tlzWZenbXFov0Ek2Kvh2Z7e2jQsF/HNdYJHU.")
//				.roles("USER")
//				.build();
//		UserDetails admin = User.builder()
//				.username("admin")
//				.password("{bcrypt}$2a$10$jkd6FSDPgdkRI4H/tlzWZenbXFov0Ek2Kvh2Z7e2jQsF/HNdYJHU.")
//				.roles("USER", "ADMIN")
//				.build();
//		JdbcUserDetailsManager users = new JdbcUserDetailsManager(dataSource);
//		users.createUser(user);
//		users.createUser(admin);
//		return users;
//	}
	
	@SneakyThrows(Exception.class)
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) {
		return http.authorizeHttpRequests()
				.anyRequest()
				.authenticated()
				.and()
				.formLogin(httpSecurityFormLoginConfigurer -> httpSecurityFormLoginConfigurer.loginPage("/login").permitAll())
				.build();
	}
	
}
