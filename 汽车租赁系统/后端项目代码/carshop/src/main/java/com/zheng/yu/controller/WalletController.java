package com.zheng.yu.controller;


import com.zheng.yu.config.FormNoTypeEnum;
import com.zheng.yu.config.handler.BusinessException;
import com.zheng.yu.config.response.Result;
import com.zheng.yu.config.response.ResultCode;
import com.zheng.yu.pojo.Paylog;
import com.zheng.yu.pojo.Scoretable;
import com.zheng.yu.pojo.User;
import com.zheng.yu.pojo.Wallet;
import com.zheng.yu.pojo.order.Order_item;
import com.zheng.yu.pojo.order.Order_shipping;
import com.zheng.yu.service.ScoretableService;
import com.zheng.yu.service.WalletService;
import com.zheng.yu.utils.CurrentTime;
import com.zheng.yu.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

@RestController
public class WalletController {

    @Autowired
    WalletService walletService;

    @Autowired
    ScoretableService scoretableService;

    @RequestMapping(value = "/wallet/querywallet", method = RequestMethod.GET)
    public Result querywallet(HttpServletRequest request) {
        String token =request.getHeader("Authorization");
        String username = JWTUtil.getUsername(token);
        Wallet wallet = walletService.querywallet(username);//查询账户余额

//        Scoretable scoretable = scoretableService.findOneScoreById(username);//查询积分

        HashMap<String, Object> result = new HashMap<>();
        result.put("wallet",wallet);
//        result.put("scoretable",scoretable);

         return Result.ok().data("result", result);

    }

    @RequestMapping(value = "/wallet/updateWallet", method = RequestMethod.POST)
    public Result updatewallet(@RequestBody Wallet wallet) {

        //查出顾客余额
       Double premoney = wallet.getPremoney();//钱包中的钱
        Double moneyPayLog = wallet.getMoney();
       Double aftermoney = premoney + wallet.getMoney();
       wallet.setMoney(aftermoney);

       int rs = walletService.updateWallet(wallet);
        Paylog paylog = new Paylog();
        String pay_time = CurrentTime.getCurrentTime();
        paylog.setUsername(wallet.getUsername());
        paylog.setMoney(moneyPayLog);
        paylog.setPay_time(pay_time);

        int rs1 = walletService.addPaylog(paylog);
        return Result.ok();
    }

    @RequestMapping(value = "/wallet/deletePayLog/{pid}", method = RequestMethod.DELETE)
    public Result deletePayLog(@PathVariable("pid") Integer pid) {
        int rs = walletService.deletePayLog(pid);
        if (rs > 0) {
            return Result.ok();
        } else {
            throw new BusinessException(ResultCode.DELETE_USER_ERROR.getCode(),
                    ResultCode.DELETE_USER_ERROR.getMessage());
        }

    }

    @RequestMapping(value = "/wallet/findPayLog", method = RequestMethod.GET)
    public Result findPayLog(HttpServletRequest request){
        String token =request.getHeader("Authorization");
        String username = JWTUtil.getUsername(token);
        System.out.println(username);

        List<Paylog> paylogList = walletService.findPayLog(username);

        int totals = paylogList.size();
        HashMap<String, Object> result = new HashMap<>();

        result.put("paylogList", paylogList);
        result.put("totals", totals);
        return Result.ok().data("result", result);


    }







}
