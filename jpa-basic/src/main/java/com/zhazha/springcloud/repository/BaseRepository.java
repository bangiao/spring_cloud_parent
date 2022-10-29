package com.zhazha.springcloud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;

@Repository
@NoRepositoryBean
public interface BaseRepository <T, ID> extends JpaRepository<T, ID>, JpaSpecificationExecutor <T>, QuerydslPredicateExecutor<T> {

}
