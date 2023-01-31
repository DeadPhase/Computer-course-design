package com.zyz.bookshopmanage.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zyz.bookshopmanage.pojo.TypeInfo;

import java.util.List;
import java.util.Map;

/**
 * @author Lenovo
 * @version 1.0
 * @data 2022/10/10 21:26
 */
public interface TypeInfoService extends IService<TypeInfo> {
    /**
     * 分页查询商品种类信息
     * @param map
     * @return
     */
    List<TypeInfo> findAllTypeInfo(Map<String,Integer> map);

    /**
     * 查询化妆品种类总数
     * @return
     */
    Long findTypeInfoTotals();
    /**
     * 根据商品名称查找
     * @param name
     * @return
     */
    TypeInfo findByName(String name);
}
