package com.zheng.yu.controller;


import com.zheng.yu.config.handler.BusinessException;
import com.zheng.yu.config.response.Result;
import com.zheng.yu.config.response.ResultCode;
import com.zheng.yu.pojo.Car;
import com.zheng.yu.pojo.CarType;
import com.zheng.yu.service.CarService;
import com.zheng.yu.utils.CurrentTime;
import com.zheng.yu.utils.UUIDutilsu;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Api(value = "汽车管理模块", tags = "汽车管理接口")
@ApiModel
public class CarController {

    @Value("${pictureFile.path}")
    private String picturePath;

    @Value("${pictureFile.path-mapping}")
    private String picturePath_mapping;

    @Autowired
    CarService carService;

    @RequestMapping(value = "/car/updatecarstatus/{car_id}/{status}", method = RequestMethod.PUT)
    public Result updateCarStatus(@PathVariable("car_id") Integer car_id,@PathVariable("status") Integer status) {

        int rs = carService.updateCarStatus(car_id,status);
        if (rs > 0) {
            return Result.ok();
        } else {
            throw new BusinessException(ResultCode.UPDATE_CAR_ERROR.getCode(),
                    ResultCode.UPDATE_CAR_ERROR.getMessage());
        }

    }


    @PostMapping("/upload/img")
    public Result upload(MultipartFile file) {
        String fileName = file.getOriginalFilename();  // 文件名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));  // 后缀名
        fileName = UUIDutilsu.getUUID() + suffixName; // 新文件名
        File dest = new File(picturePath + fileName);
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        try {
            file.transferTo(dest);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String final_fileName = "http://localhost:8282" + picturePath_mapping + fileName;
        System.out.println(final_fileName);
        return Result.ok().data("final_fileName",final_fileName);
    }

    @RequestMapping("/fileUpload")
    public Result fileUpload(@RequestParam("file") MultipartFile upload) throws IOException {
        System.out.println("接收到了");
        if (upload.isEmpty()) {
            System.out.println("文件为空空");
        }
        String path = "F:\\BiShe\\upload";
        File file = new File(path);
        if(!file.exists()){
            file.mkdirs();
        }

        //获取上传文件的后缀名
        String fileType  =upload.getOriginalFilename().substring(upload.getOriginalFilename().indexOf("."));
        // 获取上传文件的名称
        //String filename = upload.getOriginalFilename();
        // 把文件的名称设置唯一值，uuid
        String uuid = UUIDutilsu.getUUID().toString().replace("-", "");
        String filename = uuid+fileType;
        String absoultepath= path+"\\"+filename;

        System.out.println(absoultepath);

        try {
            upload.transferTo(new File(path,filename));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return Result.ok().data("absoultepath",absoultepath);
    }

    @ApiOperation(value = "添加汽车", notes = "添加汽车")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Car")
    })
    @RequestMapping(value = "/car/addcar", method = RequestMethod.POST)
    public Result addCar(@RequestBody Car car) {
        car.setStatus(1);//新增加的商品默认是上架商品
        String date = CurrentTime.getCurrentTime();
        car.setDate(date);
        int rs = carService.addCar(car);
        if (rs > 0) {
            return Result.ok();
        } else {
            throw new BusinessException(ResultCode.ADD_CAR_ERROR.getCode(),
                    ResultCode.ADD_CAR_ERROR.getMessage());
        }

    }

    @ApiOperation(value = "下架汽车", notes = "根据id下架汽车")
    @ApiImplicitParam(name = "id", value = "车辆id", required = true, dataType = "Integer")
    @RequestMapping(value = "/car/deletecarlogical/{id}", method = RequestMethod.DELETE)
    public Result deleteCarLogical(@PathVariable("id") Integer id) {
        int rs = carService.deleteCarLogical(id);
        if (rs > 0) {
            return Result.ok();
        } else {
            throw new BusinessException(ResultCode.DELETE_CAR_LOGICAL_ERROR.getCode(),
                    ResultCode.DELETE_CAR_LOGICAL_ERROR.getMessage());
        }
    }

    @RequestMapping(value = "/car/editCar", method = RequestMethod.PUT)
    public Result updateOneUser(@RequestBody Car car) {
        int rs = carService.updateCar(car);
        if (rs > 0) {
            return Result.ok();
        } else {
            throw new BusinessException(ResultCode.UPDATE_USER_ERROR.getCode(),
                    ResultCode.UPDATE_USER_ERROR.getMessage());
        }

    }

    @ApiOperation(value = "查询某一个汽车", notes = "根据id查询某一个汽车的信息")
    @ApiImplicitParam(name = "id", value = "用户id", required = true, dataType = "Integer")
    @RequestMapping(value = "/car/findonecar/{id}", method = RequestMethod.GET)
    public Result findOneCar(@PathVariable("id") Integer id) {
        Car car = carService.findOneCar(id);
        if (car != null) {
            return Result.ok().data("car", car);
        } else {
            throw new BusinessException(ResultCode.FIND_ONE_CAR_ERROR.getCode(),
                    ResultCode.FIND_ONE_CAR_ERROR.getMessage());
        }
    }


