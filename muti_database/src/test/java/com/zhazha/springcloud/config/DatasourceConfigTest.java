package com.zhazha.springcloud.config;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.zhazha.springcloud.first.entity.QUser;
import com.zhazha.springcloud.first.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@SpringBootTest
class DatasourceConfigTest {

    @PersistenceContext(unitName = "firstPersistenceUnit")
    private EntityManager entityManager;

    @Test
    public void test01() throws Exception {
        JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(this.entityManager);
        QUser qUser = QUser.user;
        User user = jpaQueryFactory.selectFrom(qUser).fetchFirst();
        System.out.println(user);
    }


}