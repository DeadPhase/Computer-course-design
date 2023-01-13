package com.zheng.yu.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//积分表
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Scoretable {
    private Integer id;
    private String username;
    private Integer score;

}
