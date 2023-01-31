package com.zyz.bookshopmanage.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zyz.bookshopmanage.pojo.GoodsInfo;

import java.util.List;

/**
 * @author Lenovo
 * @version 1.0
 * @data 2022/10/11 15:43
 */
public interface GoodsInfoService extends IService<GoodsInfo> {
    /**
     * 商品展示
     * @return
     */
    List<GoodsInfo> goodsInfoList();
}
