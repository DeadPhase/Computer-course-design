package com.zyz.bookshopmanage.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zyz.bookshopmanage.mapper.AdminMapper;
import com.zyz.bookshopmanage.pojo.Admin;
import com.zyz.bookshopmanage.pojo.User;
import com.zyz.bookshopmanage.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author Lenovo
 * @version 1.0
 * @data 2022/10/10 13:36
 */
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements AdminService {
    @Autowired
    AdminMapper adminMapper;


    @Override
    public List<Admin> findAdminInfo(Map<String, Integer> map) {
        return adminMapper.findAdminInfo(map);
    }

    @Override
    public Long findAdminTotals() {
        return adminMapper.findAdminTotals();
    }
}
