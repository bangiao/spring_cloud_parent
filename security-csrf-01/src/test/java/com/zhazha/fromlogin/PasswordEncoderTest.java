package com.zhazha.fromlogin;

import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.*;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;

import java.util.HashMap;
import java.util.Map;

public class PasswordEncoderTest {
	
	@Test
	public void test02() throws Exception {
		Map<String, PasswordEncoder> encoders = new HashMap<>();
		encoders.put("bcrypt", new BCryptPasswordEncoder());
		encoders.put("noop", NoOpPasswordEncoder.getInstance());
		encoders.put("pbkdf2", new Pbkdf2PasswordEncoder());
		encoders.put("scrypt", new SCryptPasswordEncoder());
		encoders.put("sha256", new StandardPasswordEncoder());
		
		PasswordEncoder passwordEncoder =
				new DelegatingPasswordEncoder("sha256", encoders);
		String encodePwd = passwordEncoder.encode("zhazha");
		System.out.println(passwordEncoder.matches("zhazha", encodePwd));
	}
	
	@Test
	public void test01() throws Exception {
		PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
		String encodePwd = passwordEncoder.encode("zhazha");
		System.out.println(passwordEncoder.matches("zhazha", encodePwd));
	}
	
	@Test
	public void testDelegatingPasswordEncoder() throws Exception {
		String idForEncode = "bcrypt";
		Map<String, PasswordEncoder> encoders = new HashMap<>();
		encoders.put(idForEncode, new BCryptPasswordEncoder());
		encoders.put("noop", NoOpPasswordEncoder.getInstance());
		encoders.put("pbkdf2", new Pbkdf2PasswordEncoder());
		encoders.put("scrypt", new SCryptPasswordEncoder());
		encoders.put("sha256", new StandardPasswordEncoder());
		/**
		 * {bcrypt}$2a$10$vlfPYPppAXBGt9/BFm9Jy.6aWK1giles43x8kVF0T4oESoZ5xbTBm
		 * {noop}zhazha
		 * {pbkdf2}57bd73dfc02be7a97efc021a0dea31e877b02dc8be1867683aaacf03258d9170527f75fb24ec7457
		 * {bcrypt}$2a$10$Ev/zOkyqLL5iSrNDBUiMMOukJX7nJewl6YRidkcVcwZq8jPmxUfK2
		 */
		PasswordEncoder passwordEncoder =
				new DelegatingPasswordEncoder("bcrypt", encoders);
		System.out.println(passwordEncoder.encode("zhazha"));
		passwordEncoder =
				new DelegatingPasswordEncoder("noop", encoders);
		System.out.println(passwordEncoder.encode("zhazha"));
		passwordEncoder =
				new DelegatingPasswordEncoder("pbkdf2", encoders);
		System.out.println(passwordEncoder.encode("zhazha"));
		
		// PasswordEncoderFactories 方式获得
		passwordEncoder =
				PasswordEncoderFactories.createDelegatingPasswordEncoder();
		System.out.println(passwordEncoder.encode("zhazha"));
	}
	
}
