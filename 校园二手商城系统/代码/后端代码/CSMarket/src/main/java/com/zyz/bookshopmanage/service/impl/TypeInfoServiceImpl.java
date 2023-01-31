package com.zyz.bookshopmanage.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zyz.bookshopmanage.mapper.TypeInfoMapper;
import com.zyz.bookshopmanage.pojo.TypeInfo;
import com.zyz.bookshopmanage.service.TypeInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author Lenovo
 * @version 1.0
 * @data 2022/10/10 21:30
 */
@Service
public class TypeInfoServiceImpl extends ServiceImpl<TypeInfoMapper, TypeInfo> implements TypeInfoService {

    @Autowired
    TypeInfoMapper typeInfoMapper;

    @Override
    public List<TypeInfo> findAllTypeInfo(Map<String, Integer> map) {
        return typeInfoMapper.findAllTypeInfo(map);
    }

    @Override
    public Long findTypeInfoTotals() {
        return typeInfoMapper.findTypeInfoTotals();
    }

    @Override
    public TypeInfo findByName(String name) {
        return typeInfoMapper.findByName(name);
    }
}
