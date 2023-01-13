package com.zheng.yu.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Wallet {
    private Integer wid;
    private String username;
    private Double money;
    private Double premoney;

}
