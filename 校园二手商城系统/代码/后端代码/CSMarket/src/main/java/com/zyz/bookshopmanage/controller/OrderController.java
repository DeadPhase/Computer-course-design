package com.zyz.bookshopmanage.controller;


import com.zyz.bookshopmanage.config.response.Result;
import com.zyz.bookshopmanage.pojo.OrderInfo;
import com.zyz.bookshopmanage.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Lenovo
 * @version 1.0
 * @data 2022/11/11 16:01
 */
@RestController
@RequestMapping("/order")
public class OrderController {
    private static final Logger log = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    OrderService orderService;

    /**
     * 生成订单
     *
     * @param maps
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Result addOrder(@RequestBody Map<String, Object> maps) {

        System.out.println(maps);
        orderService.addOrder(maps);
        return null;
    }

    /**
     * 查看个人订单
     *
     * @param map
     * @return
     */
    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    public Result findAll(@RequestParam HashMap<String, Object> map) {
        List<OrderInfo> orderList = orderService.findOrderAll(map);
        return Result.ok().data("orderList", orderList);

    }

    /**
     * 分页查询插件--管理员查看所有订单
     */
    @RequestMapping(value = "/findAllOrderInfo", method = RequestMethod.GET)
    public Result findAllOrderInfo(@RequestParam Map<String, Object> map) {
        log.info("Enter OrderController Use findAllOrderInfo Method...");
        Integer currentPage = Integer.parseInt(map.get("page").toString());
        Integer pageSize = Integer.parseInt(map.get("size").toString());
        HashMap<String, Object> result = orderService.findAllOrderInfo(currentPage, pageSize);
        if (result != null) {
            return Result.ok().data("result", result);
        } else {
            return Result.error().data("errMessage", "订单查询失败");
        }


    }


    /**
     * 付款
     *
     * @param map
     * @return
     */
    @RequestMapping(value = "/paymoney", method = RequestMethod.PUT)
    public Result payment(@RequestBody HashMap<String, Object> map) {
        Long uid = Long.parseLong(map.get("uid").toString());

        Long orderId = Long.parseLong(map.get("orderId").toString());
        Double payMoney = Double.parseDouble(map.get("paymoney").toString());
        Map<String, Object> result = orderService.payment(uid, payMoney, orderId);
        if (result != null) {
            return Result.ok().data("result", result);
        } else {
            return Result.error().data("errMessage", "付款失败、请联系管理员");
        }

    }

    /**
     * 退款
     *
     * @param map
     * @return
     */
    @RequestMapping(value = "/refund",method = RequestMethod.PUT)
    public Result onRefund(@RequestBody HashMap<String, Object> map) {
        Long orderId = Long.parseLong(map.get("orderId").toString());
        Integer rs = orderService.onRefund(orderId);
        if(rs > 0){
            return Result.ok();
        }else{
            return Result.error().data("errMessage","退款失败，请联系商家");
        }


    }

    /**
     * 取消退款
     *
     * @param map
     * @return
     */
    @RequestMapping(value = "/cancelRefund",method = RequestMethod.PUT)
    public Result onCancelRefund(@RequestBody HashMap<String, Object> map) {
        Long orderId = Long.parseLong(map.get("orderId").toString());
        Integer rs = orderService.onCancelRefund(orderId);
        if(rs > 0){
            return Result.ok();
        }else{
            return Result.error().data("errMessage","取消退款失败，请联系商家");
        }

    }

    /**
     * 退货
     *
     * @param map
     * @return
     */
    @RequestMapping(value = "/onReturnable",method = RequestMethod.PUT)
    public Result onReturnable(@RequestBody HashMap<String, Object> map) {
        Long orderId = Long.parseLong(map.get("orderId").toString());
        Integer rs = orderService.onReturnable(orderId);
        if(rs > 0){
            return Result.ok();
        }else{
            return Result.error().data("errMessage","取消退款失败，请联系商家");
        }

    }

    /**
     * 取消退货
     *
     * @param map
     * @return
     */
    @RequestMapping(value = "/onCancelReturnable",method = RequestMethod.PUT)
    public Result onCancelReturnable(@RequestBody HashMap<String, Object> map) {
        Long orderId = Long.parseLong(map.get("orderId").toString());
        Integer rs = orderService.onCancelReturnable(orderId);
        if(rs > 0){
            return Result.ok();
        }else{
            return Result.error().data("errMessage","取消退货失败，请联系商家");
        }

    }

    /**
     * 商家发货
     *
     * @param map
     * @return
     */
    @RequestMapping(value = "/shipments",method = RequestMethod.PUT)
    public Result onShipments(@RequestBody HashMap<String, Object> map) {
        Long orderId = Long.parseLong(map.get("orderId").toString());
        Integer rs = orderService.onShipments(orderId);
        if(rs > 0){
            return Result.ok();
        }else{
            return Result.error().data("errMessage","发货失败、请联系系统开发人员!");
        }

    }


}
