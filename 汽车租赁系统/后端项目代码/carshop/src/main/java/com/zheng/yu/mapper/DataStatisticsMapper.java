package com.zheng.yu.mapper;


import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface DataStatisticsMapper {
    Long findOrderDay();//查询当天的订单量
    Long findUserDay();  //查询当天的新增用户数量
    Long findCarDay();   //查询当天上架的汽车数量

    //查询各种汽车的租赁数量
    public List<HashMap<String, Object>> getCarTypeNum();

    //查询汽车的租赁排行情况
    public List<HashMap<String,Object>> getCarRanking();
}
