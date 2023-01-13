package com.zheng.yu.controller;


import com.zheng.yu.config.handler.BusinessException;
import com.zheng.yu.config.response.Result;
import com.zheng.yu.config.response.ResultCode;
import com.zheng.yu.pojo.Score_exchange;
import com.zheng.yu.pojo.Scoretable;
import com.zheng.yu.pojo.User;
import com.zheng.yu.service.ScoretableService;
import com.zheng.yu.utils.JWTUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Api(value = "用户积分模块", tags = "用户积分接口")
public class ScoretableController {

    @Autowired
    ScoretableService scoretableService;

    @ApiOperation(value = "用户信息分页", notes = "根据传入的页码和页面大小查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "当前页", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "size", value = "当前页大小", required = true, dataType = "Integer")
    }
    )
    @RequestMapping(value = "/score/findAll/{page}/{size}", method = RequestMethod.GET)
    public Result findAll(@PathVariable("page") Integer Page, @PathVariable("size") Integer size) {

        //准备数据 通过这两个参数，可以算出start   计算方法 start=size(page-1)
        int currentPage = Page;//当前是第几页
        int pageSize = size; //页面大小
        Map<String, Integer> map = new HashMap<String, Integer>();
        map.put("startIndex", (currentPage - 1) * pageSize);
        map.put("pageSize", pageSize);
        List<Scoretable> scoretableList = scoretableService.scoreListPage(map);
        HashMap<String, Object> result = new HashMap<>();
        Long totals = scoretableService.findScoreTotals();
        result.put("score", scoretableList);
        result.put("totals", totals);
        return Result.ok().data("result", result);

    }



    //修改用户信息
    @ApiOperation(value = "修改用户积分信息", notes = "根据id修改用户的积分信息")
    @ApiImplicitParams(
            @ApiImplicitParam(name = "Scoretable")
    )
    @RequestMapping(value = "/score/updateScore", method = RequestMethod.PUT)
    public Result updateScore(@RequestBody Scoretable scoretable) {
        int rs = scoretableService.updateScore(scoretable);
        return Result.ok();

    }

    @ApiOperation(value = "删除用户积分信息", notes = "根据id删除用户积分信息")
    @ApiImplicitParam(name = "id", value = "用户id", required = true, dataType = "String")
    @RequestMapping(value = "/score/deleteScore/{id}", method = RequestMethod.DELETE)
    public Result deleteScore(@PathVariable("id") String id) {
        int rs = scoretableService.deleteScore(id);
        return Result.ok();
    }

    @ApiOperation(value = "用户积分添加", notes = "用户积分添加")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "User")
    })
    @RequestMapping(value = "/score/addScore", method = RequestMethod.POST)
    public Result register(@RequestBody Scoretable scoretable) {
        int rs = scoretableService.addScore(scoretable);
        return Result.ok();
    }


    //    ==================积分兑换记录===========================

    //根据用户id查询用户信息
    @ApiOperation(value = "用户积分兑换记录", notes = "根据账号查询某一个用户的积分兑换记录信息")
    @ApiImplicitParam(name = "username", value = "用户账号", required = true, dataType = "String")
    @RequestMapping(value = "/score/findAllScoreExchange/{username}", method = RequestMethod.GET)
    public Result findAllScoreExchange(@PathVariable String username) {

        List<Score_exchange> score_exchangeList = scoretableService.findAllScoreExchange(username);
        return Result.ok().data("scoreList", score_exchangeList);
    }

    @ApiOperation(value = "删除用户一条兑换记录", notes = "根据id删除用户一条积分兑换记录")
    @ApiImplicitParam(name = "id", value = "用户id", required = true, dataType = "Integer")
    @RequestMapping(value = "/score/deleteScoreExchange/{id}", method = RequestMethod.DELETE)
    public Result deleteScoreExchange(@PathVariable("id") Integer id) {
        if (id == null) {
            return Result.error();
        }
        int rs = scoretableService.deleteScoreExchange(id);
        return Result.ok();
    }

    @ApiOperation(value = "删除用户所有积分兑换记录", notes = "根据账户删除用户所有积分兑换记录")
    @ApiImplicitParam(name = "username", value = "用户账号", required = true, dataType = "String")
    @RequestMapping(value = "/score/deleteUserScoreExchange/{username}", method = RequestMethod.DELETE)
    public Result deleteUserScoreExchange(@PathVariable("username") String username) {
        if (username == null) {
            return Result.error();
        }
        int rs = scoretableService.deleteUserScoreExchange(username);
        return Result.ok();
    }


}
