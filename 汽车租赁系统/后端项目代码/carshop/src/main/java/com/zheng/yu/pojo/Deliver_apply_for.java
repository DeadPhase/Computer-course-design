package com.zheng.yu.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//发货申请单
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Deliver_apply_for {
    private Integer id;
    private Integer type; //兑换、购买
    private Integer credit_exchange_id;//积分兑换记录id
    private String product_id ;//商品id
    private String express_no; //物流单号
}
