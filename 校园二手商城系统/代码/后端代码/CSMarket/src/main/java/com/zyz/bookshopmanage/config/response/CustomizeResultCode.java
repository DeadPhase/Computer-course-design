package com.zyz.bookshopmanage.config.response;

/**
 * @author zyz
 * @version 1.0
 * @data 2022/9/9 10:33
 */
public interface CustomizeResultCode {
    /**
     * 获取错误状态码
     * @return 错误状态码
     */
    Integer getCode();

    /**
     * 获取错误信息
     * @return 错误信息
     */
    String getMessage();
}
