package com.zheng.yu.mapper;

import com.zheng.yu.pojo.Car;
import com.zheng.yu.pojo.CarType;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface CarMapper {

    int findCarNum(Integer id);//查询汽车数量

    int updateCarNum(Integer car_id,Integer car_num);//修改库存数量

    int updateCarStatus(Integer car_id,Integer status);//上下架

    int addCar(Car car);//增加车辆

    int deleteCarLogical(Integer id);//逻辑删除车辆信息，下架

    int updateCar(Car car);//修改车辆信息

    Car findOneCar(Integer id);//根据id查询某一个车辆的信息

    List<Car> carList();//查询所有的车辆

    List<Car> findCarListPage(Map<String, Integer> map);//分页查询用户信息

    List<Car> findCarListPageByUser(Map<String, Integer> map);//分页查询用户信息


    Long findCarTotals();//查询总数

    Long persionFindCarTotals();//用户浏览汽车查询总数


    List<Car> findSomeCar(Car car);//条件查询

    List<Car> findSomeCarByUser(Car car);//顾客查询

    //汽车类型
    List<CarType> findCarType();//查询汽车类型

    int addCarType(CarType carType);//增加汽车类型

    CarType isExit(String type);//查看是否已经存在汽车类型

    int deleteCarType(Integer id);//删除汽车类型

    int updateCarType(CarType carType);//修改汽车类型





}
