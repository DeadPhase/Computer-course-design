package com.zheng.yu.service;

import com.zheng.yu.pojo.User;

import java.util.List;
import java.util.Map;

public interface UserService {

    User login(Map map);//用户登录

    int register(User user);//用户注册

    User findUserRegister(String count);//用户注册前先查看是否已经注册过该用户

    User getUserByUsername(String name,String password);//token的使用

    User findUserByCount(String count);//根据count查询用户是否登录（账号）

    User findOneUser(String name);//查询某一个用户

    List<User> userList();//查询所有用户


    int deleteOneUser(Integer id);//删除某一个用户

    int updateOneUser(User user);//更新用户的信息

    int updatePassword(Map<String, Object> map);//修改密码

    List<User> queryUserList(Map<String, Integer> map);//分页查询用户信息

    Long findUserTotals();//查询总数

    //动态查询用户信息
    List<User> findSomeUser(User user);


}
