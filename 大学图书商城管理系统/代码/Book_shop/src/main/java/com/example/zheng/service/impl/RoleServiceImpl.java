package com.example.zheng.service.impl;

import com.example.zheng.mapper.RoleMapper;
import com.example.zheng.pojo.Role;
import com.example.zheng.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleMapper roleMapper;
    //查看当前用户具有哪几个权限
    @Override
    public Set<Role> queryRole(String usercount) {
        return roleMapper.queryRole(usercount);
    }
}
