package com.zyz.bookshopmanage.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.RandomUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zyz.bookshopmanage.Utils.CurrentTime;
import com.zyz.bookshopmanage.mapper.*;
import com.zyz.bookshopmanage.pojo.*;
import com.zyz.bookshopmanage.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.*;

/**
 * @author Lenovo
 * @version 1.0
 * @data 2022/11/11 15:59
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, OrderInfo> implements OrderService {
    private static final Logger log = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Autowired
    OrderMapper orderMapper;

    @Autowired
    UserMapper userMapper;

    @Autowired
    AdminMapper adminMapper;

    @Autowired
    GoodsInfoMapper goodsInfoMapper;

    @Autowired
    OrderGoodsRefMapper orderGoodsRefMapper;

    @Autowired
    CartShopInfoMapper cartShopInfoMapper;


    /**
     * 包装订单的用户和商品信息
     *
     * @param orderInfo
     */
    private void packOrder(OrderInfo orderInfo) {
        Long orderId = Long.parseLong(orderInfo.getOrderId().toString());
        List<OrderGoodsRef> goodsRefList = orderGoodsRefMapper.findByOrderId(orderId);
        List<GoodsInfo> goodsList = new ArrayList<>();

        for (OrderGoodsRef res : goodsRefList) {
            GoodsInfo goodsDetailInfo = goodsInfoMapper.selectById(res.getGoodsId());
            if (goodsDetailInfo != null) {
                goodsDetailInfo.setCount(res.getCount());
                goodsList.add(goodsDetailInfo);
            }
        }
        log.info(goodsList.toString());
        orderInfo.setGoodsList(goodsList);


    }


    @Transactional
    @Override
    public OrderInfo addOrder(Map<String, Object> maps) {
        log.info("OrderServiceImplEnter：" + String.valueOf(maps));
        String createTime = CurrentTime.getCurrentTime();
        Long userId = Long.parseLong(maps.get("userId").toString());
        Integer level = Integer.parseInt(maps.get("level").toString());
        String goodsInfo = JSON.toJSONString(maps.get("goodsInfo"));
        GoodsInfo orderDetail = JSONObject.parseObject(goodsInfo, GoodsInfo.class);
        HashMap<String, Object> map = new HashMap<>();
        map.put("uid", userId);
        map.put("level", level);
        List<User> users = userMapper.selectByMap(map);
        User user = users.get(0);

        log.info("转化后的GoodsInfo对象：" + orderDetail);

        OrderInfo orderInfo = new OrderInfo();

        orderInfo.setUserId(userId);
        orderInfo.setLevel(level);
        orderInfo.setTotalPrice(orderDetail.getTotalPrice());
        orderInfo.setHarvestName(user.getUserName());
        orderInfo.setHarvestAddress(user.getAddress());
        orderInfo.setHarvestPhone(user.getPhone());

        orderInfo.setFahuoName("李星云");
        orderInfo.setFahuoAddress("湖南省长沙市开福区001街道01单元");
        orderInfo.setFahuoPhone("18238918291");
        orderInfo.setCreateTime(createTime);
        orderInfo.setFileName(orderDetail.getFileName());
        orderInfo.setStatus(0);
        orderInfo.setTotalPrice(orderDetail.getTotalPrice());
        //订单id
        String orderId = userId + DateUtil.format(new Date(), "yyyyMMddHHmm") + RandomUtil.randomNumbers(4);
        orderInfo.setOrderId(orderId);
        //生成订单
        orderMapper.insert(orderInfo);


        /**
         * 扣除库存
         */
        //查询商品信息
        GoodsInfo goodsDetail = goodsInfoMapper.selectById(orderDetail.getId());
        int sales = goodsDetail.getSales();
        Integer goodsCount = goodsDetail.getCount();
        //订单商品数量
        Integer orderCount = orderDetail.getCount();
        if (orderCount <= goodsCount) {
            goodsDetail.setCount(goodsCount - orderCount);
            //销量增加
            goodsDetail.setSales(sales + orderCount);
            goodsInfoMapper.updateById(goodsDetail);

            //建立关系
            OrderGoodsRef orderGoodsRef = new OrderGoodsRef();
            orderGoodsRef.setGoodsId(orderDetail.getId());
            orderGoodsRef.setOrderId(orderId);
            orderGoodsRef.setCount(orderDetail.getCount());
            orderGoodsRefMapper.insert(orderGoodsRef);

        }

        //删除购物车对应的商品
        HashMap<String, Object> cartShopMap = new HashMap<>();
        cartShopMap.put("userId", userId);
        cartShopMap.put("goodsId", orderDetail.getId());
        cartShopInfoMapper.deleteByMap(cartShopMap);


        return null;
    }

    /**
     * 查看个人订单
     *
     * @param maps
     * @return
     */
    @Override
    public List<OrderInfo> findOrderAll(Map<String, Object> maps) {
        Long userId = Long.parseLong(maps.get("userId").toString());
        Integer level = Integer.parseInt(maps.get("level").toString());
        HashMap<String, Object> obj = new HashMap<>();
        obj.put("userId", userId);
        obj.put("level", level);
        List<OrderInfo> orderList = orderMapper.selectByMap(obj);

        /**
         * 将商品信息和用户信息放入当前订单
         */
        for (OrderInfo orderInfo : orderList) {
            packOrder(orderInfo);
        }

        return orderList;
    }

    /**
     * 分页查询插件--管理员查看所有订单
     */
    @Override
    public HashMap<String, Object> findAllOrderInfo(Integer currentPage, Integer pageSize) {
        List<OrderInfo> orderInfoList = null;
        Long totals = 0L;
        Page<OrderInfo> orderInfoPage = new Page<>(currentPage, pageSize);
        orderMapper.selectPage(orderInfoPage, null);
        try {
            orderInfoList = orderInfoPage.getRecords();
            totals = orderInfoPage.getTotal();

        } catch (Exception e) {
            e.printStackTrace();
        }

        HashMap<String, Object> result = new HashMap<>();
        result.put("orderList", orderInfoList);
        result.put("totals", totals);
        return result;
    }

    /**
     * 用户付款
     *
     * @param uid
     * @param paymoney
     * @return
     */
    @Transactional
    @Override
    public Map<String, Object> payment(Long uid, Double paymoney, Long orderId) {
        log.info("Enter payment method...");
        HashMap<String, Object> result = new HashMap<>();
        User queryUser = null;
        int rs = 0;

        try {
            //用户扣款
            User user = userMapper.selectById(uid);
            Double accountBalance = user.getMoney();
            if (accountBalance < paymoney) {
                result.put("errMessage", "余额不总、请充值");
            }
            Double balanceAfter = accountBalance - paymoney;
            UpdateWrapper<User> updateUserWrapper = new UpdateWrapper<>();
            updateUserWrapper.like("uid", uid);
            updateUserWrapper.set("money", balanceAfter);
            userMapper.update(null, updateUserWrapper);

            //商家得到汇款
            Admin admin = adminMapper.selectById(1);
            Double accountBalanceAdmin = admin.getMoney();
            Double balanceAfterAdmin = accountBalanceAdmin + paymoney;
            UpdateWrapper<Admin> updateUserWrapperAdmin = new UpdateWrapper<>();
            updateUserWrapperAdmin.like("uid", 1);
            updateUserWrapperAdmin.set("money", balanceAfterAdmin);
            adminMapper.update(null, updateUserWrapperAdmin);

            //修改状态为1---待发货
            UpdateWrapper<OrderInfo> wrapper = new UpdateWrapper<>();
            wrapper.like("orderId", orderId);
            wrapper.set("status", 1);
            orderMapper.update(null, wrapper);

        } catch (Exception e) {
            e.printStackTrace();
        }
        queryUser = userMapper.selectById(uid);
        result.put("user", queryUser);
        return result;
    }

    /**
     * 用户退款
     * @param orderId
     * @return
     */
    @Override
    public Integer onRefund(Long orderId) {
        //修改状态为2---申请退款中
        UpdateWrapper<OrderInfo> wrapper = new UpdateWrapper<>();
        wrapper.like("orderId", orderId);
        wrapper.set("status", 2);
        Integer rs = orderMapper.update(null, wrapper);
        return rs;
    }

    /**
     * 用户取消退款
     * @param orderId
     * @return
     */
    @Override
    public Integer onCancelRefund(Long orderId) {
        //修改状态为1---待发货
        UpdateWrapper<OrderInfo> wrapper = new UpdateWrapper<>();
        wrapper.like("orderId", orderId);
        wrapper.set("status", 1);
        Integer rs = orderMapper.update(null, wrapper);
        return rs;
    }

    /**
     * 用户退货
     * @param orderId
     * @return
     */
    @Override
    public Integer onReturnable(Long orderId) {
        //修改状态为4---退货
        UpdateWrapper<OrderInfo> wrapper = new UpdateWrapper<>();
        wrapper.like("orderId", orderId);
        wrapper.set("status", 4);
        Integer rs = orderMapper.update(null, wrapper);
        return rs;
    }

    /**
     * 取消退货
     * @param orderId
     * @return
     */
    @Override
    public Integer onCancelReturnable(Long orderId) {
        //修改状态为3---已发货
        UpdateWrapper<OrderInfo> wrapper = new UpdateWrapper<>();
        wrapper.like("orderId", orderId);
        wrapper.set("status", 3);
        Integer rs = orderMapper.update(null, wrapper);
        return rs;
    }

    /**
     * 商家发货
     * @param orderId
     * @return
     */
    @Override
    public Integer onShipments(Long orderId) {
        //修改状态为3---完成
        UpdateWrapper<OrderInfo> wrapper = new UpdateWrapper<>();
        wrapper.like("orderId", orderId);
        wrapper.set("status", 3);
        Integer rs = orderMapper.update(null, wrapper);
        return rs;
    }
}
