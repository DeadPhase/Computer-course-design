package com.zyz.bookshopmanage.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zyz.bookshopmanage.pojo.User;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
/**
 * @author 静小文
 * @version 1.0
 * @data 2022/9/9 10:36
 */
@Repository
public interface UserMapper extends BaseMapper<User> {

    /**
     * 分页查询用户信息
     * @param map
     * @return
     */
    List<User> findAllUser(Map<String, Integer> map);

    /**
     * 查询总数
     * @return
     */
    Long findUserTotals();

    /**
     * 根据姓名查询用户
     * @param username
     * @return
     */
    User findByName(String username);

}
