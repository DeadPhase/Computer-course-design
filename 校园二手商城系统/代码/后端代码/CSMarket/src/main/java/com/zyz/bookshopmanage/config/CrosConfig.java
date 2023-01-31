package com.zyz.bookshopmanage.config;

import com.zyz.bookshopmanage.config.handler.MyTokenInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ConcurrentTaskExecutor;
import org.springframework.web.servlet.config.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author 静小文
 * @version 1.0
 * @data 2022/9/9 10:36
 */

@Configuration
public class CrosConfig implements WebMvcConfigurer {
    private ExecutorService executorService = null;




    /**
     * 开启跨域
     *
     * @param registry
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        //设置允许跨域的路由
        registry.addMapping("/**")
                .allowedOriginPatterns("*")
                .allowedMethods("GET", "HEAD", "POST", "PUT", "DELETE", "OPTIONS")
                .allowCredentials(true)
                .maxAge(3600)
                .allowedHeaders("*");
    }

    private MyTokenInterceptor tokenInterceptor;

    //构造方法
    public CrosConfig(MyTokenInterceptor tokenInterceptor) {
        this.tokenInterceptor = tokenInterceptor;
    }

    @Override
    public void configureAsyncSupport(AsyncSupportConfigurer configurer) {
        executorService = new ThreadPoolExecutor(2,
                2,
                100,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(2),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy());
        configurer.setTaskExecutor(new ConcurrentTaskExecutor(executorService));
        configurer.setDefaultTimeout(30000);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        List<String> excludePath = new ArrayList<>();
        //排除拦截，除了注册登录(此时还没token)，其他都拦截
        excludePath.add("/user/register");
        excludePath.add("/user/login");
        excludePath.add("/user/userLogin");
        excludePath.add("/user/changePwd");
        excludePath.add("/goodsInfo/getInfo");
        excludePath.add("/goodsInfo/GoodsDetail");
        excludePath.add("/user/isLogin");
        excludePath.add("/cartshop/add");
        excludePath.add("/cartshop/find");
        excludePath.add("/user/updateUserInfo");
        excludePath.add("/cartshop/delete");
        excludePath.add("/order/add");
        excludePath.add("/user/rechargeMoney");
        excludePath.add("/order/findAll");
        excludePath.add("/order/findAllOrderInfo");
        excludePath.add("/order/paymoney");
        excludePath.add("/order/refund");
        excludePath.add("/order/shipments");
        excludePath.add("/order/cancelRefund");
        excludePath.add("/order/onReturnable");
        excludePath.add("/order/onCancelReturnable");
        excludePath.add("/user/findUserById");
        excludePath.add("/images/**");
        excludePath.add("/static/**");
        excludePath.add("/redisTest/getName");
        excludePath.add("/user/getCode");
        excludePath.add("/user/verifyCode");
        excludePath.add("/user/updatePhone");


        registry.addInterceptor(tokenInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns(excludePath);
        WebMvcConfigurer.super.addInterceptors(registry);
    }







}
