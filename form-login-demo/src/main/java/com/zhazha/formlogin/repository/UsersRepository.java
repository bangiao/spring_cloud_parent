package com.zhazha.formlogin.repository;

import com.zhazha.formlogin.dto.UsersDTO;
import com.zhazha.formlogin.po.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<Users, String>, JpaSpecificationExecutor<Users> {
	Users findByEnabledIsTrueAndUsername(String username);
	
	<T> Optional<T> findUsersDTOByUsername(String username, Class<T> type);
	
	@Query(value = "select new com.zhazha.formlogin.dto.UsersDTO(u.username, u.password) from Users u where u.username = :username and u.enabled = true")
	UsersDTO findUsersByUsername02(@Param("username") String username);
}
