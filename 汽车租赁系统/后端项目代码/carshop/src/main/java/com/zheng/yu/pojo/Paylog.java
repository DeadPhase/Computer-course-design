package com.zheng.yu.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Paylog {
    private Integer pid;
    private String username;
    private  Double money;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String pay_time;    //订单创建时间
}
