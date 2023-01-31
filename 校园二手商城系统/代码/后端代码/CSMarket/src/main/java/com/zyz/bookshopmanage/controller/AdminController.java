package com.zyz.bookshopmanage.controller;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.zyz.bookshopmanage.Utils.CurrentTime;
import com.zyz.bookshopmanage.Utils.DateConversion;
import com.zyz.bookshopmanage.Utils.MyMD5Util;
import com.zyz.bookshopmanage.config.response.Result;
import com.zyz.bookshopmanage.mapper.AdminMapper;
import com.zyz.bookshopmanage.pojo.Admin;
import com.zyz.bookshopmanage.pojo.User;
import com.zyz.bookshopmanage.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Lenovo
 * @version 1.0
 * @data 2022/10/10 9:59
 */

@RestController
public class AdminController {

    @Autowired
    AdminService adminService;

    @Autowired
    AdminMapper adminMapper;


    /**
     * 注册
     */
    @RequestMapping(value = "/admin/register", method = RequestMethod.POST)
    public Result register(@RequestBody Admin admin) {

        String time = CurrentTime.getCurrentTime();
        admin.setRegisterTime(time);
        String encryptedPwd = null;
        int result = 0;
        try {
            //加密
            encryptedPwd = MyMD5Util.getEncryptedPwd(admin.getPassword());
            admin.setPassword(encryptedPwd);
            //根据身份证和姓名判断是否已注册
            Map<String, Object> map = new HashMap<>();
            map.put("user_name", admin.getUserName());
            map.put("Id_Card", admin.getIdCard());
            List<Admin> baseUser = adminMapper.selectByMap(map);
            if (admin == null) {
                return Result.error().data("errMessage", "请输入用户的信息");
            }
            if (baseUser.size() > 0) {
                return Result.error().data("errMessage", "该用户已经注册！");
            }

            admin.setBirthDate(DateConversion.timeConversion(admin.getBirthDate()));

            result = adminMapper.insert(admin);


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
     * 查询所有的用户信息
     *
     * @return
     */
    @RequestMapping(value = "/admin/findAllUser", method = RequestMethod.GET)
    public Result findAllUser(@RequestParam Map<String ,Object> maps) {
        //准备数据 通过这两个参数，可以算出start   计算方法 start=size(page-1)
        List<Admin> adminList = null;
        Long totals = 0L;
        Integer currentPage = Integer.parseInt(maps.get("page").toString());
        Integer pageSize =  Integer.parseInt(maps.get("size").toString());
        Map<String, Integer> map = new HashMap<String, Integer>();
        map.put("startIndex", (currentPage - 1) * pageSize);
        map.put("pageSize", pageSize);
        try {
            adminList = adminMapper.findAdminInfo(map);
            totals = adminMapper.findAdminTotals();

        }catch (Exception e){
            e.printStackTrace();
        }

        if(adminList.size() >0){
            HashMap<String, Object> result = new HashMap<>();
            result.put("userList", adminList);
            result.put("totals", totals);
            return Result.ok().data("result", result);
        }else{
            return Result.error().data("errMessage", "用户信息为空");
        }


    }


    /**
     * 删除管理员信息
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/admin/deleteUser", method = RequestMethod.DELETE)
    public Result deleteAdminInfo(@RequestParam  Map<String,Object> maps) {
        String idCard = (String) maps.get("IdCard");
        HashMap<String, Object> map = new HashMap<>();
        map.put("id_Card", idCard);
        int rs = adminMapper.deleteByMap(map);

        if (rs > 0) {
            return Result.ok();
        } else {
            return Result.error().data("errMessage", "删除管理员失败");
        }

    }

    /**
     * 修改管理员信息
     *
     * @param admin
     * @return
     */
    @RequestMapping(value = "/admin/updateUserInfo", method = RequestMethod.PUT)
    public Result upDateAdminInfo(@RequestBody Admin admin) {

        if (admin == null) {
            return Result.error().data("errMessage", "信息为空");
        }

        UpdateWrapper<Admin> updateUserWrapper = new UpdateWrapper<>();
        updateUserWrapper.like("id_Card", admin.getIdCard());
        int rs = adminMapper.update(admin, updateUserWrapper);
        if (rs > 0) {
            return Result.ok();
        } else {
            return Result.error().data("errMessage", "信息修改失败");
        }


    }
}
