package com.zyz.bookshopmanage.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author Lenovo
 * @version 1.0
 * @data 2022/10/10 21:19
 */
@Data
@TableName("type_info")
public class TypeInfo {
    private int id;
    private String name;
    private String description;
}
