package com.example.zheng.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    /**
     * 退出登录
     * @return
     */
    @RequestMapping("/logout")
    public String logout() {
        Subject subject = SecurityUtils.getSubject();
        if (subject != null) {
            subject.logout();
        }
        return "newIndex";
    }



    @RequestMapping("/hello")
    public String hello(Model model){
        model.addAttribute("msg","这里是首页");
        return "newIndex";
    }

    //跳转到用户登录界面
    @RequestMapping("/toLogin")
    public String toLogin(){
        return "userLogin";
    }

    //跳转到用户注册界面
    @RequestMapping("/register")
    public String toRegister(){
        return "userRegister";
    }


    @RequestMapping("/login")
    public String login(String username,String password,Model model){
        //获取当前的用户
        Subject subject= SecurityUtils.getSubject();

        //封装用户的登录数据
        UsernamePasswordToken token= new UsernamePasswordToken(username,password);
        try {
            subject.login(token);//执行登陆的方法，如果没有异常则是正确的
            return "customerIndex";//进入个人信息界面
        } catch (UnknownAccountException e) {
            model.addAttribute("msg","用户名错误");
            return "userLogin";
        }catch (IncorrectCredentialsException e){
            model.addAttribute("msg","密码不存在");
            return "userLogin";

        }
    }

    @RequestMapping("/ungrant")
    public String unGrant(){
        return "un";
    }

    /**
     * 添加书籍
     */
    @RequestMapping("/book/addbook")
    public String addboosk(){
        return "addBook";
    }

}
