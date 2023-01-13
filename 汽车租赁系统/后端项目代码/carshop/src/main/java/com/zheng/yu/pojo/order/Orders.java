package com.zheng.yu.pojo.order;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Orders {
    private String order_id;
    private Double prepayment;   //押金

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String create_time;    //订单创建时间
    private String usetime;//租借日期
    private String user_id;       //用户id
    private String username;          //用户姓名
    private String address;               //地址
    private String phone;              //电话号码
    private Integer status;
    private Integer user_cancle;    //用户是否取消订单（0：未取消。1：取消）
    private Integer user_log_cancle; //用户历史订单日志：（0：未取消，1：取消）
    /**
     * 1:待取车。2、待还车，3、已经还车，4、完成。3、已付尾款。
     * 4、未取车。5、已取车。6、未还车。7、已还车。8、交易完成
     */
}
