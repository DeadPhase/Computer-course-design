package com.zheng.yu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@SpringBootApplication(exclude = {HibernateJpaAutoConfiguration.class})
public class YuApplication {

    public static void main(String[] args) {
        SpringApplication.run(YuApplication.class, args);
    }


}
