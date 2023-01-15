package com.example.zheng.mapper;

import com.example.zheng.pojo.Authority;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Mapper    //这个注解表示这个是mybatis的mapeper
@Repository
public interface AuthorityMapper {

    //查询当前角色拥有的权限
    Set<Authority> queryAuthority(String roleId);
}
