package com.zhazha.springcloud.service;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.dsl.CaseBuilder;
import com.querydsl.core.types.dsl.StringExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.zhazha.springcloud.entity.*;
import com.zhazha.springcloud.repository.UserRepository;
import org.hibernate.dialect.Dialect;
import org.hibernate.loader.plan.exec.query.internal.SelectStatementBuilder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.relational.core.sql.SelectBuilder;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@SpringBootTest
class UserServiceTest {
	@PersistenceContext
	private EntityManager entityManager;

	@Resource
	private UserRepository userRepository;
	private JPAQueryFactory jpaQueryFactory;

	@Test
	public void test05() throws Exception {
		QUser qUser = QUser.user;
		CaseBuilder caseBuilder = new CaseBuilder();
		StringExpression expression = caseBuilder.when(qUser.username.eq("刘"))
				.then("小刘")
				.when(qUser.username.eq("李"))
				.then("小李")
				.otherwise(qUser.username);
		List<Tuple> stringList = jpaQueryFactory.select(qUser.id, expression, qUser.password)
				.from(qUser).fetch();
		stringList.forEach(System.err::println);
	}

	@Test
	public void test04() throws Exception {
		List<String> names = Arrays.asList("李", "赵", "刘");
		QUser qUser = QUser.user;
		JPAQuery<User> query = jpaQueryFactory.selectFrom(qUser);
		BooleanBuilder builder = new BooleanBuilder();
		names.forEach(s -> builder.or(qUser.username.eq(s)));
		List<User> userList = query.where(builder)
				.fetch();
		userList.forEach(System.err::println);
	}

	@Test
	public void test03() throws Exception {
		QItem qItem = QItem.item;
		List<Item> itemList = jpaQueryFactory.selectFrom(qItem)
				.fetch();
		itemList.forEach(System.err::println);
	}

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