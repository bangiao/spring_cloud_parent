package com.zhazha.springcloud.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zhazha.springcloud.dao.UsersDao;
import com.zhazha.springcloud.entity.Users;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
public class IndexController {

    @Resource
    private ObjectMapper objectMapper;

    @Resource
    private UsersDao usersDao;

    @RequestMapping(value = "index")
    @ResponseBody
    public String index() throws JsonProcessingException {
        LambdaQueryWrapper<Users> wrapper = Wrappers.<Users>lambdaQuery().select(Users::getId, Users::getUsername, Users::getPassword, Users::getEnabled);
//        Page<Users> page = Page.of(20, 10);
        Page<Users> page = usersDao.selectPage(Page.of(20, 10), wrapper);
        return objectMapper.writeValueAsString(page);
    }

}