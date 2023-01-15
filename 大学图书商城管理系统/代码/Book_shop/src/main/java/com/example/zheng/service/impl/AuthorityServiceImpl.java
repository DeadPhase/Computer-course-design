package com.example.zheng.service.impl;

import com.example.zheng.mapper.AuthorityMapper;
import com.example.zheng.pojo.Authority;
import com.example.zheng.service.AuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class AuthorityServiceImpl implements AuthorityService {

    @Autowired
    AuthorityMapper authorityMapper;

    //查询当前角色具有的权限
    @Override
    public Set<Authority> queryAuthority(String roleId) {
        return authorityMapper.queryAuthority(roleId);
    }
}
