package com.zyz.bookshopmanage.config.handler;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
/**
 * @author zyz
 * @version 1.0
 * @data 2022/9/9 10:33
 */
@Data
public class BusinessException extends RuntimeException{

    @ApiModelProperty(value="状态码")
    private Integer code;

    @ApiModelProperty(value="错误信息")
    private String errMsg;
}
