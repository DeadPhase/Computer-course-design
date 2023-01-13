package com.zheng.yu.pojo;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Discount_coupon {
    private Integer id;
    private String name;
    private Double type;
    private Integer num;
    private String instructions;
    private Integer userType;
    private Integer receive;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String startTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String endTime;

}
