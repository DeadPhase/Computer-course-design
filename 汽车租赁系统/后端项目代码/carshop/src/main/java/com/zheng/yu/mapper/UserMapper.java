package com.zheng.yu.mapper;

import com.zheng.yu.pojo.User;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


@Mapper    //这个注解表示这个是mybatis的mapeper
@Repository
public interface UserMapper {

    User login(Map map);//用户登录

    int register(User user);//用户注册

    User getUserByUsername(String name,String password);//token的使用

    User findUserRegister(String count);//用户注册前先查看是否已经注册过该用户

    User findUserByCount(String count);//根据count查询用户是否登录（账号）

    List<User> userList();//查询所有用户

    User findOneUser(String name);//查询某一个用户

    int deleteOneUser(Integer id);//删除某一个用户

    int updateOneUser(User user);//更新用户的信息

    int updatePassword(Map<String, Object> map);//修改密码


    List<User> queryUserList(Map<String, Integer> map);//分页查询用户信息

    Long findUserTotals();//查询总数

    //动态查询用户信息
    List<User> findSomeUser(User user);



}
