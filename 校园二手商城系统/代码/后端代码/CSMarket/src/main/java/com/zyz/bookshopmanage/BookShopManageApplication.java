package com.zyz.bookshopmanage;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;


/**
 * 扫描mapper接口所在的包
 */
/**
 * @author 静小文    化妆品种类
 * @version 1.0
 * @data 2022/9/9 10:36
 */
@EnableTransactionManagement
@SpringBootApplication
@MapperScan("com.zyz.bookshopmanage.mapper")
public class BookShopManageApplication {


    public static void main(String[] args) {
        SpringApplication.run(BookShopManageApplication.class, args);


    }

}
