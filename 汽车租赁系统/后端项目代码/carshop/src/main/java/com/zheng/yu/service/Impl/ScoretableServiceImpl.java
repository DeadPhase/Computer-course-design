package com.zheng.yu.service.Impl;

import com.zheng.yu.mapper.ScoretableMapper;
import com.zheng.yu.pojo.Score_exchange;
import com.zheng.yu.pojo.Scoretable;
import com.zheng.yu.service.ScoretableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ScoretableServiceImpl implements ScoretableService {

    @Autowired
    ScoretableMapper scoretableMapper;

    public List<Scoretable> scoreListPage(Map map) {
        return scoretableMapper.scoreListPage(map);
    }

    public Long findScoreTotals() {
        return scoretableMapper.findScoreTotals();
    }

    public Scoretable findOneScoreById(String username) {
        return scoretableMapper.findOneScoreById(username);
    }

    public int updateScore(Scoretable scoretable) {
        return scoretableMapper.updateScore(scoretable) ;
    }

    public int deleteScore(String username) {
        return scoretableMapper.deleteScore(username);
    }

    public int addScore(Scoretable scoretable) {
        return scoretableMapper.addScore(scoretable);
    }

    //    ==================积分兑换记录===========================

    public List<Score_exchange> findAllScoreExchange(String username) {
        return scoretableMapper.findAllScoreExchange(username);
    }

//    删除一个积分兑换记录
    public int deleteScoreExchange(Integer id) {
        return scoretableMapper.deleteScoreExchange(id);
    }

    // 删除一个用户的所有积分兑换记录
    public int deleteUserScoreExchange(String username) {
        return scoretableMapper.deleteUserScoreExchange(username);
    }
}
