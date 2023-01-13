package com.zheng.yu.service.Impl;

import com.zheng.yu.mapper.OrderMapper;
import com.zheng.yu.pojo.order.Order_item;
import com.zheng.yu.pojo.order.Order_shipping;
import com.zheng.yu.pojo.order.Orders;
import com.zheng.yu.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderMapper orderMapper;


    //销量前三


    public List<HashMap<String, Object>> saleInFirstThree() {
        return orderMapper.saleInFirstThree();
    }

//    根据订单编号查询一个订单
    public Orders findOneOrderById(String order_id) {
        return orderMapper.findOneOrderById(order_id);
    }

    //个人历史订单信息
    public List<Orders> findHistoryOrderPersion(String username) {
        return orderMapper.findHistoryOrderPersion(username);
    }

//    逻辑删除历史订单信息
    public int deleteHistoryOrderPersion(String order_id) {
        return orderMapper.deleteHistoryOrderPersion(order_id);
    }

    //取车
    public int getCar(Orders orders) {
        return orderMapper.getCar(orders);
    }

    //还车
    public int returnCar(Orders orders) {
        return orderMapper.returnCar(orders);
    }

    //支付订单后修改订单状态1
   public int updateCarPayStatus(String order_id){
        return orderMapper.updateCarPayStatus(order_id);
   }

    /**
     * 订单信息表的增删改查
     */

    //条件查询订单信息
    public List<Orders> ConditionOneOrders(Orders orders) {
        return orderMapper.ConditionOneOrders(orders);
    }

    //删除一个订单信息
    public int deleteOrderById(String id) {
        return orderMapper.deleteOrderById(id);
    }

//    用户查询个人的订单信息
    public List<Orders> findOrderOnlyPersion(String name) {
        return orderMapper.findOrderOnlyPersion(name);
    }

    //    个人订单信息逻辑删除
    public int delete_Persion_Order_Logical(String id) {
        return orderMapper.delete_Persion_Order_Logical(id);
    }

    //增加一个订单信息
    public int addOrders(Orders orders) {
        return orderMapper.addOrders(orders);
    }

    //修改订单信息
    public int updateOrders(Orders orders) {
        return orderMapper.updateOrders(orders);
    }

    //查询所有的订单信息
    public List<Orders> queryAllOrders(Map<String, Integer> map) {
        return orderMapper.queryAllOrders(map);
    }

    //查询所有已经完成的订单
    public List<Orders> queryAllFinishOrders() {
        return orderMapper.queryAllFinishOrders();
    }

    //查询总数
    public Long findOrderTotals() {
        return orderMapper.findOrderTotals();
    }

    //查询某一个订单
    public Orders queryOneOrders(String username) {
        return orderMapper.queryOneOrders(username);
    }


    /**
     *订单详情表的增删改查
     */

    //增加订单详情表
    public int addOrderItem(Order_item order_item) {
        return orderMapper.addOrderItem(order_item);
    }

    //删除订单详情表
    public int deleteOrderItemById(String id) {
        return orderMapper.deleteOrderItemById(id);
    }

    //查询所有的订单详情
    public List<Order_item> queryAllOrderItem() {
        return orderMapper.queryAllOrderItem();
    }

    //查询某一个订单
    public Order_item queryOneOrderItem(String id) {
        return orderMapper.queryOneOrderItem(id);
    }


    /**
     * 订单配送表的增删改查
     */

    //增加订单配送
    public int addOrderShipping(Order_shipping order_shipping) {
        return orderMapper.addOrderShipping(order_shipping);
    }

    //删除订单配送表
    public int deleteOrderShipping(String id) {
        return orderMapper.deleteOrderShipping(id);
    }

    //修改订单配送表
    public int updateOrderShipping(Order_shipping order_shipping) {
        return orderMapper.updateOrderShipping(order_shipping);
    }

    //查询所有的订单配送表
    public List<Order_shipping> queryAllOrderShipping() {
        return orderMapper.queryAllOrderShipping();
    }

    //根据订单号查询订单配送表
    public Order_shipping queryOneOrderShipping(String id) {
        return orderMapper.queryOneOrderShipping(id);
    }
}
