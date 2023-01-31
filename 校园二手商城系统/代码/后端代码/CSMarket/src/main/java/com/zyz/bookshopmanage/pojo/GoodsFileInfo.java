package com.zyz.bookshopmanage.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author Lenovo
 * @version 1.0
 * @data 2022/10/11 19:52
 */
@Data
@TableName("goods_file_info")
public class GoodsFileInfo {
    @TableId(type = IdType.AUTO)
    private Long id;

    private String originName;

    private String fileName;

}
