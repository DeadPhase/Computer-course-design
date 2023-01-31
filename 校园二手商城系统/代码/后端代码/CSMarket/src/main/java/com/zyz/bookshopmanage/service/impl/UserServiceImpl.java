package com.zyz.bookshopmanage.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zyz.bookshopmanage.Utils.MyMD5Util;
import com.zyz.bookshopmanage.mapper.UserMapper;
import com.zyz.bookshopmanage.pojo.User;
import com.zyz.bookshopmanage.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author 静小文
 * @version 1.0
 * @data 2022/9/9 10:36
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Autowired
    UserMapper userMapper;


    @Override
    public List<User> findAllUser(Map<String, Integer> map) {
        return userMapper.findAllUser(map);
    }

    @Override
    public Long findUserTotals() {
        return userMapper.findUserTotals();
    }

    @Override
    public User findByName(String username) {
        return userMapper.findByName(username);
    }


    /**
     * 根据id查询用户信息
     */
    @Override
    public User findUserById(Long uid) {
        User user = null;
        try {
            user = userMapper.selectById(uid);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return user;

    }

    /**
     * 修改密码
     *
     * @param userID
     * @param password
     * @return
     */
    @Override
    public Integer changePassword(long userID, String password, String oldPwd) {
        Integer rs = 0;
        String encryptedPwd = null;
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("uid", userID);
        try {

            encryptedPwd = MyMD5Util.getEncryptedPwd(password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        UpdateWrapper<User> wraper = new UpdateWrapper<>();
        wraper.like("uid", userID);
        wraper.set("password", encryptedPwd);
        try {
            rs = userMapper.update(null, wraper);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return rs;
    }

    /**
     * 修改手机号
     *
     * @param uid
     * @param phone
     * @return
     */
    @Override
    public int updatePhone(int uid, String phone) {
        int result = 0;
        UpdateWrapper<User> wrapper = new UpdateWrapper<>();
        wrapper.like("uid", uid);
        wrapper.set("phone", phone);
        try {
            result = userMapper.update(null, wrapper);
        } catch (Exception e) {
            e.printStackTrace();
        }


        return result;
    }
}
