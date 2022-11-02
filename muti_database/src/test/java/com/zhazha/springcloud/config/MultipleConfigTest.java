package com.zhazha.springcloud.config;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.zhazha.springcloud.security.entity.QUser;
import com.zhazha.springcloud.security.entity.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@SpringBootTest
class MultipleConfigTest {

    @PersistenceContext(unitName = "securityPersistenceUnit")
    private EntityManager entityManager;

    @Test
    public void test01() throws Exception {
        QUser qUser = QUser.user;
        JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(this.entityManager);
        List<User> userList = jpaQueryFactory.selectFrom(qUser)
                .where(qUser.username.startsWith("s")/*, qUser.enabled.eq(1)*/)
                .where(qUser.enabled.eq(1))
                .limit(20)
                .offset(10)
                .fetch();
        userList.forEach(System.err::println);
    }

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }
}