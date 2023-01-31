package com.zyz.bookshopmanage.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zyz.bookshopmanage.pojo.User;

import java.util.List;
import java.util.Map;
/**
 * @author 静小文
 * @version 1.0
 * @data 2022/9/9 10:36
 */
public interface UserService extends IService<User> {
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
     * 查询用户
     * @param username
     * @return
     */
    User findByName(String username);

    /**
     * 根据id查询用户信息
     */
    public User findUserById(Long uid);

    /**
     * 修改密码
     * @param userID
     * @param password
     * @return
     */
    Integer changePassword(long userID,String password,String oldPwd);

    /**
     * 修改手机号
     * @param uid
     * @param phone
     * @return
     */
    int updatePhone(int uid,String phone);

}
