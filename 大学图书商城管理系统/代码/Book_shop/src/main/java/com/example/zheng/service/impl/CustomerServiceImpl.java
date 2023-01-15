package com.example.zheng.service.impl;

import com.example.zheng.mapper.CustomerMapper;
import com.example.zheng.pojo.*;
import com.example.zheng.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    CustomerMapper customerMapper;


    //用户和角色建立联合表
    @Override
    public int addCustomerRole(CustomerRole customerRole) {
        return customerMapper.addCustomerRole(customerRole);
    }

    //查询所有的用户信息
    @Override
    public List<Customer> queryCustomerList() {
        return customerMapper.queryCustomerList();
    }

    //根据id查询用户的个人信息
    @Override
    public Customer queryById(String id) {
        return customerMapper.queryById(id);
    }

    //增加一个学生的信息
    @Override
    public int addCustomer(Customer customer) {
        return customerMapper.addCustomer(customer);
    }

    //修改学生的信息
    @Override
    public int updateCustomer(Customer customer) {

        return customerMapper.updateCustomer(customer);
    }

    //修改密码
    @Override
    public int updatePassword(Customer customer) {
        return customerMapper.updatePassword( customer);
    }


    //物理删除
    @Override
    public int deleteCustomer(String id) {
        return customerMapper.deleteCustomer(id);
    }

    //逻辑删除
    @Override
    public int logicalDeleteCustomer(String id) {
        return customerMapper.logicalDeleteCustomer(id);
    }


    /**
     * ===================收货地址============================
     */
    //增加收获地址
    @Override
    public int addAddress(Hvaddress hvaddress) {
        return customerMapper.addAddress(hvaddress);
    }

    //删除收获地址
    @Override
    public int deleteAddress(String id) {
        return customerMapper.deleteAddress(id);
    }

    //修改收获地址
    @Override
    public int updateAddress(Hvaddress hvaddress) {
        return customerMapper.updateAddress(hvaddress);
    }

    //查看所有的收获地址
    @Override
    public List<Hvaddress> addressList(Hvaddress hvaddress) {
        return customerMapper.addressList(hvaddress);
    }



    /**
     * ===================充值记录===========================
     */

    //根据账户查询充值记录
    @Override
    public List<Paylog> paylogList(String usercount) {
        return customerMapper.paylogList(usercount);
    }

    //删除充值记录
    @Override
    public int deletePayLog(String wId) {
        return customerMapper.deletePayLog(wId);
    }




    /**
     *====================钱包的管理=======================
     */

    //    为新注册的用户设置一个钱包
    @Override
    public int addCustomerWaller(Wallet wallet) {
        return customerMapper.addCustomerWaller(wallet);
    }

    //查看钱包的余额
    @Override
    public Wallet querywallet(String usercount) {
        return customerMapper.querywallet(usercount);
    }


    //增加充值记录
    @Override
    public int addPaylog(Paylog paylog) {
        return customerMapper.addPaylog(paylog);
    }


    //增加充值的金额
    @Override
    public int updateWallet(Double emoney, String usercount) {
        return customerMapper.updateWallet(emoney,usercount);
    }
}
