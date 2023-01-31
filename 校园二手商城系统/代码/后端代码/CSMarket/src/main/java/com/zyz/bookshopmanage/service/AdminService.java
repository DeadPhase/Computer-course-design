package com.zyz.bookshopmanage.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.zyz.bookshopmanage.pojo.Admin;


import java.util.List;
import java.util.Map;

/**
 * @author Lenovo
 * @version 1.0
 * @data 2022/10/10 13:36
 */
public interface AdminService extends IService<Admin> {

    /**
     * 分页查询管理员信息
     * @param map
     * @return
     */
    List<Admin> findAdminInfo(Map<String, Integer> map);

    /**
     * 查询总数
     * @return
     */
    Long findAdminTotals();
}
