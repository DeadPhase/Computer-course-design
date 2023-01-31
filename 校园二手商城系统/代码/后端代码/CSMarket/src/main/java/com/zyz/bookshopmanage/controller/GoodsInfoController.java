package com.zyz.bookshopmanage.controller;



import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.zyz.bookshopmanage.config.response.Result;
import com.zyz.bookshopmanage.mapper.GoodsInfoMapper;
import com.zyz.bookshopmanage.mapper.TypeInfoMapper;
import com.zyz.bookshopmanage.pojo.Admin;
import com.zyz.bookshopmanage.pojo.GoodsInfo;
import com.zyz.bookshopmanage.pojo.TypeInfo;
import com.zyz.bookshopmanage.service.GoodsInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Lenovo
 * @version 1.0
 * @data 2022/10/11 15:46
 */
@RestController
public class GoodsInfoController {



    @Autowired
    GoodsInfoMapper goodsInfoMapper;

    @Autowired
    GoodsInfoService goodsInfoService;

    @Autowired
    TypeInfoMapper typeInfoMapper;

    /**
     * 查询化妆品种类
     * @return
     */
    @RequestMapping(value = "/goodsInfo/getTypeInfo", method = RequestMethod.GET)
    public Result getTypeInfoOption() {
        List<TypeInfo> typeInfoList = null;
        try{
            typeInfoList =typeInfoMapper.selectList(null);
        }catch (Exception e){
            e.printStackTrace();
        }
        if (typeInfoList != null) {
            return Result.ok().data("goodsInfoListList", typeInfoList);
        } else {
            return Result.error();
        }
    }

    /**
     * 增加商品
     * @param goodsInfo
     * @return
     */
    @RequestMapping(value = "/goodsInfo/addGoodsInfo", method = RequestMethod.POST)
    public Result addGoodsInfo(@RequestBody GoodsInfo goodsInfo){
        int result = 0;
        if(goodsInfo == null){
            return Result.error().data("errMessage","信息不能为空！");
        }

        //判断是否已添加商品
        Map<String, Object> map = new HashMap<>();
        map.put("name", goodsInfo.getName());
        List<GoodsInfo> goodsInfoList = goodsInfoMapper.selectByMap(map);

        if(goodsInfoList.size() > 0){
            return Result.error().data("errMessage","该商品已经添加！");
        }else{
            result = goodsInfoMapper.insert(goodsInfo);
        }

        if(result > 0){
            return Result.ok();
        }else{
            return Result.error().data("errMessage","添加失败！");
        }

    }

    /**
     * 使用分页查询插件
     */
    @RequestMapping(value = "/goodsInfo/findAllGoodsInfo", method = RequestMethod.GET)
    public Result pagingQuery(@RequestParam Map<String,Object> map){

        Integer currentPage = Integer.parseInt(map.get("page").toString());
        Integer pageSize =  Integer.parseInt(map.get("size").toString());

        Page<GoodsInfo> goodsInfos = new Page<>(currentPage, pageSize);
        goodsInfoMapper.selectPage(goodsInfos,null);
        List<GoodsInfo> goodsInfoList = goodsInfos.getRecords();
        Long totals = goodsInfos.getTotal();
        HashMap<String, Object> result = new HashMap<>();
        result.put("goodsInfoList",goodsInfoList);
        result.put("totals",totals);
        return Result.ok().data("result",result);

    }

    /**
     * 修改上架状态 正整数
     * @param map
     * @return
     */
    @RequestMapping(value = "/goodsInfo/updateSaleStatus", method = RequestMethod.PUT)
    public Result updateSaleStatus(@RequestBody Map<String,Object> map){
        int result = 0;
        if(map == null){
            return Result.error().data("errMessage","修改失败");
        }
        int id = Integer.parseInt(map.get("myId").toString());
        String isSale = map.get("myIsSale").toString();

        GoodsInfo goodsInfo = new GoodsInfo();
        goodsInfo.setIsSale(isSale);
        System.out.println(goodsInfo);

        UpdateWrapper<GoodsInfo> gfWrapper =new UpdateWrapper<>();
        gfWrapper.like("id",id);
        gfWrapper.set("is_sale",isSale);
        try{
            result = goodsInfoMapper.update(null,gfWrapper);;
        }catch (Exception e){
            e.printStackTrace();
        }
        if(result > 0){
            return Result.ok();
        }else{
            return Result.error().data("errMessage","修改失败");

        }


    }

    /**
     * 删除商品信息
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/goodsInfo/deleteGoodsInfo", method = RequestMethod.DELETE)
    public Result deleteGoodsInfo(@RequestParam  Map<String,Object> maps) {
        Integer id = Integer.parseInt(maps.get("id").toString());

        HashMap<String, Object> map = new HashMap<>();
        map.put("id", id);
        int rs = goodsInfoMapper.deleteByMap(map);

        if (rs > 0) {
            return Result.ok();
        } else {
            return Result.error().data("errMessage", "删除商品信息失败");
        }

    }

    /**
     * 修改商品信息
     *
     * @param goodsInfo
     * @return
     */
    @RequestMapping(value = "/goodsInfo/updateGoodsInfo", method = RequestMethod.PUT)
    public Result upDateGoodsInfo(@RequestBody GoodsInfo goodsInfo) {

        if (goodsInfo == null) {
            return Result.error().data("errMessage", "信息为空");
        }
        System.out.println(goodsInfo);

        UpdateWrapper<GoodsInfo> updateGoodsWrapper = new UpdateWrapper<>();
        updateGoodsWrapper.like("id", goodsInfo.getId());
        int rs = goodsInfoMapper.update(goodsInfo, updateGoodsWrapper);
        if (rs > 0) {
            return Result.ok();
        } else {
            return Result.error().data("errMessage", "商品信息修改失败");
        }



    }

    /**
     * 查询商品展示信息
     * @return
     */
    @RequestMapping(value = "/goodsInfo/getInfo", method = RequestMethod.GET)
    public Result findGoosIngo(){
        List<GoodsInfo> goodsInfoList = goodsInfoService.goodsInfoList();
        if(goodsInfoList != null){
            HashMap<String, Object> result = new HashMap<>();
            result.put("list",goodsInfoList);
            return Result.ok().data("result",result);
        }else {
            return Result.error();
        }
    }

    /**
     * 查询商品的详情信息
     * @return
     */
    @RequestMapping(value = "/goodsInfo/GoodsDetail",method = RequestMethod.GET)
    public Result findGoodsDetail(@RequestParam Map<String,Object> map){
        Integer id = Integer.parseInt(map.get("id").toString());
        GoodsInfo goodsInfo = goodsInfoMapper.selectById(id);
        if(goodsInfo == null){
            return Result.error().data("errMessage","查询商品信息不存在");
        }else{
            HashMap<String, Object> result = new HashMap<>();
            result.put("goodsDetail",goodsInfo);
            return Result.ok().data("result",result);
        }

    }



}
