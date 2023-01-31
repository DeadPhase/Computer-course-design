package com.zyz.bookshopmanage.controller;

import com.zyz.bookshopmanage.Utils.CurrentTime;
import com.zyz.bookshopmanage.config.response.Result;
import com.zyz.bookshopmanage.mapper.CartShopInfoMapper;
import com.zyz.bookshopmanage.mapper.GoodsInfoMapper;
import com.zyz.bookshopmanage.pojo.CartShopInfo;
import com.zyz.bookshopmanage.pojo.GoodsInfo;
import com.zyz.bookshopmanage.service.CartShopInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Lenovo
 * @version 1.0
 * @data 2022/11/8 14:20
 */
@RestController
@RequestMapping("/cartshop")

public class CartShopInfoController {
    @Autowired
    GoodsInfoMapper goodsInfoMapper;

    @Autowired
    CartShopInfoMapper cartShopInfoMapper;

    @Autowired
    CartShopInfoService cartShopInfoService;



    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Result add(@RequestBody Map<String, Object> maps) {
        System.out.println(maps);
        Long userId = Long.parseLong(maps.get("userId").toString());
        Integer level = (Integer) maps.get("level");
        Integer count = (Integer) maps.get("count");
        Long goodsId = Long.parseLong(maps.get("goodsId").toString()) ;
        String time = CurrentTime.getCurrentTime();

        //加入之前、判断是否已经加入该商品。
        CartShopInfo cartShopInfo = cartShopInfoService.findCartShopInfoByUserIdGoodsId(userId,goodsId);
        if(cartShopInfo != null){
            return Result.error().data("errMessage","该商品已经加入购物车中");
        }

        CartShopInfo info = new CartShopInfo();
        info.setUserId(userId);
        info.setLevel(level);
        info.setCount(count);
        info.setGoodsId(goodsId);
        info.setCreateTime(time);

        int rs = cartShopInfoMapper.insert(info);

        if (rs > 0) {
            return Result.ok();
        } else {
            return Result.error().data("errMessage", "加入购物车失败");
        }

    }


    @RequestMapping(value = "/find",method = RequestMethod.GET)
    public Result queryCartShopInfo(@RequestParam Map<String,Object> maps){
        List<GoodsInfo> goodsList = new ArrayList<>();
        Long userId = Long.parseLong(maps.get("userId").toString());
        Integer level = Integer.parseInt(maps.get("level").toString()) ;
        List<CartShopInfo> cartShopInfoList = cartShopInfoService.queryCartShopByUserId(userId,level);
        for(CartShopInfo cartShopInfo : cartShopInfoList){
            Long id = cartShopInfo.getGoodsId();
            GoodsInfo goods = goodsInfoMapper.selectById(id);
            if(goods != null){
                Double price = goods.getPrice()*cartShopInfo.getCount()*goods.getDiscount();
                DecimalFormat df = new DecimalFormat("0.00");
                price = Double.valueOf(df.format(price));
                //将购物车中的商品数量
                goods.setCount(cartShopInfo.getCount());
                //这里的id也是购物车中商品id
                goods.setId(cartShopInfo.getGoodsId());
                goods.setTotalPrice(price);

                goodsList.add(goods);
            }
        }

        return Result.ok().data("goodsList",goodsList);
    }


    @RequestMapping(value = "/delete",method = RequestMethod.DELETE)
    public Result deleteCartShopOneInfo(@RequestParam Map<String,Object> maps){
        Long userId = Long.parseLong(maps.get("userId").toString());
        Long goodsId = Long.parseLong(maps.get("goodsId").toString()) ;
        HashMap<String, Object> map = new HashMap<>();
        map.put("userId",userId);
        map.put("goodsId",goodsId);
        int rs = cartShopInfoMapper.deleteByMap(map);
        if(rs > 0){
            return Result.ok();
        }else{
            return Result.error().data("errMessage","删除失败");
        }


    }

}
