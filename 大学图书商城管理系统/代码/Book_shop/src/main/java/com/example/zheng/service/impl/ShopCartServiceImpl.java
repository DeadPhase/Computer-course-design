package com.example.zheng.service.impl;

import com.example.zheng.mapper.ShopCartMapper;
import com.example.zheng.pojo.*;
import com.example.zheng.service.ShopCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ShopCartServiceImpl implements ShopCartService {

    @Autowired
    ShopCartMapper shopCartMapper;


    //将订单和收货地址建立联系显示到前台
    @Override
    public Hvaddress getOrderAddress(String usId) {
        return shopCartMapper.getOrderAddress(usId);
    }

    //将订单和地址建立关系
    @Override
    public int addOrderAddress(Order_address order_address) {
        return shopCartMapper.addOrderAddress(order_address);
    }

    //查询出主键
    @Override
    public List<UserShopRelation> userShopRelation(String usercount) {
        return shopCartMapper.userShopRelation(usercount);
    }

    //删除临时商品表和购物车关系
    @Override
    public int deleteUSR(String usrId) {
        return shopCartMapper.deleteUSR(usrId);
    }

    @Override
    public int deleteList(String usercount) {
        return shopCartMapper.deleteList(usercount);
    }

    // 删除一个订单
    @Override
    public int cancleBuyOrder(String usId) {
        return shopCartMapper.cancleBuyOrder(usId);
    }

    //将一个订单的信息展示出来
    @Override
    public List<Buglog> buylogDetail(String usercount, String usId) {
        return shopCartMapper.buylogDetail(usercount,usId);
    }

    //记录购买日志
    @Override
    public int addBuyLog(Buglog buglog) {
        return shopCartMapper.addBuyLog(buglog);
    }

//    查询购买记录
    @Override
    public List<UserShopList> queryUserShopListBy(String usercount) {
        return shopCartMapper.queryUserShopListBy(usercount);
    }

    @Override
    //查询用户购物车里边的商品
    public List<ShopCart> queryCartList(String usercount) {
        List<ShopCart> cartlist = shopCartMapper.queryShopCartList(usercount);
        return cartlist;
    }

    //动态查询当前用户购物车中某一个商品
    @Override
    public List<ShopCart> queryShopByOption(ShopCart shopCart) {
        return shopCartMapper.queryShopByOption(shopCart);
    }

    //查询购物车里某一个具体的商品信息
    @Override
    public ShopCart shopById(String id) {
        return shopCartMapper.shopById(id);
    }

    //增加商品到购物车
    @Override
    public int addShop(ShopCart shopCart) {
        return shopCartMapper.addShop(shopCart);
    }

    //删除购物车里的商品
    @Override
    public int deleteShop(String scId) {
        return shopCartMapper.deleteShop(scId);
    }


     //修改购物车里商品的信息
    @Override
    public int updateShop(ShopCart shopCart) {
        return shopCartMapper.updateShop(shopCart);
    }

    //判断是否添加购买地址
    @Override
    public Order_address queryOrderAddress(String usId) {
        return shopCartMapper.queryOrderAddress(usId);
    }

    /**
     * 购买商品的操作
     */


    /**
     * 首先便利购物单：目的：1、如果有购物单、根据当前购物单状态。判断当购物单是否已经完成购买
     *                      2、如果还无购物单则创建一个新的购物单
     */
    @Override
    public UserShopList queryUserShopList(String usercount) {
        return shopCartMapper.queryUserShopList(usercount);
    }

    //增加一个临时购物清单
    @Override
    public int addUserShopList(UserShopList userShopList) {
        return shopCartMapper.addUserShopList(userShopList);
    }

    /**
     * 存放购买书籍的临时表
     */
    @Override
   public int addShopList(Shoplist shoplist){

       return shopCartMapper.addShopList(shoplist);
   }

    /**
     * 临时表和购物清单联合表
     */
    @Override
    public int addUserShopRelation(UserShopRelation userShopRelation) {
        return shopCartMapper.addUserShopRelation(userShopRelation);
    }

    //临时表和购物车中的表进行关联\目的是在完成购买操作后\购物车中的商品信息不展示
    @Override
    public int addShopCartList(ShopCartList shopCartList) {
        return shopCartMapper.addShopCartList(shopCartList);
    }

//    联合查询出\购物车中的哪些商品被购买了
    @Override
    public List<Shoplist> querySomeBook(String usercount) {
        return shopCartMapper.querySomeBook(usercount);
    }

//    更新购物车中商品展示的状态
    @Override
    public int updateShopCart(String scId) {
        return shopCartMapper.updateShopCart(scId);
    }

//    更新订单状态
    @Override
    public int updateUserShopList(String usercount) {
        return shopCartMapper.updateUserShopList(usercount);
    }

    //更新订单的付钱状态
    @Override
    public int updatePay(String usercount) {
        return shopCartMapper.updatePay(usercount);
    }

    //更新订单的发货状态
    @Override
    public int updateFahuo(String usercount, String usId) {
        return shopCartMapper.updateFahuo(usercount,usId);
    }

    //更新订单的收货状态
    @Override
    public int updateGain(String usercount, String usId) {
        return shopCartMapper.updateGain(usercount,usId);
    }

//    更新订单的评论状态
    @Override
    public int updateComment(String usercount, String usId) {
        return shopCartMapper.updateComment(usercount,usId);
    }

    //    将当前订单的商品显示在前台
    @Override
    public List<Shoplist> shopList(String usercount) {
        return shopCartMapper.shopList(usercount);
    }

//    查询出要购买的所有商品
    @Override
    public List<Shoplist> buyShopList(String usercount) {
        return shopCartMapper.buyShopList(usercount);
    }


    //修改列表中商品的状态改为已经购买0-->1
    @Override
    public int updateShopList(String slId) {
        return shopCartMapper.updateShopList(slId);
    }


    //搜索当前的购物单是否已经有该商品存在
    @Override
    public Shoplist queryShopListById(String scId) {
        return shopCartMapper.queryShopListById(scId);
    }


}
