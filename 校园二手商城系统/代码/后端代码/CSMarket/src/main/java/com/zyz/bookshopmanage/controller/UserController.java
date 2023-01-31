package com.zyz.bookshopmanage.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.zyz.bookshopmanage.Utils.CurrentTime;
import com.zyz.bookshopmanage.Utils.MyMD5Util;
import com.zyz.bookshopmanage.Utils.MyTokenUtil;
import com.zyz.bookshopmanage.config.response.Result;
import com.zyz.bookshopmanage.mapper.UserMapper;
import com.zyz.bookshopmanage.pojo.User;
import com.zyz.bookshopmanage.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 静小文
 * @version 1.0
 * @data 2022/9/9 10:36
 */
@RestController
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    UserMapper userMapper;

    /**
     * 注册
     */
    @RequestMapping(value = "/user/register", method = RequestMethod.POST)
    public Result register(@RequestBody User user) {

        String time = CurrentTime.getCurrentTime();
        user.setRegisterTime(time);
        String encryptedPwd = null;
        int result = 0;
        try {
            //加密
            encryptedPwd = MyMD5Util.getEncryptedPwd(user.getPassword());
            user.setPassword(encryptedPwd);
            //根据身份证和姓名判断是否已注册
            Map<String, Object> map = new HashMap<>();
            map.put("user_name", user.getUserName());
            map.put("Id_Card", user.getIdCard());
            List<User> baseUser = userMapper.selectByMap(map);
            if (user == null) {
                return Result.error().data("errMessage", "请输入用户的信息");
            }
            if (baseUser.size() > 0) {
                return Result.error().data("errMessage", "该用户已经注册！");
            }
            result = userMapper.insert(user);


        } catch (Exception e) {
            e.printStackTrace();
        }


        if (result > 0) {
            return Result.ok();
        } else {
            return Result.error().data("errMessage", "注册失败");
        }

    }

    /**
     * 删除用户信息
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/user/deleteUser", method = RequestMethod.DELETE)
    public Result deleteUserInfo(@RequestParam Map<String, Object> maps) {
        String idCard = (String) maps.get("IdCard");
        HashMap<String, Object> map = new HashMap<>();
        map.put("id_Card", idCard);
        int rs = userMapper.deleteByMap(map);

        if (rs > 0) {
            return Result.ok();
        } else {
            return Result.error().data("errMessage", "删除失败");
        }

    }

    /**
     * 修改用户信息
     *
     * @param user
     * @return //        updateUserWrapper.like("id_Card", user.getIdCard());
     */
    @RequestMapping(value = "/user/updateUserInfo", method = RequestMethod.PUT)
    public Result updateUserInfo(@RequestBody User user) {

        if (user == null) {
            return Result.error().data("errMessage", "用户信息为空");
        }

        UpdateWrapper<User> updateUserWrapper = new UpdateWrapper<>();
        updateUserWrapper.like("uid", user.getUid());
        int rs = userMapper.update(user, updateUserWrapper);
        if (rs > 0) {
            //返回修改后的用户信息
            return Result.ok().data("userInfo", user);
        } else {
            return Result.error().data("errMessage", "用户信息修改失败");
        }


    }


    /**
     * 充值
     *
     * @param map
     * @return
     */
    @RequestMapping(value = "/user/rechargeMoney", method = RequestMethod.PUT)
    public Result rechargeMoney(@RequestBody Map<String, Object> map) {
        int result = 0;
        if (map == null) {
            return Result.error().data("errMessage", "充值失败");
        }
        int uid = Integer.parseInt(map.get("userId").toString());
        Double rechargeMoney = Double.parseDouble(map.get("money").toString());
        Double preMoney = Double.parseDouble(map.get("preMoney").toString());
        Double afterMoney = rechargeMoney + preMoney;
        UpdateWrapper<User> userWrapper = new UpdateWrapper<>();
        userWrapper.like("uid", uid);
        userWrapper.set("money", afterMoney);
        try {
            result = userMapper.update(null, userWrapper);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (result > 0) {
            return Result.ok();
        } else {
            return Result.error().data("errMessage", "充值失败");
        }


    }

    /**
     * 用户登录
     *
     * @param maps
     * @return
     */
    @RequestMapping(value = "/user/userLogin", method = RequestMethod.GET)
    public Result UserLogin(@RequestParam Map<String, Object> maps, HttpServletRequest request) {
        boolean flag = false;
        String username = maps.get("username").toString();
        String password = maps.get("password").toString();
        if (username == null || password == null) {
            return Result.error().data("errMessage", "输入的用户名或者密码为空!");
        }
        User user = userMapper.findByName(username);
        if (user != null) {
            try {
                flag = MyMD5Util.validPassword(password, user.getPassword());
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            return Result.error().data("errMessage", "不存在该用户");
        }

        if (flag) {
            request.getSession().setAttribute("user", user);
            return Result.ok().data("user", user);
        } else {
            return Result.error().data("errMessage", "密码错误");
        }


    }

    /**
     * 查看用户是否登录
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/user/isLogin", method = RequestMethod.GET)
    public Result isLogin(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        System.out.println(user);
        if (user == null) {
            return Result.error().data("errMessage", "未登录");
        }

        User newUserStatus = userMapper.findByName(user.getUserName());
        if (newUserStatus != null) {
            return Result.ok().data("user", newUserStatus);
        }

        return Result.error();


    }

    /**
     * 登录
     *
     * @return
     */
    @RequestMapping(value = "/user/login", method = RequestMethod.POST)
    public Result login(@RequestBody User users, HttpServletRequest request) {
        User user = userMapper.findByName(users.getUserName());
        boolean flag = false;
        if (user != null) {
            try {
                flag = MyMD5Util.validPassword(users.getPassword(), user.getPassword());
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            return Result.error().data("errMessage", "不存在该用户");
        }

        if (flag) {
            String token = MyTokenUtil.sign(user);
            Map<String, Object> result = new HashMap<>();
            result.put("token", token);
            result.put("user", user);
            request.getSession().setAttribute("user", user);
            return Result.ok().data("result", result);
        } else {
            return Result.error().data("errMessage", "密码错误");
        }


    }

    /**
     * 查询所有的用户信息
     *
     * @return
     */
    @RequestMapping(value = "/user/findAllUser", method = RequestMethod.GET)
    public Result findAllUser(@RequestParam Map<String, Object> maps) {
        //准备数据 通过这两个参数，可以算出start   计算方法 start=size(page-1)
        List<User> userList = null;
        Long totals = 0L;
        Integer currentPage = Integer.parseInt(maps.get("page").toString());
        Integer pageSize = Integer.parseInt(maps.get("size").toString());
        Map<String, Integer> map = new HashMap<String, Integer>();
        map.put("startIndex", (currentPage - 1) * pageSize);
        map.put("pageSize", pageSize);
        try {
            userList = userService.findAllUser(map);
            totals = userService.findUserTotals();

        } catch (Exception e) {
            e.printStackTrace();
        }

        if (userList.size() > 0) {
            HashMap<String, Object> result = new HashMap<>();
            result.put("userList", userList);
            result.put("totals", totals);
            return Result.ok().data("result", result);
        } else {
            return Result.error().data("errMessage", "用户信息为空");
        }


    }

    @RequestMapping(value = "/user/findUserById", method = RequestMethod.GET)
    public Result findUserById(@RequestParam HashMap<String, Object> map) {
        Long uid = Long.parseLong(map.get("uid").toString());
        User user = userService.findUserById(uid);
        if (user != null) {
            return Result.ok().data("user", user);
        } else {
            return Result.error().data("errMessage", "信息查询失败！！！");
        }

    }

    /**
     * 查询用户信息
     *
     * @param idCard
     * @return
     */
    @RequestMapping(value = "/user/search/{idCard}", method = RequestMethod.GET)
    public Result searchUser(@PathVariable("idCard") String idCard) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("id_Card", idCard);
        List<User> userList = userMapper.selectList(queryWrapper);
        if (userList.size() > 0) {
            HashMap<String, Object> result = new HashMap<>();
            result.put("userList", userList);
            return Result.ok().data("result", result);
        } else {
            return Result.error().data("errMessage", "查询的用户不存在");
        }


    }

    /**
     * 修改密码
     *
     * @param map
     * @return
     */
    @RequestMapping(value = "/user/changePwd", method = RequestMethod.PUT)
    public Result changePassword(@RequestBody HashMap<Object, String> map) {
        Long userId = Long.parseLong(map.get("uid").toString());
        String username = map.get("username").toString();
        String pwd = map.get("password");
        String oldPwd = map.get("oldPwd");
        boolean flag = false;
        User user = userMapper.findByName(username);
        if (user != null) {
            try {
                flag = MyMD5Util.validPassword(oldPwd, user.getPassword());
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            return Result.error().data("errMessage", "不存在该用户");
        }
        if (!flag) {
            return Result.error().data("errMessage", "原始密码错误");
        }


        Integer rs = userService.changePassword(userId, pwd, oldPwd);
        if (rs > 0) {
            return Result.ok();
        } else {
            return Result.error().data("errMessage", "修改密码失败");
        }
    }

    /**
     * 修改手机号
     *
     * @param maps
     * @return
     */
    @RequestMapping(value = "/user/updatePhone", method = RequestMethod.PUT)
    public Result updatePhone(@RequestBody Map<String, Object> maps) {
        int rs = 0;
        int uid = Integer.parseInt(maps.get("userId").toString());
        String phone = maps.get("phone").toString();
        try {
            rs = userService.updatePhone(uid, phone);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (rs > 0) {
            return Result.ok().data("phone", phone);
        } else {
            return Result.error().data("errMessage", "修改手机号失败！");
        }

    }


}
