package com.zheng.yu.pojo;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data                   //get,set
@NoArgsConstructor     //无参构造函数
@AllArgsConstructor   //有参构造函数

public class User {
    private int user_id;       //用户主键、自增
    private String count;  //用户账号
    private String name;      //姓名
    private String password;  //密码
    private String phone;     //电话号码
    @JsonFormat(pattern = "yyyy-MM-dd")
    private String time;      //注册时间
    private Integer vip;
//    private List<Role> roleList;//角色


}
