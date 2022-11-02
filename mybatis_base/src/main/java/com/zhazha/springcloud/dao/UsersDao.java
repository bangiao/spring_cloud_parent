package com.zhazha.springcloud.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhazha.springcloud.entity.Users;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UsersDao extends BaseMapper<Users> {

}

