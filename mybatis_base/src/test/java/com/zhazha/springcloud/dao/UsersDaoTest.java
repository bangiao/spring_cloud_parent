package com.zhazha.springcloud.dao;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhazha.springcloud.entity.Users;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
//@MapperScan(basePackages = "com.zhazha.springcloud.dao")
public class UsersDaoTest {

    @Resource
    private UsersDao usersDao;

    @Resource
    private ObjectMapper objectMapper;

    @Test
    public void test03() throws Exception {
        com.github.pagehelper.Page<Users> page = PageHelper.<Users>startPage(30, 10)
                .doSelectPage(() -> usersDao.selectList(Wrappers.lambdaQuery(Users.class)
                        .likeRight(Users::getUsername,"s")
                        .eq(Users::getEnabled,true)));
        PageInfo<Users> pageInfo = new PageInfo<>(page);
        System.out.println(objectMapper.writeValueAsString(pageInfo));
    }

    @Test
    public void test02() throws Exception {
        PageHelper.startPage(20, 10);
        List<Users> usersList = usersDao.selectList(Wrappers.emptyWrapper());
        PageInfo<Users> pageInfo = new PageInfo<>(usersList);
        System.out.println(objectMapper.writeValueAsString(pageInfo));
    }

    @Test
    public void test01() throws Exception {
        // 不能分页?
        Page<Users> usersPage = usersDao.selectPage(Page.of(20, 10), Wrappers.emptyWrapper());
        System.out.println(objectMapper.writeValueAsString(usersPage));
    }

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

}