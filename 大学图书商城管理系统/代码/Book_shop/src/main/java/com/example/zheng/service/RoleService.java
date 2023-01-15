package com.example.zheng.service;

import com.example.zheng.pojo.Role;

import java.util.Set;

public interface RoleService {
    //查询当前用户具有的角色
    Set<Role> queryRole(String usercount);
}
