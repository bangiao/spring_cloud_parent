package com.zhazha.springcloud.repository;

import com.zhazha.springcloud.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}