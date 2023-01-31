package com.zyz.bookshopmanage.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zyz.bookshopmanage.pojo.Admin;
import com.zyz.bookshopmanage.pojo.User;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author Lenovo
 * @version 1.0
 * @data 2022/10/10 13:35
 */
@Repository
public interface AdminMapper extends BaseMapper<Admin> {

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
