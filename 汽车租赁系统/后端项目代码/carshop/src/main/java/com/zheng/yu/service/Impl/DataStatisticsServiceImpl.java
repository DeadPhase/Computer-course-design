package com.zheng.yu.service.Impl;

import com.zheng.yu.mapper.DataStatisticsMapper;
import com.zheng.yu.service.DataStatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DataStatisticsServiceImpl implements DataStatisticsService {

    @Autowired
    DataStatisticsMapper dataStatisticsMapper;

    public Long findOrderDay() {
        return dataStatisticsMapper.findOrderDay();
    }

    public Long findUserDay() {
        return dataStatisticsMapper.findUserDay();
    }

    public Long findCarDay() {
        return dataStatisticsMapper.findCarDay();
    }


//    查询各种汽车的租赁数量
    public List<HashMap<String, Object>> getCarTypeNum() {
        return dataStatisticsMapper.getCarTypeNum();
    }

    //查询汽车的租赁排行情况
    public List<HashMap<String, Object>> getCarRanking() {
        return dataStatisticsMapper.getCarRanking();
    }


}
