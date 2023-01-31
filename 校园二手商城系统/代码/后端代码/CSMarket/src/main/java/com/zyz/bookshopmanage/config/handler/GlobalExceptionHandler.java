package com.zyz.bookshopmanage.config.handler;


import com.zyz.bookshopmanage.config.response.Result;
import com.zyz.bookshopmanage.config.response.ResultCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
/**
 * @author zyz
 * @version 1.0
 * @data 2022/9/9 10:33
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 全局异常处理，没有指定异常的类型，不管什么类型的异常都是可以捕获的
     * @param e
     * @return
     */
    /**
     *
     *只要出现异常，就会走这个类
     * 返回json数据的字符串
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result error(Exception e){
        log.error(e.getMessage());
        return Result.error();

    }

    @ExceptionHandler(ArithmeticException.class)
    @ResponseBody
    public Result error(ArithmeticException e){
        log.error(e.getMessage());
        return Result.error().code(ResultCode.ARITHM_ETICEXCEPTION.getCode())
                .message(ResultCode.ARITHM_ETICEXCEPTION.getMessage());
    }

    @ExceptionHandler(BusinessException.class)
    @ResponseBody
    public Result error(BusinessException e){
        log.error(e.getErrMsg());
        return Result.error().code(e.getCode())
                .message(e.getErrMsg());

    }
}
