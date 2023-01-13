package com.zheng.yu.service;

import com.zheng.yu.pojo.Shopping_car;

import java.util.List;
import java.util.Map;

public interface ShoppingCarService {

    int addShoppingCar(Shopping_car shopping_car);//增加租赁汽车到购物车

    int deleteShoppingCar(Integer id);//根据商品id删除租赁汽车

    int updateShoppingCar(Shopping_car shopping_car);//修改租赁汽车信息

    List<Shopping_car> findShoppingCarByName(String username);//查询一个用户的收藏

}
