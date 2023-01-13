package com.zheng.yu.pojo.order;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order_shipping {
    private String order_id;  //订单编号
    private String receiver_name; //收货人
    private String receiver_phone; //收货电话
    private String receiver_state; //省份
    private String receiver_city;  //城市
    private String receiver_district; //收货地址
}
