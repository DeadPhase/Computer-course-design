package com.zheng.yu.controller;


import com.zheng.yu.config.response.Result;
import com.zheng.yu.pojo.Wallet;
import com.zheng.yu.service.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;



@Api(value = "数据分析", tags = "数据分析")
@ApiModel
@RestController
public class DataStatisticsController {

    @Autowired
    DataStatisticsService dataStatisticsService;

    @Autowired
    UserService userService;

    @Autowired
    OrderService orderService;

    @Autowired
    CarService carService;

    @Autowired
    WalletService walletService;

    //查询汽车租赁情况
    @RequestMapping(value = "/static/getcarranking", method = RequestMethod.GET)
    public Result getCarRanking(){
        List<HashMap<String, Object>> list = dataStatisticsService.getCarRanking();
        System.out.println(list);
        return Result.ok().data("carRank",list);

    }


    //不同类型的汽车的销量
    @RequestMapping(value = "/static/getcartypenum", method = RequestMethod.GET)
    public Result getCarTypeNum(){
        Map<String, String> map = new HashMap<String, String>();
//        List<HashMap<String, String>> list = mapper.getRentAmountMonthDetail(startTime, endTime);

        List<HashMap<String, Object>> list = dataStatisticsService.getCarTypeNum();
        System.out.println(list);
        return Result.ok().data("carTypeNum",list);

    }


    @RequestMapping(value = "/static/adminStatic", method = RequestMethod.GET)
    public Result findadminIndex(){

        //收入
        Wallet wallet = walletService.querywallet("admin");//查询账户余额


//        当天的数据
        Long todayOrderNum = dataStatisticsService.findOrderDay();
        Long todayUserNum = dataStatisticsService.findUserDay();
        Long todayCarNum = dataStatisticsService.findCarDay();

        //总的数据
        Long allUserNum = userService.findUserTotals();
        Long allOrderNum = orderService.findOrderTotals();
        Long allCarNum =carService.findCarTotals();
        Double totalMoney = wallet.getMoney();

        HashMap<String, Object> result = new HashMap<>();
        result.put("todayOrderNum",todayOrderNum);
        result.put("todayUserNum",todayUserNum);
        result.put("todayCarNum",todayCarNum);
        result.put("allUserNum",allUserNum);
        result.put("allOrderNum",allOrderNum);
        result.put("allCarNum",allCarNum);
        result.put("totalMoney",totalMoney);
        return Result.ok().data("result",result);


    }
}
