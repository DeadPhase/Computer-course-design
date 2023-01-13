package com.zheng.yu.pojo.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order_item {
    private String id;
    private Integer car_id;//汽车id
    private String order_id;  //订单编号
    private Integer num ;     //数量
    private String name;      //汽车名称
    private String color;     //汽车颜色
    private String type ;     //汽车种类
    private String  imageUrl; //汽车图片
    private Double price;       //汽车单价
    private Double total_price;  //汽车总价
    private int  daynum;         //租借天数
    private int discoundId;   //是否使用优惠券，记录用户个人优惠券id
    private Double discountType;   //优惠力度



}
