package com.zhazha.webflux.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.io.Serializable;

@Data
@Table("users")
public class Users implements Serializable {
    @Id
    private Long id;
    private Integer age;
    private String name;
}
