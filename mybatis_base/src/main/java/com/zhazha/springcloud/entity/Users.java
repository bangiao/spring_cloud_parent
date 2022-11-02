package com.zhazha.springcloud.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import org.springframework.stereotype.Repository;

@Data
@Repository
public class Users extends Model<Users> {
    private Integer id;

    private String username;

    private String password;

    private Integer enabled;
}

