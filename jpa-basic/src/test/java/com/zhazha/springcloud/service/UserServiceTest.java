package com.zhazha.springcloud.service;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.zhazha.springcloud.entity.*;
import com.zhazha.springcloud.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Arrays;

@SpringBootTest
class UserServiceTest {
	@PersistenceContext
	private EntityManager entityManager;

	@Resource
	private UserRepository userRepository;
	private JPAQueryFactory jpaQueryFactory;

	@Test
	public void test02() throws Exception {
		QCart qCart = QCart.cart;
		QItem qItem = QItem.item;
		Cart cart = jpaQueryFactory.select(qCart)
				.from(qCart)
				.join(qItem)
				.on(qCart.id.eq(qItem.cart.id))
				.where(qCart.id.eq(1))
				.fetchOne();
		System.err.println("---------------------------------------------------------------");
		System.err.println(cart);
		System.err.println("---------------------------------------------------------------");
	}

	@Test
	public void test01() throws Exception {
		QUser user = QUser.user;
		User one = jpaQueryFactory.select(user).from(user)
				.where(user.username.eq("李"))
				.fetchOne();
		System.err.println(one);
	}

	@Test
	void event() {
		userRepository.saveAll(Arrays.asList(
				new User("刘", "一"),
				new User("陈", "二"),
				new User("张", "三"),
				new User("李", "四"),
				new User("王", "五"),
				new User("赵", "六"),
				new User("孙", "七"),
				new User("周", "八")
		));
	}

	@BeforeEach
	void setUp() {
		jpaQueryFactory = new JPAQueryFactory(this.entityManager);
	}

	@AfterEach
	void tearDown() {
	}
}