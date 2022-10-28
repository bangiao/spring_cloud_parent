package com.zhazha.springcloud.repository;

import com.zhazha.springcloud.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Integer> {
}