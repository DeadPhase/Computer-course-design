package com.zheng.yu.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
//个人优惠券的使用情况
public class PersionDiscountTable {
    private Integer id;
    private String name;//优惠券名称
    private String username;//用户名
    private String instruction;//使用说明
    private Integer num;//数量
    private Double type;//折扣类型
    private String startTime;//使用开始时间
    private String endTime;//使用结束时间

}
