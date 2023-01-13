package com.zheng.yu.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarType {
    private Integer id;
    private String type;//汽车类型1：SUV。2：轿车。3：跑车
    private Integer car_type_num;//该类型汽车上架数量
    private Integer car_order_num;  //该类型汽车的订单量
}
