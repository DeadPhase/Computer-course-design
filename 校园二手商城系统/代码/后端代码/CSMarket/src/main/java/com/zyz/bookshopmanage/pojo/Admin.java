package com.zyz.bookshopmanage.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author Lenovo
 * @version 1.0
 * @data 2022/10/10 9:59
 */
@Data

@TableName("admin_info")
public class Admin {
    /**
     *  @TableId(type = IdType.AUTO)
     */
    @TableId(value = "uid",type = IdType.AUTO)
    private Long uid;
    @TableField("nick_name")
    private String nickName;

    @TableField("user_name")
    private String userName;

    private String password;

    private String gender;

    @TableField("birth_Date")
    private String birthDate;

    @TableField("Id_Card")
    private String idCard;

    private String email;

    private String phone;

    private Double money;



    @TableField("register_time")
    private String registerTime;
}
