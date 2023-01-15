package com.example.zheng.mapper;


import com.example.zheng.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper    //这个注解表示这个是mybatis的mapeper
@Repository
public interface UserMapper {

    /**
     *
     * @param name
     * @param password
     * @return  登录
     */
    User userlogin(String name, String password);
}
