package com.zheng.yu;


import com.zheng.yu.config.FormNoTypeEnum;
import com.zheng.yu.pojo.Car;
import com.zheng.yu.service.CarService;
import com.zheng.yu.service.FormNoGenerateService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Slf4j
public class TestCar {

    @Autowired
    CarService carService;

    @Autowired
    private FormNoGenerateService formNoGenerateService;

    /**
     * 单号生成测试
     */
    @Test
    public void getOrder(){
    String formNO =    formNoGenerateService.generateFormNo(FormNoTypeEnum.YF_ORDER);
        System.out.println(formNO);
    }

//    //测试增加汽车
//    @Test
//    void testAddCar(){
//        Car car = new Car();
//        car.setName("大众");
//        car.setColor("白色");
//        car.setGuarantee_money(500.00);
//        car.setPrice(1000.00);
//        car.setType("轿车");
//        car.setStatus(1);
//        int rs = carService.addCar(car);
//
//        int a = 4;
//    }

    //逻辑删除汽车
    @Test
    void testDeleteCarLogical(){
        int rs = carService.deleteCarLogical(1);
    }

    //查询所有的汽车
    @Test
    void testFindAllCar(){
        List<Car> carList = carService.carList();
        int rs = carList.size();
    }

    //根据id查询某一个用户
    @Test
    void testFindOneCar(){
        Car car = carService.findOneCar(1);
        int a = 4;
    }

    //修改
    @Test
    void testUpdateCar(){
        Car car = new Car();
        car.setCar_id(1);
        car.setName("大众");
        car.setColor("白色");
        car.setGuarantee_money(500.00);
        car.setType("轿车");
        car.setStatus(1);
        int rs = carService.updateCar(car);
    }
}
