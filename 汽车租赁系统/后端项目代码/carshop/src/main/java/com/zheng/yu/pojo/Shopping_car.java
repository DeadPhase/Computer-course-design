package com.zheng.yu.pojo;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Shopping_car {
    private Integer car_id;
    private String name;
    private String username;
    private String color;
    private Integer num;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String startTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String endTime;
    private Double guarantee_money;
    private String type;
    private String imageUrl;
    private String address;
    private String phone;

}
