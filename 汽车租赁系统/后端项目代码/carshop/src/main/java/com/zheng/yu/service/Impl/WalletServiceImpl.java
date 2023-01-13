package com.zheng.yu.service.Impl;

import com.zheng.yu.mapper.WalletMapper;
import com.zheng.yu.pojo.Paylog;
import com.zheng.yu.pojo.Wallet;
import com.zheng.yu.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WalletServiceImpl implements WalletService {

    @Autowired
    WalletMapper walletMapper;

    //增加用户钱包
    public int addCustomerWaller(Wallet wallet) {
        return walletMapper.addCustomerWaller(wallet);
    }

    //查询当前用户
    public Wallet querywallet(String username) {
        return walletMapper.querywallet(username);
    }

    //增加充值记录
    public int addPaylog(Paylog paylog) {
        return walletMapper.addPaylog(paylog);
    }

    //更新个人钱包
    public int updateWallet(Wallet wallet) {
        return walletMapper.updateWallet(wallet);
    }

    //查看个人的充值记录
    public List<Paylog> findPayLog(String username) {
        return walletMapper.findPayLog(username);
    }

    //删除个人的充值记录
    public int deletePayLog(Integer pid) {
        return walletMapper.deletePayLog(pid);
    }
}
