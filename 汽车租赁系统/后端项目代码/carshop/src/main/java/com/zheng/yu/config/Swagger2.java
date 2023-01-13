package com.zheng.yu.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 放在与application.java同级目录下
 * @Configuration 让spring来加载该类配置
 * @EnableSwagger2 来启用Swagger2
 */

@Configuration
@EnableSwagger2
public class Swagger2 {

    @Bean
    public Docket createRestApi() {

        /**
         * 创建API应用
         * apiInfo()增加API相关信息
         * 通过select()函数返回一个ApiSelectorBuilder实例，用来控制哪些接口暴漏给Swagger来展现
         * 本例采用指定扫描的包路径来定义指定要建立API的目录
         */

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.zheng.yu.controller"))
                .paths(PathSelectors.any()) // 可以根据url路径设置哪些请求加入文档，忽略哪些请求
                .build();
    }

    /**
     *创建该API的基本信息（基本信息展示在文档页面中）
     * 访问地址：http://项目实际地址/swagger-ui.html
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("红灯笼网上汽车租赁系统") //设置文档的标题
                .description("红灯笼网上汽车租赁系统的接口设计") // 设置文档的描述
                .version("1.0.0") // 设置文档的版本信息-> 1.0.0 Version information
                .termsOfServiceUrl("http://8282/") // 设置文档的License信息->1.3 License information
                .build();
    }
}
