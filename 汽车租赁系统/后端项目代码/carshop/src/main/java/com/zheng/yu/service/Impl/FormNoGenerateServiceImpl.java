package com.zheng.yu.service.Impl;


import com.zheng.yu.config.FormNoTypeEnum;
import com.zheng.yu.controller.FormNoConstants;
import com.zheng.yu.service.FormNoGenerateService;
import com.zheng.yu.utils.FormNoSerialUtil;

import com.zheng.yu.utils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * 单号生成接口实现
 *
 * @author mengqiang
 * @version FormNoGenerateServiceImpl.java, v 1.0 2019-01-01 18:10
 */
@Service
public class FormNoGenerateServiceImpl implements FormNoGenerateService {
    /**
     * redis 服务
     * demo 项目没有加redis相关，若有需要请参考，redis的博客
     */
    @Autowired
    private RedisCache redisCache;
    /**
     * 根据单据编号类型 生成单据编号
     *
     * @param formNoTypeEnum 单据编号类型
     * @author mengqiang
     * @date 2019/1/1
     */
    @Override
    public String generateFormNo(FormNoTypeEnum formNoTypeEnum) {
        //获得单号前缀
        //格式 固定前缀 +时间前缀 示例 ：YF20190101
        String formNoPrefix = FormNoSerialUtil.getFormNoPrefix(formNoTypeEnum);
        //获得缓存key
        String cacheKey = FormNoSerialUtil.getCacheKey(formNoPrefix);
        //获得当日自增数
        Long incrementalSerial = redisCache.incr(cacheKey);
        //设置失效时间 7天
        redisCache.expire(cacheKey, FormNoConstants.DEFAULT_CACHE_DAYS, TimeUnit.DAYS);
        //组合单号并补全流水号
        String serialWithPrefix = FormNoSerialUtil
                .completionSerial(formNoPrefix, incrementalSerial, formNoTypeEnum);
        //补全随机数
        return FormNoSerialUtil.completionRandom(serialWithPrefix, formNoTypeEnum);
    }
}
