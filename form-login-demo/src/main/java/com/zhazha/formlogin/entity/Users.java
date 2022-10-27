package com.zhazha.formlogin.entity;

import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * (Users)实体类
 *
 * @author makejava
 * @since 2022-10-27 23:08:09
 */
@Data
@Entity
public class Users implements Serializable {
    private static final long serialVersionUID = -12974772985897054L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String username;
    
    private String password;
    
    private Boolean enabled;
}

