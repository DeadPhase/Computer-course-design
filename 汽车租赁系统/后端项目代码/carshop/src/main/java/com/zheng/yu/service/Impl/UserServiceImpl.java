package com.zheng.yu.service.Impl;

import com.zheng.yu.mapper.UserMapper;
import com.zheng.yu.pojo.User;
import com.zheng.yu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    //用户登录
    public User login(Map map) {
        return userMapper.login(map);
    }

    //用户注册
    public int register(User user) {
        return userMapper.register(user);
    }

//    用户注册前先查看是否已经注册过该用户
    public User findUserRegister(String count) {
        return userMapper.findUserRegister(count);
    }

    //token的使用
    public User getUserByUsername(String name, String password) {
        return userMapper.getUserByUsername(name,password);
    }

    //根据count查询用户是否登录（账号）
    public User findUserByCount(String count) {
        return userMapper.findUserByCount(count);
    }


//    查询某一个用户
    public User findOneUser(String name) {
        return userMapper.findOneUser(name);
    }

    //查询所有用户
    public List<User> userList() {
        return userMapper.userList();
    }



    //删除某一个用户
    public int deleteOneUser(Integer id) { return userMapper.deleteOneUser(id); }

    //修改用户的信息
    public int updateOneUser(User user) { return userMapper.updateOneUser(user); }

    //修改密码
    public int updatePassword(Map<String, Object> map) {
        return userMapper.updatePassword(map);
    }

    //分页查询用户信息
    public List<User> queryUserList(Map<String, Integer> map) {
        return userMapper.queryUserList(map);
    }

    //查询用户总数
    public Long findUserTotals() {
        return userMapper.findUserTotals();
    }


//    动态查询用户信息
    public List<User> findSomeUser(User user) {
        return userMapper.findSomeUser(user);
    }


}
