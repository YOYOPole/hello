package com.example.jwt.service.impl;

import com.example.jwt.entity.User;
import com.example.jwt.mapper.UserMapper;
import com.example.jwt.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    UserMapper userMapper;

    @Override
    public User login(User user) {
        User users = userMapper.login(user);
        if (users != null) {
            return user;
        }
        throw new RuntimeException("登录失败！");
    }
}
