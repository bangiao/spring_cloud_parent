package com.zhazha.springcloud;

import com.zhazha.springcloud.entity.Cart;
import com.zhazha.springcloud.entity.Item;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import javax.annotation.Resource;
import java.util.HashSet;

@SpringBootTest
class JpaBasicApplicationTest {
	
	@Resource
	private SessionFactory sessionFactory;
	
	@Resource
	private ApplicationContext applicationContext;
	private Session session;
	
	@BeforeEach
	void setUp() {
		session = sessionFactory.openSession();
	}
	
	@AfterEach
	void tearDown() {
	}
	
	
	@Test
	public void testUpdate() throws Exception {
		Item item = this.session.get(Item.class, 2);
		System.out.println(item);
	}
	
	@Test
	public void testSave() throws Exception {
		Transaction transaction = session.beginTransaction();
		Cart cart = new Cart();
		cart.setId(1);
		HashSet<Item> items = new HashSet<>();
		Item item = new Item();
		item.setId(1);
		item.setCart(cart);
		items.add(item);
//		Item item2 = new Item();
//		item2.setId(2);
//		item2.setCart(cart);
//		items.add(item2);
//		cart.setItems(items);
		session.save(cart);
		session.save(item);
		transaction.commit();
		System.out.println(cart);
		System.out.println(item);
//		System.out.println(item2);
	}
	
}