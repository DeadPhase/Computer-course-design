package com.zheng.yu.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Car {
    private Integer car_id;  //主键
    private String name;     //汽车名
    private int car_num;//汽车库存数量
    private String license_plate_car;
    private String color;    //颜色
    @JsonFormat(pattern = "yyyy-MM-dd")
    private String date;     //上架时间
    private Double guarantee_money; //押金
    private String imageUrl;     //图像存储地址
    private String type;      //种类
    private Integer status;   //是否上架、下架。1：代表上架。0:代表下架
    private String remark;

}
