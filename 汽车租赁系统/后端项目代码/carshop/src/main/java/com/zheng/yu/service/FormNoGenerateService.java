package com.zheng.yu.service;

import com.zheng.yu.config.FormNoTypeEnum;

/**
 * 单号生成接口
 *
 * @author mq
 */
public interface FormNoGenerateService {

    /**
     * 根据单据编号类型 生成单据编号
     *
     * @param formNoTypeEnum 单据编号类型
     * @author mengqiang
     * @date 2019/1/1
     */
    String generateFormNo(FormNoTypeEnum formNoTypeEnum);
}

