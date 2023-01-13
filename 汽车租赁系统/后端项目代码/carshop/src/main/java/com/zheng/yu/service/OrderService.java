package com.zheng.yu.service;

import com.zheng.yu.pojo.order.Order_item;
import com.zheng.yu.pojo.order.Order_shipping;
import com.zheng.yu.pojo.order.Orders;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface OrderService {

    List<HashMap<String,Object>> saleInFirstThree();//销售前三

    /**
     * 订单信息表的增删改查
     */

    Orders findOneOrderById(String order_id);//根据订单编号查询一个订单

    List<Orders> findHistoryOrderPersion(String username);//查询已完成订单

    int deleteHistoryOrderPersion(String order_id);//逻辑删除历史订单信息

    int getCar(Orders orders);//取车

    int returnCar(Orders orders);//还车

    int updateCarPayStatus(String order_id);//支付订单后修改订单状态1

    List<Orders> ConditionOneOrders(Orders orders);//条件查询订单信息

    int deleteOrderById(String id);//删除一个订单信息

    List<Orders> findOrderOnlyPersion(String name);//用户查询个人的订单信息

    int delete_Persion_Order_Logical(String id);//个人订单信息逻辑删除

    int addOrders(Orders orders);//增加一个订单信息

    int updateOrders(Orders orders);//修改订单信息

    List<Orders> queryAllOrders(Map<String, Integer> map);//查询所有的订单信息

    List<Orders> queryAllFinishOrders();//查询所有已经完成的订单

    Long findOrderTotals();//查询总数

    Orders queryOneOrders(String username);//查询某一个订单


    /**
     *订单详情表的增删改查
     */
    int addOrderItem(Order_item order_item);//增加订单详情表

    int deleteOrderItemById(String id);//删除订单详情表

    List<Order_item> queryAllOrderItem();//查询所有的订单详情

    Order_item queryOneOrderItem(String id);//查询某一个订单详情


    /**
     * 订单配送表的增删改查
     */
    int addOrderShipping(Order_shipping order_shipping);//增加订单配送

    int deleteOrderShipping(String id);//删除订单配送表

    int updateOrderShipping(Order_shipping order_shipping);//修改订单配送表

    List<Order_shipping> queryAllOrderShipping();//查询所有的订单配送表

    Order_shipping queryOneOrderShipping(String id);//根据订单号查询订单配送表

}
