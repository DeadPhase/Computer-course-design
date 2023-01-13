package com.zheng.yu.utils;



//用来进行积分兑换金额
public class TransformNum {

    public static Double useDiscountNeedPay(Double total_price,Double type){
        if(type > 0){
            //使用了优惠券
            Double needPayMoney = total_price*type;
            return needPayMoney;
        }else{
            //没有使用优惠券
            return total_price;
        }

    }

    public static int getMoney(int jifen){
       if(jifen >= 100){
           //每一百积分兑换五块钱
           int rs = jifen/100;
           int diya = (int) (rs*(5.0));//抵押的金额
           return diya;
       }else{
           return 0;
       }

    }
}
