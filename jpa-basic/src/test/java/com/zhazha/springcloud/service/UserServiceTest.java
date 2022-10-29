package com.zhazha.springcloud.service;

import com.zhazha.springcloud.entity.User;
import com.zhazha.springcloud.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceTest {
	@Resource
	private UserRepository userRepository;

	@Test
	public void test01() throws Exception {
	}
	
	@Test
	void event() {
		userRepository.saveAll(Arrays.asList(
				new User("刘","一"),
				new User("陈","二"),
				new User("张","三"),
				new User("李","四"),
				new User("王","五"),
				new User("赵","六"),
				new User("孙","七"),
				new User("周","八")
		));
	}
}