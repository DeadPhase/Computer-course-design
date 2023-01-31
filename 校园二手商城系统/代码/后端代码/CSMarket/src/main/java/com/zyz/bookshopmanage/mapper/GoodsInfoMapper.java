package com.zyz.bookshopmanage.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zyz.bookshopmanage.pojo.GoodsInfo;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @author Lenovo
 * @version 1.0
 * @data 2022/10/11 15:41
 */
@Repository
public interface GoodsInfoMapper extends BaseMapper<GoodsInfo> {

    /**
     * 商品展示
     * @return
     */
    List<GoodsInfo> goodsInfoList();

}
