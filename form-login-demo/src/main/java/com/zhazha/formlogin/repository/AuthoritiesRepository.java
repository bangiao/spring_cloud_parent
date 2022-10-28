package com.zhazha.formlogin.repository;

import com.zhazha.formlogin.po.Authorities;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface AuthoritiesRepository extends JpaRepository<Authorities, String>, JpaSpecificationExecutor<Authorities> {

}
