package com.zyz.bookshopmanage.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * @author Lenovo
 * @version 1.0
 * @data 2022/10/11 15:42
 */
@Setter
@Getter
@TableName("goods_info")
public class GoodsInfo implements Serializable {
    @TableId(type = IdType.AUTO)
    Long id;
    private String name;
    private String description;
    private String type;
    private Double price;
    private int count;
    private Double discount;
    private int sales;
    private String isSale;
    @TableField("totalPrice")
    private Double totalPrice;
    /**
     * 存储的文件名
     */
    private String fileName;
}
