package com.zhazha.formlogin.po;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * (Users)实体类
 *
 * @author makejava
 * @since 2022-10-27 23:08:09
 */
@Data
@Entity
@Table(name = "users")
public class Users implements Serializable {
    private static final long serialVersionUID = -12974772985897054L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String username;
    
    private String password;
    
    private Boolean enabled;
}

