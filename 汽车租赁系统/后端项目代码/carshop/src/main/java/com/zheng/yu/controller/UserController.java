package com.zheng.yu.controller;

import com.zheng.yu.config.handler.BusinessException;
import com.zheng.yu.config.response.Result;
import com.zheng.yu.config.response.ResultCode;
import com.zheng.yu.pojo.User;
import com.zheng.yu.pojo.Wallet;
import com.zheng.yu.service.Impl.TokenService;
import com.zheng.yu.service.UserService;
import com.zheng.yu.service.WalletService;
import com.zheng.yu.utils.CurrentTime;
import com.zheng.yu.utils.JWTUtil;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
//@RequestMapping("")
@Api(value = "系统用户模块", tags = "系统用户接口")
@ApiModel
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    TokenService tokenService;

    @Autowired
    WalletService walletService;

    /**
     * 登录方法

     * @return ModelAndView
     */
//    @RequestMapping("/login")
    @RequestMapping(value = "/user/login", method = RequestMethod.POST)
    public Result login(@RequestBody User user)  {
        System.out.println(user.getName()+" "+user.getPassword());

        User userForBase=userService.getUserByUsername(user.getName(), user.getPassword());

        System.out.println("------------------------------"+userForBase);

        if(userForBase != null){
            String token =  JWTUtil.createToken(user.getName());
//            System.out.println(token);
            HashMap<String, Object> result = new HashMap<>();
            result.put("token",token);
            result.put("vip",userForBase.getVip());
            return Result.ok().data("result",result);
        }else{
            String token = null;
            return Result.error().data("token",token);
        }


    }



    @ApiOperation(value = "用户注册", notes = "用户注册")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "User")
    })
    @Transactional(rollbackFor = Exception.class)//设置检查时异常时回滚事务
    @RequestMapping(value = "/user/register", method = RequestMethod.POST)
    public Result register(@RequestBody User user) throws Exception  {
        //注册前查看是否已经有用户注册

        User rss = userService.findUserRegister(user.getCount());

        if(rss!=null ){//已经存在用户
            return Result.error();
        }else{
            String time = CurrentTime.getCurrentTime();
            user.setTime(time);
            user.setVip(1);//默认是普通用户
            int rs = userService.register(user);


            if (rs > 0) {
//                生成钱包
                Wallet wallet = new Wallet();
                wallet.setUsername(user.getName());
                wallet.setMoney(0.00);
                int rs1 = walletService.addCustomerWaller(wallet);
                return Result.ok();

            } else {
                throw new BusinessException(ResultCode.USER_REGISTER_ERROR.getCode(),
                        ResultCode.USER_REGISTER_ERROR.getMessage());

            }
        }





    }


    @ApiOperation(value = "用户信息分页", notes = "根据传入的页码和页面大小查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "当前页", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "size", value = "当前页大小", required = true, dataType = "Integer")
    }
    )
    @RequestMapping(value = "/user/findAll/{page}/{size}", method = RequestMethod.GET)
    public Result findAll(@PathVariable("page") Integer Page, @PathVariable("size") Integer size) {

        //准备数据 通过这两个参数，可以算出start   计算方法 start=size(page-1)
        int currentPage = Page;//当前是第几页
        int pageSize = size; //页面大小
        Map<String, Integer> map = new HashMap<String, Integer>();
        map.put("startIndex", (currentPage - 1) * pageSize);
        map.put("pageSize", pageSize);
        List<User> userList = userService.queryUserList(map);
        HashMap<String, Object> result = new HashMap<>();
        Long totals = userService.findUserTotals();
        result.put("users", userList);
        result.put("totals", totals);
        return Result.ok().data("result", result);

    }


    @ApiOperation(value = "查询所有用户", notes = "查询所有的用户")
    @RequestMapping(value = "/user/alluser", method = RequestMethod.GET)
    public Result findAll() {
        List<User> list = userService.userList();
        return Result.ok().data("users", list);
    }

    @RequestMapping(value = "/user/findOneUser", method = RequestMethod.GET)
    public Result findOneUser(HttpServletRequest request ){
        String token =request.getHeader("Authorization");
        String username = JWTUtil.getUsername(token);
        User user = userService.findOneUser(username);
        return Result.ok().data("user",user);

    }



    //根据用户id删除用户信息
    @ApiOperation(value = "根据id删除用户信息", notes = "根据id删除用户信息")
    @ApiImplicitParam(name = "id", value = "用户id", required = true, dataType = "Integer")
    @RequestMapping(value = "/user/deleteoneuser/{id}", method = RequestMethod.DELETE)
    public Result deleteOneUser(@PathVariable("id") Integer id) {
        int rs = userService.deleteOneUser(id);
        if (rs > 0) {
            return Result.ok();
        } else {
            throw new BusinessException(ResultCode.DELETE_USER_ERROR.getCode(),
                    ResultCode.DELETE_USER_ERROR.getMessage());
        }


    }

    //修改用户信息
    @ApiOperation(value = "修改用户的信息", notes = "修改用户的信息")
    @ApiImplicitParams(
            @ApiImplicitParam(name = "User")
    )
    @RequestMapping(value = "/user/updateoneuser", method = RequestMethod.PUT)
    public Result updateOneUser(@RequestBody User user) {
        int rs = userService.updateOneUser(user);
        if (rs > 0) {
            return Result.ok();
        } else {
            throw new BusinessException(ResultCode.UPDATE_USER_ERROR.getCode(),
                    ResultCode.UPDATE_USER_ERROR.getMessage());
        }

    }

    @ApiOperation(value = "修改密码", notes = "根据传入的用户id和新的密码进行密码修改")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户编号", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "pwd", value = "新的密码", required = true, dataType = "Integer")
    }
    )
    @RequestMapping(value = "/user/updatepassword/{id}/{pwd}", method = RequestMethod.PUT)
    public Result updatePassword(@PathVariable("id") Integer id, @PathVariable("pwd") String pwd) {
        Map<String, Object> map = new HashMap();
        map.put("user_id", id);
        map.put("password", pwd);
        int rs = userService.updatePassword(map);

        if (rs > 0) {
            return Result.ok();
        } else {
            throw new BusinessException(ResultCode.UPDATE_PASSWORD_ERROR.getCode(),
                    ResultCode.UPDATE_PASSWORD_ERROR.getMessage());
        }
    }


    //查询某一部分用户
    @ApiOperation(value = "条件查询", notes = "条件查询")
    @ApiImplicitParams(
            @ApiImplicitParam(name = "User")
    )
    @RequestMapping(value = "/user/findsomeuser", method = RequestMethod.POST)
    public Result findSomeUser(@RequestBody User user) {
        System.out.println(user);
        List<User> userList = userService.findSomeUser(user);
        HashMap<String, Object> result = new HashMap<>();
        Integer totals = userList.size();
        result.put("users", userList);
        result.put("totals", totals);
        return Result.ok().data("result", result);

    }


}
