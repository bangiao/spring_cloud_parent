package com.zhazha.springcloud.repository;

import com.zhazha.springcloud.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Integer> {
}