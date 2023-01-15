package com.example.zheng.mapper;

import com.example.zheng.pojo.*;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;


@Mapper    //这个注解表示这个是mybatis的mapeper
@Repository
public interface ShopCartMapper {

/**
 * 搜索当前的购物单是否已经有该商品存在
 */
   Shoplist queryShopListById(String scId);


  /**
   * 将订单和收货地址建立联系显示到前台
   */

  Hvaddress getOrderAddress(String usId);

  /**
   * 将订单和地址建立关系
   */
  int addOrderAddress(Order_address order_address);

  /**
   * 删除一个订单

   */
  int  cancleBuyOrder(String usId);

  /**
   * 删除临时商品表和购物车关系
   */
  int deleteUSR(String usrId);

  /**
   *查询出主键
   */
  List<UserShopRelation> userShopRelation(String usercount);

  //删除临时订单表
  int deleteList(String usercount);

    //记录购买日志
    int addBuyLog(Buglog buglog);

    //查询购买记录
    List<UserShopList> queryUserShopListBy(String usercount);

    //将一个订单的信息展示出来
    List<Buglog> buylogDetail(String usercount,String usId);


    /**
     * 查询当前用户购物车所具有的商品
     */
    List<ShopCart> queryShopCartList(String scName);

    /**
     * 动态查询当前用户购物车中某一个商品
     */
    List<ShopCart> queryShopByOption(ShopCart shopCart);

    /**
     * 查询购物车里某一个具体的商品信息
     */
    ShopCart shopById(String id);

    /**
     * 增加商品进入购物车
     */
    int addShop(ShopCart shopCart);

    /**
     * 删除商品
     */
    int deleteShop(String scId);

    /**
     * 修改购物车里商品的信息
     */
    int updateShop(ShopCart shopCart);

//===================================购买商品================================

  /**
   * 判断是否添加购买地址
   */
    Order_address queryOrderAddress(String usId);

  /**
     * 首先便利购物单：目的：1、如果有购物单、根据当前购物单状态。判断当购物单是否已经完成购买
     *                      2、如果还无购物单则创建一个新的购物单
     */
    UserShopList queryUserShopList(String usercount);//根据当前登录对象进行判断

    /**
     * 存放购买书籍的临时表
     */
    int addShopList(Shoplist shoplist);

    /**
     * 增加一个临时购物清单
     */
    int addUserShopList(UserShopList userShopList);

    /**
     * 临时表和购物清单联合表
     */
    int addUserShopRelation(UserShopRelation userShopRelation);

    /**
     * 临时表和购物车中的表进行关联\目的是在完成购买操作后\购物车中的商品信息不展示
     */
    int addShopCartList(ShopCartList shopCartList);

    /**
     * 联合查询出、购物车中的哪些商品被购买了
     */
    List<Shoplist> querySomeBook(String usercount);

    /**
     * 更新购物车中商品展示的状态
     */
    int updateShopCart(String scId);

    //更新订单状态
    int updateUserShopList(String usercount);


    //更新订单的付钱状态
    int updatePay(String usercount);

    //更新订单的发货状态
    int updateFahuo(String usercount,String usId);

    //更新订单的收货状态
    int updateGain(String usercount,String usId);

    //更新订单的评论状态
    int updateComment(String usercount,String usId);


    /**
     * 将当前订单的商品显示在前台
     */
    List<Shoplist> shopList(String usercount);



//开始购买
    //查询出要购买的所有商品
     List<Shoplist> buyShopList(String usercount);

     //修改列表中商品的状态改为已经购买0-->1
    int updateShopList(String slId);


}
