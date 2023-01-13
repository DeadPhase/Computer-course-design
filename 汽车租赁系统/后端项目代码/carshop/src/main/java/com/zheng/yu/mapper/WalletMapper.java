package com.zheng.yu.mapper;


import com.zheng.yu.pojo.Paylog;
import com.zheng.yu.pojo.Wallet;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface WalletMapper {

    //为新注册的用户设置一个钱包
    int addCustomerWaller(Wallet wallet);

    //查询当前登录用户的钱包
    Wallet querywallet(String username);

    //增加充值记录
    int addPaylog(Paylog paylog);

    //增加充值的金额
    int updateWallet(Wallet wallet);

    //查看个人充值记录
    List<Paylog> findPayLog(String username);

    //删除个人充值记录
    int deletePayLog(Integer pid);
}
