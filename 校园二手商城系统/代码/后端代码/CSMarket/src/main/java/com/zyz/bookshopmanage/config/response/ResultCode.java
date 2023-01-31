package com.zyz.bookshopmanage.config.response;


/**
 * @author zyz
 * @version 1.0
 * @data 2022/9/9 10:33
 */
public enum ResultCode implements CustomizeResultCode {
    /**
     * 200:操作成功
     */
    SUCCESS(200,"成功"),

    /**
     * 9999：响应失败
     */
    ERROR(9999,"失败"),
    /**
     * 6666：密码不正确
     */
    PWD_NOT_CORRECT(6666,"输入的密码不正确！请重新尝试！"),

    /**
     * 6667：尚未登录
     */
    NOT_LOGIN(6667,"尚未登录"),

    /**
     * 6668：算数异常
     */
    ARITHM_ETICEXCEPTION(6668,"算数异常"),



    /**
     * 404：没有找到对应的请求路径
     */
    PAGE_NOT_FOUND(404,"请求的页面飞向了外太空。。。"),

    /**
     * 500：服务端异常
     */
    INTERNAL_SERVER_ERROR(500,"服务器冒烟了。。。等下访问"),

    /**
     * 201 ：未知异常
     */
    UNKNOW_SERVER_ERROR(201,"未知异常，请联系管理员")
    ;
    private Integer code;
    private String message;

    ResultCode(Integer code,String message){
        this.code=code;
        this.message=message;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
