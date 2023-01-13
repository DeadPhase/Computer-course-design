package com.zheng.yu.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface DataStatisticsService {
    Long findOrderDay();//查询当天的订单量
    Long findUserDay();  //查询当天的新增用户数量
    Long findCarDay();   //查询当天上架的汽车数量
    //查询各种汽车的租赁数量
    public List<HashMap<String, Object>> getCarTypeNum();

    //查询汽车的租赁排行情况
    public List<HashMap<String,Object>> getCarRanking();
}
