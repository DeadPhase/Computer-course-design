package com.zyz.bookshopmanage.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * @author Lenovo
 * @version 1.0
 * @data 2022/11/11 17:03
 */
@Data
public class OrderGoodsRef {
    @TableId(type = IdType.AUTO)
    private Long id;

    @TableField("orderId")
    private String orderId;

    @TableField("goodsId")
    private Long goodsId;

    private Integer count;
}
