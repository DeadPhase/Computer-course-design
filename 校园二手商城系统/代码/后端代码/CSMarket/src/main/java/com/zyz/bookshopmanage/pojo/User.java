package com.zyz.bookshopmanage.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
/**
 * @author 静小文
 * @version 1.0
 * @data 2022/9/9 10:36
 */
@Data
@TableName("tbl_user")

public class User {
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

    @JsonFormat(pattern = "yyyy-mm-dd")
    @TableField("Id_Card")
    private String idCard;

    private String email;

    private String phone;
    private String address;

    private Double money;

    @TableField("register_time")
    private String registerTime;

    private Integer level;

    private String introduction;
}
