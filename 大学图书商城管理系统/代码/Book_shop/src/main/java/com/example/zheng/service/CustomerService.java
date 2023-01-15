package com.example.zheng.service;

import com.example.zheng.pojo.*;

import java.util.List;

public interface CustomerService {

    //用户和角色建立联合表
    int addCustomerRole(CustomerRole customerRole);

    //查询所有
    List<Customer> queryCustomerList();

    //通过id查询
    Customer queryById(String id);

    //添加
    int addCustomer(Customer customer);

    //修改
    int updateCustomer(Customer customer);

    //修改密码
    int updatePassword(Customer customer);

    //物理删除
    int deleteCustomer(String id);

    //逻辑删除
    int logicalDeleteCustomer(String id);

    /**
     * ===================收货地址============================
     */

    //增加收获地址
    int addAddress(Hvaddress hvaddress);

    //删除收获地址
    int deleteAddress(String id);

    //修改收获地址
    int updateAddress(Hvaddress hvaddress);

    //查看所有的收获地址
    List<Hvaddress> addressList(Hvaddress hvaddress);


    /**
     * ===================充值记录===========================
     */

    //根据账户查询充值记录
    List<Paylog> paylogList(String usercount);

    //删除充值记录
    int deletePayLog(String wId);


    /**
     *====================钱包的管理=======================
     */

    //为新注册的用户设置一个钱包
    int addCustomerWaller(Wallet wallet);

    //查询当前登录用户的钱包
    Wallet querywallet(String usercount);

    //增加充值记录
    int addPaylog(Paylog paylog);

    //增加充值的金额
    int updateWallet(Double emoney,String usercount);


}
