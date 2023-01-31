package com.zyz.bookshopmanage.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zyz.bookshopmanage.pojo.TypeInfo;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author Lenovo
 * @version 1.0
 * @data 2022/10/10 21:23
 */
@Repository
public interface TypeInfoMapper extends BaseMapper<TypeInfo> {


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