    @ApiOperation(value = "查询所有汽车", notes = "查询所有的汽车")
    @RequestMapping(value = "/car/carlist", method = RequestMethod.GET)
    public Result carList() {
        List<Car> carList = carService.carList();
        if (carList != null) {
            return Result.ok().data("carList", carList);
        } else {
            throw new BusinessException(ResultCode.FIND_ALL_CAR_ERROR.getCode(),
                    ResultCode.FIND_ALL_CAR_ERROR.getMessage());
        }
    }

    @ApiOperation(value = "汽车信息分页", notes = "根据传入的页码和页面大小查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "当前页", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "size", value = "当前页大小", required = true, dataType = "Integer")
    }
    )
    @RequestMapping(value = "/car/findAll/{page}/{size}", method = RequestMethod.GET)
    public Result findAll(@PathVariable("page") Integer Page, @PathVariable("size") Integer size) {

        //准备数据 通过这两个参数，可以算出start   计算方法 start=size(page-1)
        int currentPage = Page;//当前是第几页
        int pageSize = size; //页面大小
        Map<String, Integer> map = new HashMap<String, Integer>();
        map.put("startIndex", (currentPage - 1) * pageSize);
        map.put("pageSize", pageSize);
        List<Car> carList = carService.findCarListPage(map);
        HashMap<String, Object> result = new HashMap<>();
        Long totals = carService.findCarTotals();
        result.put("cars", carList);
        result.put("totals", totals);
        return Result.ok().data("result", result);

    }

    //carList

    @RequestMapping(value = "/car/findAllCar", method = RequestMethod.GET)
    public Result findAllCar() {
        List<Car> carList = carService.carList();//查询所有上架的汽车
        HashMap<String, Object> result = new HashMap<>();
        Long totals = carService.persionFindCarTotals();
        result.put("cars", carList);
        result.put("totals", totals);
        return Result.ok().data("result", result);

    }

    @RequestMapping(value = "/car/findAllByUser/{page}/{size}", method = RequestMethod.GET)
    public Result findAllByUser(@PathVariable("page") Integer Page, @PathVariable("size") Integer size) {

        //准备数据 通过这两个参数，可以算出start   计算方法 start=size(page-1)
        int currentPage = Page;//当前是第几页
        int pageSize = size; //页面大小
        Map<String, Integer> map = new HashMap<String, Integer>();
        map.put("startIndex", (currentPage - 1) * pageSize);
        map.put("pageSize", pageSize);
        List<Car> carList = carService.findCarListPageByUser(map);
        HashMap<String, Object> result = new HashMap<>();
        Long totals = carService.persionFindCarTotals();
        result.put("cars", carList);
        result.put("totals", totals);
        return Result.ok().data("result", result);

    }

    //查询某一部分汽车
    @ApiOperation(value = "条件查询", notes = "条件查询")
    @ApiImplicitParams(
            @ApiImplicitParam(name = "Car")
    )
    @RequestMapping(value = "/car/findsomecar", method = RequestMethod.POST)
    public Result findSomeUser(@RequestBody Car car) {
        List<Car> carList = carService.findSomeCar(car);
        HashMap<String, Object> result = new HashMap<>();
        Integer totals = carList.size();
        result.put("cars", carList);
        result.put("totals", totals);
        return Result.ok().data("result", result);

    }

    @RequestMapping(value = "/car/findsomecarbyuser", method = RequestMethod.POST)
    public Result findSomeByUser(@RequestBody Car car) {
        List<Car> carList = carService.findSomeCarByUser(car);
        HashMap<String, Object> result = new HashMap<>();
        Integer totals = carList.size();
        result.put("cars", carList);
        result.put("totals", totals);
        return Result.ok().data("result", result);

    }

    @RequestMapping(value = "/car/addcartype", method = RequestMethod.POST)
    public Result addCarType(@RequestBody CarType carType) {

        CarType isExitCarType = carService.isExit(carType.getType());

        if(isExitCarType !=null){
            return Result.error().data("errMessage","该汽车类型已经存在！！！");
        }else{
            int rs = carService.addCarType(carType);
            if(rs > 0){
                return Result.ok();
            }else{
                return Result.error();
            }


            }

        }





    @RequestMapping(value = "/car/deletecartype/{id}", method = RequestMethod.DELETE)
    public Result deleteCarType(@PathVariable("id") Integer id) {
        int rs = carService.deleteCarType(id);
        if (rs > 0) {
            return Result.ok();
        } else {
            return Result.error();
        }
    }

    @RequestMapping(value = "/car/updatecartype", method = RequestMethod.PUT)
    public Result updateCarType(@RequestBody CarType carType) {
        CarType isExitCarType = carService.isExit(carType.getType());
        if(isExitCarType !=null){
            return Result.error().data("errMessage","该汽车类型已经存在！！！");}
        int rs = carService.updateCarType(carType);
        if (rs > 0) {
            return Result.ok();
        } else {
            return Result.error();
        }

    }

    @RequestMapping(value = "/car/cartypelist", method = RequestMethod.GET)
    public Result findCarType() {
        List<CarType> carList = carService.findCarType();
        if (carList != null) {
            return Result.ok().data("cartypeList", carList);
        } else {
            return Result.error();
        }
    }



}
