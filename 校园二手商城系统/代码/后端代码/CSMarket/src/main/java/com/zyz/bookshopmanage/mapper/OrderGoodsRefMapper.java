package com.zyz.bookshopmanage.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zyz.bookshopmanage.pojo.OrderGoodsRef;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Lenovo
 * @version 1.0
 * @data 2022/11/11 17:07
 */
@Repository
public interface OrderGoodsRefMapper extends BaseMapper<OrderGoodsRef> {

    /**
     * 查看订单-商品联合表。目的找到商品id
     * @param orderId
     * @return
     */
    List<OrderGoodsRef> findByOrderId(Long orderId);
}
