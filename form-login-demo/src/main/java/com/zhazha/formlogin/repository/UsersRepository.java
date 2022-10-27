package com.zhazha.formlogin.repository;

import com.zhazha.formlogin.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<Users, String>, JpaSpecificationExecutor<Users> {
	Users findByEnabledIsTrueAndUsername(String username);
	
	<T> Optional<T> findUsersDTOByUsername(String username, Class<T> type);
}
