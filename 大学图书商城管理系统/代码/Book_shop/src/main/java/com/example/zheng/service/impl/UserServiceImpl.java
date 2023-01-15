package com.example.zheng.service.impl;

import com.example.zheng.mapper.UserMapper;
import com.example.zheng.pojo.User;
import com.example.zheng.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;
    @Override
    public User userlogin(String name, String password) {
        return userMapper.userlogin(name,password);
    }
}
