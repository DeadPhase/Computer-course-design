package com.example.zheng.service;

import com.example.zheng.pojo.Authority;

import java.util.Set;

public interface AuthorityService {

    //查询当前角色拥有的权限
    Set<Authority> queryAuthority(String roleId);
}
