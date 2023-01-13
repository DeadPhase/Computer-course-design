package com.zheng.yu.service.Impl;

import com.zheng.yu.mapper.DiscountCouponMapper;
import com.zheng.yu.pojo.Discount_coupon;
import com.zheng.yu.pojo.PersionDiscountTable;
import com.zheng.yu.service.DiscountCouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class DiscountCouponServiceImpl  implements DiscountCouponService {

    @Autowired
    DiscountCouponMapper discountCouponMapper;

    public int addDiscountCoupon(Discount_coupon discount_coupon) {
        return discountCouponMapper.addDiscountCoupon(discount_coupon);
    }

    public int deleteDiscountCoupon(Integer id) {
        return discountCouponMapper.deleteDiscountCoupon(id);
    }

    public int updateDiscountCoupon(Discount_coupon discount_coupon) {
        return discountCouponMapper.updateDiscountCoupon(discount_coupon);
    }

    public List<Discount_coupon> lookDiscountCouponPage(Map map) {
        return discountCouponMapper.lookDiscountCouponPage(map);
    }

//    用户查询商家提供的优惠券
    public List<Discount_coupon> findAllDiscount() {
        return discountCouponMapper.findAllDiscount();
    }

    public Discount_coupon lookOneDiscountCoupon(Integer id) {
        return discountCouponMapper.lookOneDiscountCoupon(id);
    }

    public Long discountCouponTotals() {
        return discountCouponMapper.discountCouponTotals();
    }

    //===========================用户优惠券的管理==============
    public int addPersionDiscount(PersionDiscountTable persionDiscountTable) {
        return discountCouponMapper.addPersionDiscount(persionDiscountTable);
    }

    public int deletePersionDiscountById(Integer id) {
        return discountCouponMapper.deletePersionDiscountById(id);
    }

    public List<PersionDiscountTable> findPersionDiscout(String username) {
        return discountCouponMapper.findPersionDiscout(username);
    }
}
