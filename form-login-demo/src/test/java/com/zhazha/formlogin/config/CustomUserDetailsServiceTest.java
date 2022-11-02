package com.zhazha.formlogin.config;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.zhazha.formlogin.po.QUsers;
import com.zhazha.formlogin.po.Users;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@SpringBootTest
class CustomUserDetailsServiceTest {

    @PersistenceContext
    private EntityManager entityManager;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void loadUserByUsername() {
        QUsers qUsers = QUsers.users;
        JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(this.entityManager);
        List<Users> usersList = jpaQueryFactory.selectFrom(qUsers)
                .where(qUsers.username.contains("s"))
                .limit(20)
                .offset(10)
                .fetch();
        for (Users users : usersList) {
            System.err.println(users);
        }
    }
}