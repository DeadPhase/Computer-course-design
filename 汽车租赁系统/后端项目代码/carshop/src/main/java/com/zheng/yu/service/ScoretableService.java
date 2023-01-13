package com.zheng.yu.service;

import com.zheng.yu.pojo.Deliver_apply_for;
import com.zheng.yu.pojo.Score_exchange;
import com.zheng.yu.pojo.Scoretable;

import java.util.List;
import java.util.Map;

public interface ScoretableService {

    List<Scoretable> scoreListPage(Map map);//分页查询用户积分

    Long findScoreTotals();//查询总数

    Scoretable findOneScoreById(String username);//查询某一个用户积分

    int updateScore(Scoretable scoretable);//修改积分

    int deleteScore(String username); //删除积分

    int addScore(Scoretable scoretable);//增加积分


    //    ==================积分兑换记录===========================

    List<Score_exchange > findAllScoreExchange(String username);//查询一个用户的积分兑换记录

    int deleteScoreExchange(Integer id); //删除一个积分兑换记录

    int deleteUserScoreExchange(String username);//删除一个用户的所有积分兑换记录




}
