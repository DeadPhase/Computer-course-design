package com.zyz.bookshopmanage.service;

import com.zyz.bookshopmanage.pojo.OrderInfo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Lenovo
 * @version 1.0
 * @data 2022/11/11 15:59
 */
public interface OrderService {

    public OrderInfo addOrder(Map<String,Object> maps);

    public List<OrderInfo> findOrderAll(Map<String,Object> maps);

    /**
     * 分页查询插件--管理员查看所有订单
     * @param currentPage
     * @param pageSize
     * @return
     */
    public HashMap<String,Object> findAllOrderInfo(Integer currentPage, Integer pageSize);

    /**
     * 用户付款
     * @param uid
     * @param paymoney
     * @return
     */
    public Map<String,Object> payment(Long uid,Double paymoney,Long orderId);

    /**
     * 用户退款
     * @param orderId
     * @return
     */
    Integer onRefund(Long orderId);

    /**
     * 用户取消退款
     * @param orderId
     * @return
     */
    Integer onCancelRefund(Long orderId);

    /**
     * 用户退货
     * @param orderId
     * @return
     */
    Integer onReturnable(Long orderId);

    /**
     * 取消退货
     * @param orderId
     * @return
     */
    Integer onCancelReturnable(Long orderId);

    /**
     * 商家发货
     * @param orderId
     * @return
     */
    Integer onShipments(Long orderId);
}
