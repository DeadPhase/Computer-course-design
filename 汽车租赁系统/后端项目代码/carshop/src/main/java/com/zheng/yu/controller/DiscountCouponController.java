package com.zheng.yu.controller;


import com.zheng.yu.config.response.Result;

import com.zheng.yu.pojo.Discount_coupon;

import com.zheng.yu.pojo.Paylog;
import com.zheng.yu.pojo.PersionDiscountTable;
import com.zheng.yu.service.DiscountCouponService;
import com.zheng.yu.utils.JWTUtil;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Api(value = "优惠券管理模块", tags = "优惠券管理接口")
@ApiModel
public class DiscountCouponController {

    @Autowired
    DiscountCouponService discountCouponService;

    @ApiOperation(value = "添加优惠券", notes = "添加优惠券")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Discount_coupon")
    })
    @RequestMapping(value = "/discount/adddiscountcoupon", method = RequestMethod.POST)
    public Result addDiscountCoupon(@RequestBody Discount_coupon discount_coupon) throws ParseException {
//        //设置转换的日期格式
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//
//        //开始时间
//        Date startDate = sdf.parse(discount_coupon.getStartTime());
//        //结束时间
//        Date endDate = sdf.parse(discount_coupon.getEndTime());
//updatediscountcoupon
//        //相差天数
//        long betweenDate = (endDate.getTime() - startDate.getTime() )/(60*60*24*1000);
//        System.out.println("相差日期"+betweenDate);


        int rs = discountCouponService.addDiscountCoupon(discount_coupon);
        return Result.ok();
    }


    @ApiOperation(value = "下架优惠券", notes = "根据id下架优惠券")
    @ApiImplicitParam(name = "id", value = "优惠券id", required = true, dataType = "Integer")
    @RequestMapping(value = "/discount/deletediscountcoupon/{id}", method = RequestMethod.DELETE)
    public Result deleteDiscountCoupon(@PathVariable("id") Integer id) {
        int rs = discountCouponService.deleteDiscountCoupon(id);
        return Result.ok();
    }

    @ApiOperation(value = "修改优惠券的信息", notes = "根据id修改优惠券的信息")
    @ApiImplicitParams(
            @ApiImplicitParam(name = "Discount_coupon")
    )
    @RequestMapping(value = "/discount/updatediscountcoupon", method = RequestMethod.PUT)
    public Result updateDiscountCoupon(@RequestBody Discount_coupon discount_coupon) {
        int rs = discountCouponService.updateDiscountCoupon(discount_coupon);
        return Result.ok();
    }



    @ApiOperation(value = "优惠券分页", notes = "根据传入的页码和页面大小查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "当前页", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "size", value = "当前页大小", required = true, dataType = "Integer")
    }
    )
    @RequestMapping(value = "/discount/lookdiscountcouponpage/{page}/{size}", method = RequestMethod.GET)
    public Result lookDiscountCouponPage(@PathVariable("page") Integer Page, @PathVariable("size") Integer size) {
        int currentPage = Page;//当前是第几页
        int pageSize = size; //页面大小
        Map<String, Integer> map = new HashMap<String, Integer>();
        map.put("startIndex", (currentPage - 1) * pageSize);
        map.put("pageSize", pageSize);

        List<Discount_coupon> discount_couponList = discountCouponService.lookDiscountCouponPage(map);
        HashMap<String, Object> result = new HashMap<>();
        Long totals = discountCouponService.discountCouponTotals();
        result.put("discounts", discount_couponList);
        result.put("totals", totals);
        return Result.ok().data("result", result);

    }

    @ApiOperation(value = "根据id查询优惠券", notes = "根据id查询某一个优惠券的信息")
    @ApiImplicitParam(name = "id", value = "优惠券id", required = true, dataType = "Integer")
    @RequestMapping(value = "/discount/findonediscount/{id}", method = RequestMethod.GET)
    public Result lookOneDiscountCoupon(@PathVariable("id") Integer id) {
        Discount_coupon discount_coupon = discountCouponService.lookOneDiscountCoupon(id);

        return Result.ok().data("one_discount_coupon", discount_coupon);

    }


     //===========================用户优惠券的管理==============

    //用户添加优惠券
    @RequestMapping(value = "/discount/addPersionDiscount", method = RequestMethod.POST)
    public Result addPersionDiscount(HttpServletRequest request, @RequestBody Discount_coupon discount_coupon) {
        String token =request.getHeader("Authorization");
        String username = JWTUtil.getUsername(token);
        System.out.println(username+"优惠券领取");
        PersionDiscountTable persionDiscountTable = new PersionDiscountTable();

        persionDiscountTable.setUsername(username);
        persionDiscountTable.setName(discount_coupon.getName());
        persionDiscountTable.setType(discount_coupon.getType());
        persionDiscountTable.setStartTime(discount_coupon.getStartTime());
        persionDiscountTable.setEndTime(discount_coupon.getEndTime());
        persionDiscountTable.setNum(discount_coupon.getReceive());
        persionDiscountTable.setInstruction(discount_coupon.getInstructions());

         int rs = discountCouponService.addPersionDiscount(persionDiscountTable);
         if(rs > 0){
             return Result.ok();
         }else{
             return Result.error();
         }


     }

     //用户删除优惠券
     @RequestMapping(value = "/discount/deletePersionDiscountById/{id}", method = RequestMethod.DELETE)
     public Result deletePersionDiscountById(@PathVariable("id") Integer id) {
         int rs = discountCouponService.deletePersionDiscountById(id);
         if(rs > 0){
             return Result.ok();
         }
         return Result.error();
     }

    //用户查看优惠券
    @RequestMapping(value = "/discount/findPersionDiscout", method = RequestMethod.GET)
    public Result findPersionDiscout(HttpServletRequest request){
        String token =request.getHeader("Authorization");
        String username = JWTUtil.getUsername(token);
        System.out.println(username);

        List<PersionDiscountTable> list = discountCouponService.findPersionDiscout(username);
        int totals = list.size();
        if(totals > 0){
            HashMap<String, Object> result = new HashMap<>();
            result.put("list", list);
            result.put("totals", totals);
            return Result.ok().data("result", result);
        }else{
            return Result.error();
        }


    }

    //用户查看优惠券
    @RequestMapping(value = "/discount/findAllDiscount", method = RequestMethod.GET)
    public Result findAllDiscount(){

        List<Discount_coupon> list = discountCouponService.findAllDiscount();
        int totals = list.size();
        if(totals > 0){
            HashMap<String, Object> result = new HashMap<>();
            result.put("list", list);
            result.put("totals", totals);
            return Result.ok().data("result", result);
        }else{
            return Result.error();
        }


    }


}
