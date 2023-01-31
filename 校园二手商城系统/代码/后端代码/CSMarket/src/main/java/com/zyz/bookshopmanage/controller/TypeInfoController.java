package com.zyz.bookshopmanage.controller;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.zyz.bookshopmanage.config.response.Result;
import com.zyz.bookshopmanage.mapper.TypeInfoMapper;
import com.zyz.bookshopmanage.pojo.TypeInfo;
import com.zyz.bookshopmanage.service.TypeInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Lenovo
 * @version 1.0
 * @data 2022/10/10 23:11
 */
@RestController
public class TypeInfoController {
    @Autowired
    TypeInfoService typeInfoService;

    @Autowired
    TypeInfoMapper typeInfoMapper;

    /**
     * 增加商品种类
     * @param typeInfo
     * @return
     */
    @RequestMapping(value = "/typeInfo/addTypeInfo", method = RequestMethod.POST)
    public Result addTypeInfo(@RequestBody TypeInfo typeInfo){
        int result = 0;
        if(typeInfo == null){
            return Result.error().data("errMessage","信息不能为空！");
        }
        TypeInfo typeInfo1 = typeInfoService.findByName(typeInfo.getName());

        if(typeInfo1 != null){
            return Result.error().data("errMessage","该商品种类已经添加！");
        }else{
            result = typeInfoMapper.insert(typeInfo);
        }

        if(result > 0){
            return Result.ok();
        }else{
            return Result.error().data("errMessage","添加失败！");
        }

    }

    /**
     * 查询所有化妆品种类
     *
     * @return
     */
    @RequestMapping(value = "/typeInfo/findAllTypeInfo", method = RequestMethod.GET)
    public Result findAllTypeInfo(@RequestParam Map<String ,Object> maps) {
        //准备数据 通过这两个参数，可以算出start   计算方法 start=size(page-1)
        List<TypeInfo> typeInfoList = null;
        Long totals = 0L;
        Integer currentPage = Integer.parseInt(maps.get("page").toString());
        Integer pageSize =  Integer.parseInt(maps.get("size").toString());
        Map<String, Integer> map = new HashMap<String, Integer>();
        map.put("startIndex", (currentPage - 1) * pageSize);
        map.put("pageSize", pageSize);
        try {
            typeInfoList = typeInfoService.findAllTypeInfo(map);
            totals = typeInfoService.findTypeInfoTotals();

        }catch (Exception e){
            e.printStackTrace();
        }

        if(typeInfoList.size() >0){
            HashMap<String, Object> result = new HashMap<>();
            result.put("typeInfoList", typeInfoList);
            result.put("totals", totals);
            return Result.ok().data("result", result);
        }else{
            return Result.error().data("errMessage", "化妆品种类信息为空");
        }



    }

    /**
     * 删除化妆品信息
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/typeInfo/deleteTypeInfo", method = RequestMethod.DELETE)
    public Result deleteTypeInfo(@RequestParam  Map<String,Object> maps) {
        String id = (String) maps.get("id");
        HashMap<String, Object> map = new HashMap<>();
        map.put("id", id);
        int rs = typeInfoMapper.deleteByMap(map);

        if (rs > 0) {
            return Result.ok();
        } else {
            return Result.error().data("errMessage", "删除失败");
        }

    }

    /**
     * 修改用户信息
     *
     * @param typeInfo
     * @return
     */
    @RequestMapping(value = "/typeInfo/updateTypeInfo", method = RequestMethod.PUT)
    public Result updateTypeInfo(@RequestBody TypeInfo typeInfo) {

        if (typeInfo == null) {
            return Result.error().data("errMessage", "信息不能为空");
        }

        UpdateWrapper<TypeInfo> updateUserWrapper = new UpdateWrapper<>();
        updateUserWrapper.like("id", typeInfo.getId());
        int rs = typeInfoMapper.update(typeInfo, updateUserWrapper);
        if (rs > 0) {
            return Result.ok();
        } else {
            return Result.error().data("errMessage", "信息修改失败");
        }


    }


}
