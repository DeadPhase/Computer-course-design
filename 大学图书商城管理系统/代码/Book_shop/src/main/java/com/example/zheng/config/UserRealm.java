package com.example.zheng.config;


import com.example.zheng.pojo.Authority;
import com.example.zheng.pojo.Customer;
import com.example.zheng.pojo.Role;

import com.example.zheng.service.AuthorityService;
import com.example.zheng.service.CustomerService;
import com.example.zheng.service.RoleService;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;


//自定义的UserRealm

public class UserRealm extends AuthorizingRealm {

    @Autowired
    CustomerService customerService;


    @Autowired
    RoleService roleService;

    @Autowired
    AuthorityService authorityService;


    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
//        System.out.println("执行了授权");
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        //拿到当前的登录对象
        Subject subject = SecurityUtils.getSubject();
        Customer user = (Customer)subject.getPrincipal();
        //设置当前用户的权限
        Collection<String> perms = new HashSet<String>();//设置集合
        //查询当前用户的角色
        Set<Role> roleSet =roleService.queryRole(user.getUsercount());
        //迭代器迭代集合元素 遍历
        Iterator<Role> iterator = roleSet.iterator();
        while(iterator.hasNext()){
            Role role1 =iterator.next();
            Set<Authority> authoritySet=authorityService.queryAuthority(role1.getRoleId());//查询当前角色具有的权限
            for(Authority authority : authoritySet){
                perms.add(authority.getAuthName());//将每一个当前用户的权限加入
            }
        }

        info.addStringPermissions(perms);
        return info;



    }

    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

//        System.out.println("执行了认证");
        UsernamePasswordToken userToken = (UsernamePasswordToken) token;
        //连接真实的数据库
        Customer customer = customerService.queryById(userToken.getUsername());

        //1、思路：在用户表中新增加一个权限字段、然后再次查询数据库获得该用户所具有的权限（该权限为要给集合？？？？？？
        if(customer == null){
            //没有这个人
            return null;
        }
        //密码认证
        return  new SimpleAuthenticationInfo(customer,customer.getPassword(),"");

    }
}
