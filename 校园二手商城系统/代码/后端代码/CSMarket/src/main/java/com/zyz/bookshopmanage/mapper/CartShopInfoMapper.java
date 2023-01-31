package com.zyz.bookshopmanage.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zyz.bookshopmanage.pojo.CartShopInfo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Lenovo
 * @version 1.0
 * @data 2022/11/8 15:15
 */
@Repository
public interface CartShopInfoMapper extends BaseMapper<CartShopInfo> {
    /**
     * 查询用户购物车中的商品id
     * @param userId
     * @param level
     * @return
     */
     List<CartShopInfo> queryCartShopByUserId(Long userId,Integer level);

    /**
     * 查询购物车中是否已经添加过同一个商品
     * @param userId
     * @param goodsId
     * @return
     */
     CartShopInfo findCartShopInfoByUserIdGoodsId(Long userId,Long goodsId);
}
