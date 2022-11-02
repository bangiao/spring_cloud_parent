package com.zhazha.springcloud.config;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.zhazha.springcloud.jpa.entity.QUser;
import com.zhazha.springcloud.jpa.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@SpringBootTest
class DatasourceConfigTest {

    @PersistenceContext(unitName = "jpaPersistenceUnit")
    private EntityManager entityManager;

    @Test
    public void test01() throws Exception {
        JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(this.entityManager);
        QUser qUser = QUser.user;
        List<User> userList = jpaQueryFactory.selectFrom(qUser)
                .where(qUser.username.containsIgnoreCase("s"))
                .limit(20)
                .offset(10)
                .fetch();
        userList.forEach(System.err::println);
    }


}