package com.zhazha.test;

import com.zaxxer.hikari.HikariConfig;
import com.zhazha.formlogin.FormLoginApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeansException;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest(classes = FormLoginApplication.class)
public class DatabaseConfigurationTest implements ApplicationContextAware {
	
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
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}
}
