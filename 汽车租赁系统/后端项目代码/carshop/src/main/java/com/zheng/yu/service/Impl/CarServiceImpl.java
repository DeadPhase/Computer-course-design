package com.zheng.yu.service.Impl;

import com.zheng.yu.mapper.CarMapper;
import com.zheng.yu.pojo.Car;
import com.zheng.yu.pojo.CarType;
import com.zheng.yu.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CarServiceImpl implements CarService {

    @Autowired
    CarMapper carMapper;

  //查看汽车库存数量
    @Override
    public int findCarNum(Integer id) {
        return carMapper.findCarNum(id);
    }

    //修改库存数量
    @Override
    public int updateCarNum(Integer car_id, Integer car_num) {
        return carMapper.updateCarNum(car_id,car_num);
    }

    //    上下架
    public int updateCarStatus(Integer car_id, Integer status) {
        return carMapper.updateCarStatus(car_id,status);
    }

    //增加车辆
    public int addCar(Car car) {
        return carMapper.addCar(car);
    }

    //逻辑删除车辆信息，下架
    public int deleteCarLogical(Integer id) {
        return carMapper.deleteCarLogical(id);
    }

    //修改车辆信息
    public int updateCar(Car car) {
        return carMapper.updateCar(car);
    }

    //根据id查询某一个车辆的信息
    public Car findOneCar(Integer id) {
        return carMapper.findOneCar(id);
    }

    //查询所有的车辆
    public List<Car> carList() {
        return carMapper.carList();
    }

    //分页查询汽车信息
    public List<Car> findCarListPage(Map<String, Integer> map) {
        return carMapper.findCarListPage(map);
    }

    @Override
    public List<Car> findCarListPageByUser(Map<String, Integer> map) {
        return carMapper.findCarListPageByUser(map);
    }


    //查询总数
    public Long findCarTotals() {
        return carMapper.findCarTotals();
    }

   //用户浏览汽车查询总数
    public Long persionFindCarTotals() {
        return carMapper.persionFindCarTotals();
    }


    //条件查询
    public List<Car> findSomeCar(Car car) {
        return carMapper.findSomeCar(car);
    }

    //顾客查询汽车
    public List<Car> findSomeCarByUser(Car car) {
        return carMapper.findSomeCarByUser(car);
    }


    //汽车类型
    public List<CarType> findCarType() {
        return carMapper.findCarType();
    }

    public int addCarType(CarType carType) {
        return carMapper.addCarType(carType);
    }

//    查看是否已经存在汽车类型
    public CarType isExit(String type) {
        return carMapper.isExit(type);
    }

    public int deleteCarType(Integer id) {
        return carMapper.deleteCarType(id);
    }

    public int updateCarType(CarType carType) {
        return carMapper.updateCarType(carType);
    }


}
