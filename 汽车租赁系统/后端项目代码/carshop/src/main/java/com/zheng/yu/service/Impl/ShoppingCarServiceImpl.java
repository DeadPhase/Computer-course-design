package com.zheng.yu.service.Impl;

import com.zheng.yu.mapper.ShoppingCarMapper;
import com.zheng.yu.pojo.Shopping_car;
import com.zheng.yu.service.ShoppingCarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ShoppingCarServiceImpl implements ShoppingCarService {

    @Autowired
    ShoppingCarMapper shoppingCarMapper;

    //增加租赁汽车到购物车
    public int addShoppingCar(Shopping_car shopping_car) {
        return shoppingCarMapper.addShoppingCar(shopping_car);
    }

    //根据商品id删除租赁汽车
    public int deleteShoppingCar(Integer id) {
        return shoppingCarMapper.deleteShoppingCar(id);
    }

    //修改租赁汽车信息
    public int updateShoppingCar(Shopping_car shopping_car) {
        return shoppingCarMapper.updateShoppingCar(shopping_car);
    }

    //查询用户的收藏汽车
    public List<Shopping_car> findShoppingCarByName(String username) {
        return shoppingCarMapper.findShoppingCarByName(username);
    }

}
