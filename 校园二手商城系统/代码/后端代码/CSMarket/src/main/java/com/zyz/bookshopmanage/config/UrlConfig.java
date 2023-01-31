package com.zyz.bookshopmanage.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author Lenovo
 * @version 1.0
 * @data 2022/10/12 18:58
 */
@Configuration
public class UrlConfig implements WebMvcConfigurer {
    private static final String BASE_PATH = System.getProperty("user.dir") + "/src/main/resources/static/file/";

    /**
     *  * 资源映射路径
     *  * addResourceHandler：访问映射路径
     *  * addResourceLocations：资源绝对路径
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/images/**").addResourceLocations("file:"+BASE_PATH);

    }

}

