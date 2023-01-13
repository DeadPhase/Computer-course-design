package com.zheng.yu.service;

import com.zheng.yu.pojo.Discount_coupon;
import com.zheng.yu.pojo.PersionDiscountTable;

import java.util.List;
import java.util.Map;

public interface DiscountCouponService {

    //增加优惠券
    int addDiscountCoupon(Discount_coupon discount_coupon);

    //删除优惠券
    int deleteDiscountCoupon(Integer id);

    //修改优惠券
    int updateDiscountCoupon(Discount_coupon discount_coupon);

    //查看优惠券

    List<Discount_coupon> lookDiscountCouponPage(Map map);

    //用户查询商家提供的优惠券
    List<Discount_coupon> findAllDiscount();

    //查看某一个优惠券信息
    Discount_coupon lookOneDiscountCoupon(Integer id);

    //查看多少条数据
    Long discountCouponTotals();

    //===========================用户优惠券的管理==============
//增加个人优惠券
    int addPersionDiscount(PersionDiscountTable persionDiscountTable);

    //删除个人优惠券
    int deletePersionDiscountById(Integer id);

    //查看个人优惠券
    List<PersionDiscountTable> findPersionDiscout(String username);

}
