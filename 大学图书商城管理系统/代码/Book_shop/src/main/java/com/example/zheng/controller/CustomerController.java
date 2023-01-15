package com.example.zheng.controller;

import com.example.zheng.Utils.CurrentTime;
import com.example.zheng.Utils.UUIDutils;
import com.example.zheng.mapper.CustomerMapper;
import com.example.zheng.pojo.*;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.SecurityUtils;
import org.mybatis.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


import java.util.List;

import static java.lang.System.out;


@Controller
public class CustomerController {

//    private static Logger logger = Logger.getLogger(CustomerController.class);
    
    @Autowired
    private CustomerMapper customerMapper;

    //跳转到修改信息界面
    @RequestMapping("/cusomer/toUpdatePwd")
    public String  toUpdatePassword(){
        return "updatePassword";
    }

    //增加用户信息
    @RequestMapping("/customer/toregister")
    public String toRegister(String usercount,String name,String pwd,String sex,String age,String email,String tel,String address){
        Customer customer = new Customer();
        customer.setUsercount(usercount);
        customer.setName(name);
        customer.setPassword(pwd);
        customer.setTel(tel);
        customer.setSex(sex);
        customer.setAge(age);
        customer.setEmail(email);
        customer.setAddress(address);
        customer.setIs_delete(0);


        //用户赋予角色
        String crId = UUIDutils.getUUID();
        CustomerRole customerRole = new CustomerRole(crId,usercount,"R003");//注册的新用户默认是普通用户
        customerMapper.addCustomerRole(customerRole);

        //用户赋予钱包
        String wId = UUIDutils.getUUID();
        Wallet wallet = new Wallet(wId,0.00,usercount);
        customerMapper.addCustomerWaller(wallet);


        try {
         int rs =  customerMapper.addCustomer(customer);
         if(rs > 0){
             return "userLogin";
         }else{
             //注册失败、跳转到注册页
             return "userRegister";
         }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "userRegister";
    }


    @Mylog(value = "修改密码")  //这里添加了AOP的自定义注解
    //修改密码
    @RequestMapping("/customer/updatePassword")
    public String UpdatePassword(String password){
        //拿到当前用户的信息
        Customer parent  = (Customer) SecurityUtils.getSubject().getPrincipal();
        String pwd = password;
        String usercount = parent.getUsercount();
        try {
            Customer customer = new Customer();
            customer.setUsercount(usercount);
            customer.setPassword(pwd);
            customerMapper.updatePassword(customer);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "newIndex";//直接返回登录页
    }



    //进入个人信息界面
    @RequestMapping("/customer/toCustomerIndex")
    public  String toCustomerIndex(){
        return "customerIndex";
    }

    @Mylog(value = "获取个人信息")  //这里添加了AOP的自定义注解
    @RequestMapping("/customer/info")
    public String queryCustomer(Model model){
        try {
            //拿到当前用户的信息
            Customer parent  = (Customer) SecurityUtils.getSubject().getPrincipal();
            Customer customerList = customerMapper.queryById(parent.getUsercount());
            if(customerList != null){
                model.addAttribute("customerList",customerList);
                return "TestCustomer";
            }else{
                return "TestCustomer";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "TestCustomer";
    }



    //物理删除用户
    @RequestMapping("/customer/delete")
    public String deleteCustomer(String usercount,Model model){
        try {
            int rs = customerMapper.deleteCustomer(usercount);
            if(rs > 0){
                model.addAttribute("删除成功");
                return "redirect:/customer";
            }else{
                model.addAttribute("删除失败");
                return "redirect:/customer";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "redirect:/customer";
    }

    //逻辑删除
    @RequestMapping("/customer/logicalDelete")
    public String logicalDeleteCustomer(String usercount,Model model){
        try {
            int rs = customerMapper.logicalDeleteCustomer(usercount);
            if(rs > 0){
                model.addAttribute("删除成功");
                return "redirect:/customer";
            }else{
                model.addAttribute("删除失败");
                return "redirect:/customer";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "redirect:/customer";
    }


//========================用户地址==========================

    @RequestMapping("/customer/address")
    public String addressList( Model model){

        try {
            Hvaddress hvaddress = new Hvaddress();
            //拿到当前用户的信息
            Customer parent  = (Customer) SecurityUtils.getSubject().getPrincipal();
            String usercount = parent.getUsercount();
            hvaddress.setUsercount(usercount);
            List<Hvaddress> list = customerMapper.addressList(hvaddress);
            if(list == null){
                model.addAttribute("error","地址列表为空");
            }else{
                model.addAttribute("addressList",list);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "address";

    }


    //修改用户的收货地址、首先查询出用户的信息
    @RequestMapping("/customer/toupdateAddress")
    public String updateAddress(Model model,String haId){
        Hvaddress hvaddress = new Hvaddress();
        Hvaddress hvaddress1 = new Hvaddress();
        hvaddress.setHaId(haId);

        try {
            List<Hvaddress> hvaddressList = customerMapper.addressList(hvaddress);
            hvaddress1 = hvaddressList.get(0);//取出查询到的数据

            if(hvaddress1 != null){
                model.addAttribute("address",hvaddress1);
                return "updateAddress";
            }else{
                return "redirect:/customer/address";
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


        return "redirect:/customer/address";
    }

    //修改用户的信息
    @RequestMapping("/customer/updateAddress")
    public String update(String getHaId,String getTel,String getName,String getAddress,Model model){

        //拿到当前用户的信息
        Customer parent  = (Customer) SecurityUtils.getSubject().getPrincipal();
        String usercount = parent.getUsercount();

        Hvaddress hvaddress = new Hvaddress(getHaId,usercount, getName, getAddress, getTel);

        try {
           int rs =  customerMapper.updateAddress(hvaddress);
           if(rs > 0){
               model.addAttribute("success");
               return "redirect:/customer/address";
           }else{
               model.addAttribute("failure");
               return "redirect:/customer/address";
           }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/customer/address";
    }

    //首先需要跳转到增加用户的界面
    @RequestMapping("/customer/toAddAddress")
    public String toAddAddress(){
        return "addAddress";
    }

    @Mylog(value = "增加收货地址")  //这里添加了AOP的自定义注解
    //增加用户的收货地址
    @RequestMapping("/customer/addAddress")
    public String addAddress(Model model,String getTel,String getName,String getAddress){

        //拿到当前用户的信息
        Customer parent  = (Customer) SecurityUtils.getSubject().getPrincipal();
        String usercount = parent.getUsercount();
        String getHaId = UUIDutils.getUUID();//随机生成一个主键
        Hvaddress hvaddress = new Hvaddress(getHaId,usercount, getName, getAddress, getTel);

        try {
            int rs =customerMapper.addAddress(hvaddress);

            if(rs > 0){
                model.addAttribute("success");
                return "redirect:/customer/address";
            }else{
                model.addAttribute("failure");
                return "redirect:/customer/address";

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/customer/address";
    }


//返回首页
    @RequestMapping("/customer/toIndexCustomer")
    public String toIndex(){
        return "customerIndex";
    }


    @Mylog(value = "删除收货地址")  //这里添加了AOP的自定义注解
    //删除用户的收货地址
    @RequestMapping("/customer/deleteAddress")
    public String deleteAddress(String haId,Model model){

        try {
         int rs =   customerMapper.deleteAddress(haId);
         if(rs > 0){
             model.addAttribute("success");
             return "redirect:/customer/address";
         }else{
             model.addAttribute("failure");
         }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "redirect:/customer/address";
    }



    /**
     * ===================充值记录===========================
     */

    @RequestMapping("/customer/paylog")
    public String paylogList(@RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum, Model model){
        //拿到当前用户的信息
        Customer parent  = (Customer) SecurityUtils.getSubject().getPrincipal();

        try {
             List<Paylog> list = customerMapper.paylogList(parent.getUsercount());//根据当前的对象查询出该用户的充值记录
             PageInfo<Paylog> pageInfo = new PageInfo<Paylog>(list);
             model.addAttribute("pageInfo",pageInfo);

             if(list == null){

                 model.addAttribute("error","查询的充值记录位空");
             }else{
                 model.addAttribute("paylogList",list);
             }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "paylogIndex";
    }


    @Mylog(value = "删除充值记录")  //这里添加了AOP的自定义注解
    //删除充值的记录
    @RequestMapping("/customer/deletePaylog")
    public String deletePaylog(String pId,Model model){

        try {
          int rs =  customerMapper.deletePayLog(pId);
          if(rs > 0){
              model.addAttribute("success","删除成功");
          }else{
              model.addAttribute("error","删除失败");
          }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/customer/paylog";
    }



    /**
     *====================钱包的管理=======================
     */

    @RequestMapping("/customer/wallet")
    public String toWallet(Model model){

        //拿到当前用户的信息
        Customer parent  = (Customer) SecurityUtils.getSubject().getPrincipal();
        try {
          Wallet wallet =  customerMapper.querywallet(parent.getUsercount());
          if(wallet != null){
              model.addAttribute("wallet",wallet);
              model.addAttribute("success","查询钱包余额成功");
          }else{
              model.addAttribute("error","查询钱包余额失败");
          }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "walletIndex";

    }

    //先跳转到充值记录页面/customer/torecharge
    @RequestMapping("/customer/torecharge")
    public String toRecharge(){
        return "recharge";
    }


    @Mylog(value = "充钱")  //这里添加了AOP的自定义注解
    //办理充值业务
    @RequestMapping("/customer/recharge")
    public String recharge(int money,Model model){
        //拿到当前用户的信息
        Customer parent  = (Customer) SecurityUtils.getSubject().getPrincipal();
        String usercount = parent.getUsercount();
        String Pid =UUIDutils.getUUID();
        String time = CurrentTime.getCurrentTime();

        //从数据库中查出当前用户的金额
        Wallet wallet =  customerMapper.querywallet(parent.getUsercount());
        Double emoney =wallet.getBalance();//
        Double newMoney = emoney+money;//将新的金额设置位剩余金额

        try {
           int rss =  customerMapper.updateWallet(newMoney,usercount);//金额增加

            Paylog paylog = new Paylog(Pid, usercount, money, time);//记录充值的金额和日期
           int rs = customerMapper.addPaylog(paylog);
           if(rs > 0){

               model.addAttribute("success","增加记录成功");
           }else{
               model.addAttribute("failuer","增加记录失败");
           }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "redirect:/customer/wallet";

    }






}
