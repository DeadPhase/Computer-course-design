package com.zheng.yu.mapper;


import com.zheng.yu.pojo.Shopping_car;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

//购物车
@Mapper
@Repository
public interface ShoppingCarMapper {


    int addShoppingCar(Shopping_car shopping_car);//增加租赁汽车到购物车

    int deleteShoppingCar(Integer id);//根据商品id删除租赁汽车

    int updateShoppingCar(Shopping_car shopping_car);//修改租赁汽车信息

    List<Shopping_car> findShoppingCarByName(String username);//查询用户收藏汽车列表


}
