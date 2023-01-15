package com.example.zheng.mapper;

import com.example.zheng.pojo.Role;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.Set;


@Mapper    //这个注解表示这个是mybatis的mapeper
@Repository
public interface RoleMapper {


    //查询当前用户具有的角色
    Set<Role> queryRole(String usercount);
}
