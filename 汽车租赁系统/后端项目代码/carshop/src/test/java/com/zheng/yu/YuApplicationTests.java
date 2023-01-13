package com.zheng.yu;


import com.zheng.yu.pojo.User;
import com.zheng.yu.service.CarService;
import com.zheng.yu.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.mybatis.logging.Logger;
import org.mybatis.logging.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@SpringBootTest
@Slf4j
class YuApplicationTests {

    @Autowired
    UserService userService;





    @Test
    void contextLoads() {
    }

    //测试使用分页查询用户信息
    @Test
    void testUserLimitList(){

    }

    //测试修改密码
    @Test
    void testUpdatePassword(){
        Map<String,Object> map = new HashMap();
        map.put("user_id",3);
        map.put("password","111");
        int rs = userService.updatePassword(map);
    }

    //测试查询用户信息
    @Test
    void testUserList(){
        List<User> userList = userService.userList();
        int a = userList.size();
    }

    /**
     *
     @PostMapping("/login")
     public Map<String,Object> save(@RequestBody Customer customer){

     HashMap map = new HashMap();
     HashMap map1 = new HashMap();
     map.put("usercount",customer.getUsercount());
     map.put("password",customer.getPassword());
     Customer customer1=  customerService.login(map);
     map1.put("customer",customer1);
     map1.put("success","success");
     return map1;


     }
     */
    //测试登录
    @Test
    void testLogin(){
        Map usermap = new HashMap();
        log.info("测试");

        usermap.put("name","小明");
        usermap.put("password","123");
      User user =  userService.login(usermap);
      int a=4;
    }
    /**
     *   private Logger logger = LoggerFactory.getLogger(LogTest.class);
     */

    //测试注册
    @Test
    void testRegister(){
        User user = new User();
        user.setName("李四");
        user.setPassword("123456");
        user.setPhone("14325674531");
        int a = userService.register(user);
        int b = 3;
    }


    //测试删除一个用户的信息，根据id
    @Test
    void testDeleteOneUser(){
        int rs = userService.deleteOneUser(1);
        int b=4;
    }

    //测试修改用户的信息
//    @Test
//    void testUpdateOneUser(){
//        User user = new User(1, "小红", "000", "18348593741","2323");
//        int rs = userService.updateOneUser(user);
//        int a = 3;
//    }

//    //测试用户具有的角色
//    @Test
//    void testUserRole(){
//        Set<Role> roleList = authorityService.queryRole(1);
//
//        int a= 1;
//    }
//
//    //测试角色拥有的权限
//    @Test
//    void testRolePermission(){
//        Set<Permission> permissionSet = authorityService.queryPerms(1000);
//
//        int b=2;
//    }


}
