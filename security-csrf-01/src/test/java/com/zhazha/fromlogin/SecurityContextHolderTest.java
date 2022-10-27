package com.zhazha.fromlogin;

import org.junit.Test;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Collection;

public class SecurityContextHolderTest {
	
	@Test
	public void test02() throws Exception {
		SecurityContext context = SecurityContextHolder.getContext();
		Authentication authentication = context.getAuthentication();
		String username = (String) authentication.getPrincipal();
		String password = (String) authentication.getCredentials();
		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
	}
	
	@Test
	public void test01() throws Exception {
		// 创建一个 securityContext
		SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
		// 创建一个认证
		// 里面存储了认证用户的用户名和密码, 还有授权角色
		TestingAuthenticationToken authenticationToken = new TestingAuthenticationToken("username", "password", "USER");
		// 将认证信息绑定到上下文
		securityContext.setAuthentication(authenticationToken);
		// 设置到全局上下文
		// 将新的SecurityContext与当前执行线程相关联。
		SecurityContextHolder.setContext(securityContext);
	}
	
}
