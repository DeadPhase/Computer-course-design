package com.zyz.bookshopmanage.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author Lenovo
 * @version 1.0
 * @data 2022/11/8 14:06
 */
@Data
@TableName("cart_shop_info")
public class CartShopInfo {
    @TableId(type = IdType.AUTO)
    private Long id;
    private int count;
    @TableField("goodsId")
    private Long goodsId;
    @TableField("userId")
    private Long userId;
    private int level;
    @TableField("createTime")
    private String createTime;
}
