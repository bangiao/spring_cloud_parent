package com.zhazha.formlogin.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import java.util.HashMap;

@Configuration
public class SecurityConfig {
	
	@Bean
	public CustomUserDetailsService detailsService() {
		return new CustomUserDetailsService();
	}

//	@Bean
//	public UserDetailsService inMemoryUserDetailsManager() {
//		InMemoryUserDetailsManager userDetailsManager = new InMemoryUserDetailsManager();
//		userDetailsManager.createUser(User.withUsername("user")
//				.password("$2a$10$2YLsbXVa8APn4lwfnIrPRupVZMQYbiAfgKuL8Y4Y8Rt7eErxWjkEe")
//				.roles("admin", "guest")
//				.build());
//		return userDetailsManager;
//	}
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public ObjectMapper objectMapper() {
		return new ObjectMapper();
	}
	
	
	@Bean
	public SecurityFilterChain securityWebFilterChain(HttpSecurity httpSecurity, ObjectMapper objectMapper) throws Exception {
		return httpSecurity.authorizeHttpRequests()
				.antMatchers("/js/**", "/css/**", "/images/**")
				.permitAll()
				.mvcMatchers("/hello")
				.permitAll() // 白名单 允许
				.anyRequest()
				.authenticated()
				.and()
				.formLogin()
				.loginPage("/login") // 配置了这个, loginProcessingUrl 就必须跟着配置
				.loginProcessingUrl("/doLogin")
				.usernameParameter("name")
				.passwordParameter("pwd")
//				.defaultSuccessUrl("/")
//				.successForwardUrl("/")
				.successHandler((request, response, authentication) -> {
					HashMap<String, Object> retHashMap = new HashMap<>();
					retHashMap.put("msg", "登录成功");
					retHashMap.put("status", HttpStatus.OK.value());
					retHashMap.put("authentication", authentication);
					response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
					String s = objectMapper.writeValueAsString(retHashMap);
					response.getWriter().println(s);
				})
				.permitAll()
				.and()
				.csrf()
				.disable()
				.build();
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
	
	/*
	已弃用
Use a org.springframework.security.web.SecurityFilterChain Bean to configure HttpSecurity or a WebSecurityCustomizer Bean to configure WebSecurity
	 */
//	@Bean
//	public WebSecurityCustomizer webSecurityCustomizer() {
//		return web -> web.ignoring()
//				.antMatchers("/js/**", "/css/**", "/images/**");
//	}

//	@SneakyThrows(Exception.class)
//	@Bean
//	public SecurityFilterChain filterChain(HttpSecurity http) {
//		return http
//				.authorizeRequests()
//				.anyRequest()
//				.authenticated()
//				.and()
//				.formLogin()
//				.loginPage("/login")
//				.loginProcessingUrl("/doLogin") // 自定义提交表单请求
//				.usernameParameter("name") // 前端 form 标签的 username input 的参数从默认的 username 设置为 name
//				.passwordParameter("pwd") // 前端 form 标签的 password input 的参数从默认的 password 设置为 pwd
//				.defaultSuccessUrl("/", true) // 登录成功后自动跳转的地址(未必, 如果登录之前的地址是 /from, 那么登录之后会跳转到 from) , 当然如果你把 defaultSuccessUrl 的第二个参数设置为 true, 那么效果和 successForwardUrl 一样, 不管登录之前是哪个网址, 登录之后都是配置的 /index 网址
////				.successForwardUrl("/index") // 不管登录之前是在哪个地址,登录之后都跳转到 /index , 相当于 defaultSuccessUrl("/index", true)
//				.failureForwardUrl("/login?error") // 登录失败转发的地址
////				.failureUrl("/error") // 登录失败重定向的地址, 默认是 /login?error, 这里修改成了 error.html
//				.permitAll()
//				.and()
//				.logout()
//				.logoutUrl("/logout") // 默认注销的 URL 是 /logout，是一个 GET 请求，我们可以通过 logoutUrl 方法来修改默认的注销 URL。
////				.logoutRequestMatcher(new AntPathRequestMatcher("/logout", "POST")) // logoutRequestMatcher 方法不仅可以修改注销 URL，还可以修改请求方式，实际项目中，这个方法和 logoutUrl 任意设置一个即可。
//				.logoutSuccessUrl("/login") // logoutSuccessUrl 表示注销成功后要跳转的页面。
//				.deleteCookies() // deleteCookies 用来清除 cookie。
//				.clearAuthentication(true) // clearAuthentication 和 invalidateHttpSession 分别表示清除认证信息和使 HttpSession 失效，默认可以不用配置，默认就会清除。
//				.invalidateHttpSession(true)
//				.permitAll()
//				.and()
//				.csrf()
//				.disable()
//				.build();
//	}
	
}
