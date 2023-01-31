package com.zyz.bookshopmanage.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
//import org.springframework.data.annotation.Transient;
import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * @author Lenovo
 * @version 1.0
 * @data 2022/11/11 15:58
 */
@Data
public class OrderInfo implements Serializable {
    @TableId(type = IdType.AUTO)
    private Long id;

    @TableField("orderId")
    private String orderId;

    @TableField("userId")
    private Long userId;

    @TableField("totalPrice")
    private Double totalPrice;

    private Integer level;

    @TableField("harvestAddress")
    private String harvestAddress;

    @TableField("harvestName")
    private String harvestName;

    @TableField("harvestPhone")
    private String harvestPhone;

    @TableField("fahuoAddress")
    private String fahuoAddress;

    @TableField("fahuoName")
    private String fahuoName;

    @TableField("fahuoPhone")
    private String fahuoPhone;

    @TableField("createTime")
    private String createTime;

    @TableField("fileName")
    private  String fileName;
    private Integer status;

//    @TableField(exist = false)
    private transient List<GoodsInfo> goodsList;




}
