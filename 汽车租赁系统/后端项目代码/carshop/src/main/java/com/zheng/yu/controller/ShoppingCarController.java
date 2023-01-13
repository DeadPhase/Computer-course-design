package com.zheng.yu.controller;


import com.zheng.yu.config.handler.BusinessException;
import com.zheng.yu.config.response.Result;

import com.zheng.yu.pojo.Shopping_car;
import com.zheng.yu.service.ShoppingCarService;
import com.zheng.yu.utils.JWTUtil;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController

@Api(value = "购物车管理模块", tags = "购物车管理接口")
@ApiModel
public class ShoppingCarController {

    @Autowired
    ShoppingCarService shoppingCarService;

    @ApiOperation(value = "添加汽车到购物车", notes = "添加汽车")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Shopping_car")
    })
    @RequestMapping(value = "/shoppingcar/addShoppingCar", method = RequestMethod.POST)
    public Result addCar(HttpServletRequest request, @RequestBody Shopping_car shopping_car) {
        String token =request.getHeader("Authorization");
        String username = JWTUtil.getUsername(token);
        shopping_car.setUsername(username);

        int rs = shoppingCarService.addShoppingCar(shopping_car);

        return Result.ok();


    }

    @ApiOperation(value = "删除租赁汽车", notes = "根据id删除租赁汽车")
    @ApiImplicitParam(name = "id", value = "车辆id", required = true, dataType = "Integer")
    @RequestMapping(value = "/shoppingcar/deleteShoppingCar/{id}", method = RequestMethod.DELETE)
    public Result deleteShoppingCar(@PathVariable("id") Integer id) {
        int rs = shoppingCarService.deleteShoppingCar(id);
        return Result.ok();

    }

    @ApiOperation(value = "修改购物车的商品信息", notes = "根据id修改购物车的信息")
    @ApiImplicitParams(
            @ApiImplicitParam(name = "Shopping_car")
    )
    @RequestMapping(value = "/shoppingcar/updateShoppingCar", method = RequestMethod.PUT)
    public Result updateShoppingCar(@RequestBody Shopping_car shopping_car) {
        int rs = shoppingCarService.updateShoppingCar(shopping_car);
        return Result.ok();
    }


    @ApiOperation(value = "购物车商品信息分页", notes = "根据传入的页码和页面大小查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "当前页", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "size", value = "当前页大小", required = true, dataType = "Integer")
    }
    )
    @RequestMapping(value = "/shoppingcar/findShoppingCarByName/{username}", method = RequestMethod.GET)
    public Result findShoppingCarByName(@PathVariable("username")String username) {

        List<Shopping_car> shoppingCarList = shoppingCarService.findShoppingCarByName(username);
        HashMap<String, Object> result = new HashMap<>();
        int totals = shoppingCarList.size();
        result.put("shoppingcars", shoppingCarList);
        result.put("totals", totals);
        return Result.ok().data("result", result);

    }


}
