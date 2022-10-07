package com.zhazha.webflux.controller;

import com.zhazha.webflux.dao.UsersRepository;
import com.zhazha.webflux.domain.Users;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;

@RestController
@Slf4j
public class UsersController {

    @Resource
    private UsersRepository usersRepository;

    @GetMapping("get/{id}")
    public Mono<Users> get(@PathVariable("id") Long id) {
        return usersRepository.findById(id);
    }


}