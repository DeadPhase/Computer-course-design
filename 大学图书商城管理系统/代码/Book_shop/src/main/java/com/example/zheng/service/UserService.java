package com.example.zheng.service;

import com.example.zheng.pojo.User;

public interface UserService {

    //登录
    User userlogin(String name, String password);
}
