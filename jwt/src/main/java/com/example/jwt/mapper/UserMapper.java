package com.example.jwt.mapper;

import com.example.jwt.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    public User login(User user);
}
