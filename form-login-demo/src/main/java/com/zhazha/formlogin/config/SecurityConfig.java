package com.zhazha.formlogin.config;

import lombok.SneakyThrows;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class SecurityConfig {
	
	@Bean
	public UserDetailsService inMemoryUserDetailsManager() {
		InMemoryUserDetailsManager userDetailsManager = new InMemoryUserDetailsManager();
		userDetailsManager.createUser(User.withUsername("user")
				.password("$2a$10$2YLsbXVa8APn4lwfnIrPRupVZMQYbiAfgKuL8Y4Y8Rt7eErxWjkEe")
				.roles("admin", "guest")
				.build());
		return userDetailsManager;
	}
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	//	/**
//	 * 这样会在mysql数据库中 insert 数据
//	 *
//	 * @param dataSource
//	 * @return
//	 */
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
	
	@Bean
	public WebSecurityCustomizer webSecurityCustomizer() {
		return web -> web.ignoring()
				.antMatchers("/js/**", "/css/**", "/images/**");
	}
	
	@SneakyThrows(Exception.class)
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) {
		return http.authorizeRequests()
				.anyRequest()
				.authenticated()
				.and()
				.formLogin()
				.loginPage("/login.html")
				.permitAll()
				.and()
				.csrf()
				.disable()
				.build()
				;
	}
	
}
