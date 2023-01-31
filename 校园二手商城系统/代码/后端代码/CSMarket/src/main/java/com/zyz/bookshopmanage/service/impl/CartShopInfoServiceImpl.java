package com.zyz.bookshopmanage.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zyz.bookshopmanage.mapper.CartShopInfoMapper;
import com.zyz.bookshopmanage.pojo.CartShopInfo;
import com.zyz.bookshopmanage.service.CartShopInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Lenovo
 * @version 1.0
 * @data 2022/11/8 15:16
 */
@Service
public class CartShopInfoServiceImpl extends ServiceImpl<CartShopInfoMapper, CartShopInfo> implements CartShopInfoService {
    @Autowired
    CartShopInfoMapper cartShopInfoMapper;
    @Override
    public List<CartShopInfo> queryCartShopByUserId(Long userId, Integer level) {
        return cartShopInfoMapper.queryCartShopByUserId(userId,level);
    }

    /**
     *
     * @param userId
     * @param goodsId
     * @return
     */
    @Override
    public CartShopInfo findCartShopInfoByUserIdGoodsId(Long userId, Long goodsId) {

        return cartShopInfoMapper.findCartShopInfoByUserIdGoodsId(userId,goodsId);
    }
}
