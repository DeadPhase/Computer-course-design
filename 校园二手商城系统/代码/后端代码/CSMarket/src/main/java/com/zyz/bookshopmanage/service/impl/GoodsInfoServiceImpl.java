package com.zyz.bookshopmanage.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zyz.bookshopmanage.mapper.GoodsInfoMapper;
import com.zyz.bookshopmanage.pojo.GoodsInfo;
import com.zyz.bookshopmanage.service.GoodsInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Lenovo
 * @version 1.0
 * @data 2022/10/11 15:44
 */
@Service
public class GoodsInfoServiceImpl extends ServiceImpl<GoodsInfoMapper, GoodsInfo> implements GoodsInfoService {
    @Autowired
    private  GoodsInfoMapper goodsInfoMapper;
    @Override
    public List<GoodsInfo> goodsInfoList() {
        return goodsInfoMapper.goodsInfoList();
    }
}
