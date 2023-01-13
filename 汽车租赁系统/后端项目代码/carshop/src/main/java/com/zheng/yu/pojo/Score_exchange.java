package com.zheng.yu.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//积分兑换记录
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Score_exchange {
    private Integer id;
    private String username; //用户账号
    private Integer exchanged_credit; //兑换的积分
    private String product_id ;   //商品id

}
