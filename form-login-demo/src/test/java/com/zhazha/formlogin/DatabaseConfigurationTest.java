package com.zhazha.formlogin;

import com.zaxxer.hikari.HikariConfig;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest(classes = FormLoginApplication.class)
public class DatabaseConfigurationTest {
//	public static void main(String[] args) throws Exception {
//		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//		String encode = passwordEncoder.encode("123456");
//		System.err.println(encode);
//		System.err.println(passwordEncoder.matches("123456", encode));
//	}
	
	@Test
	public void test02() throws Exception {
		PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
		// {bcrypt}$2a$10$D3XCUDK44gjej2EfhBpPM.0h/wvOPH2B8vws.GGXh7J30QmdI62.y
		System.err.println(passwordEncoder.encode("123456"));
	}
	
	private ApplicationContext applicationContext;
	
	@Test
	public void testBean() throws Exception {
		HikariConfig hikariConfig = applicationContext.getBean(HikariConfig.class);
		System.out.println(hikariConfig);
	}
}
