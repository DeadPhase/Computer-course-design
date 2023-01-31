package com.zyz.bookshopmanage.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zyz.bookshopmanage.pojo.CartShopInfo;

import java.util.List;

/**
 * @author Lenovo
 * @version 1.0
 * @data 2022/11/8 15:15
 */
public interface CartShopInfoService extends IService<CartShopInfo> {
    List<CartShopInfo> queryCartShopByUserId(Long userId, Integer level);

    CartShopInfo findCartShopInfoByUserIdGoodsId(Long userId,Long goodsId);


}
