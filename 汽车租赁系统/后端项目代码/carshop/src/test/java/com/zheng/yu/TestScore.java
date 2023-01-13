package com.zheng.yu;


import com.zheng.yu.pojo.Scoretable;
import com.zheng.yu.service.ScoretableService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.List;

@SpringBootTest
@Slf4j
public class TestScore {

    @Autowired
    ScoretableService scoretableService;

    //测试增加
//    @Test
//    void addScore(){
//        Scoretable scoretable = new Scoretable();
//        scoretable.setUser_count("wanger");
//        scoretable.setScore(400);
//        int rs = scoretableService.addScore(scoretable);
//    }

    @Test
    void findOneScore(){
        Scoretable scoretable = scoretableService.findOneScoreById("wanger");
        System.out.println(scoretable);
    }

//    @Test
//    void updateScore(){
//        Scoretable scoretable = new Scoretable();
//        scoretable.setUser_count("wanger");
//        scoretable.setScore(500);
//        int rs = scoretableService.updateScore(scoretable);
//
//    }


    @Test
    void page(){
        HashMap<String, Integer> map = new HashMap<>();
        map.put("startIndex",0);
        map.put("pageSize",2);
        List<Scoretable> list = scoretableService.scoreListPage(map);
        int rs =2;

    }

    @Test
    void delete(){
        int rs = scoretableService.deleteScore("wanger");
    }




}
