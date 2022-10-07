package com.zhazha.webflux.dao;

import com.zhazha.webflux.domain.Users;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends ReactiveCrudRepository<Users, Long> {
}
