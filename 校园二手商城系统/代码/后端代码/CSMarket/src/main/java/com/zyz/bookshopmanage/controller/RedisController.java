package com.zyz.bookshopmanage.controller;


import com.zyz.bookshopmanage.config.response.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author zyz
 * @version 1.0
 * @data 2022/12/5 14:18
 * @Description:
 */
@RestController
public class RedisController {

    @Autowired
    private RedisTemplate redisTemplate;

    //六位数验证码
    public static String getRandom() {
        Random random = new Random();
        String code = "";
        for (int i = 0; i < 6; i++) {
            int rand = random.nextInt(10);
            code += rand;
        }
        return code;


    }

    /**
     * 发送手机号、获取验证码
     *
     * @return
     */
    @RequestMapping(value = "/user/getCode", method = RequestMethod.GET)
    public Result getVerifyCode(@RequestParam Map<String, Object> maps) {
        String phone = (String) maps.get("phone");
        //手机发送次数key
        String countKey = "verifyCode" + phone + "count";
        //验证码key
        String codeKey = "verifyCode" + phone + "code";
        //每个手机1小时发送三次
        Integer count = (Integer) redisTemplate.opsForValue().get(countKey);

        if (count == null) {
            //第一次发送
            redisTemplate.opsForValue().set(countKey, 1, 60 * 60, TimeUnit.SECONDS);
            System.out.println("第几次发送:" + count);

        } else if (count <= 2) {
            Integer nums = ++count;
            redisTemplate.opsForValue().set(countKey, nums,  60 * 60, TimeUnit.SECONDS);
            System.out.println("第几次发送:" + count);

        } else {
            System.out.println("请一个小时后再次尝试");
            return Result.error().data("errMessage", "请一个小时后再次尝试");
        }
        //发送验证码到redis中
        String vcode = getRandom();
        System.out.println("生成的验证码是：" + vcode);
        redisTemplate.opsForValue().set(codeKey, vcode, 120, TimeUnit.SECONDS);
        return Result.ok().data("vcode", vcode);

    }

    /**
     * 验证验证码
     *
     * @return
     */
    @RequestMapping(value = "/user/verifyCode", method = RequestMethod.GET)
    public Result verifyCode(@RequestParam Map<String, Object> maps) {
        String phone = (String) maps.get("phone");
        String code = (String) maps.get("code");
        //验证码的key
        String codeKey = "verifyCode" + phone + "code";
        String redisCode = (String) redisTemplate.opsForValue().get(codeKey);

        //判断
        if (redisCode.equals(code)) {
            return Result.ok();
        } else {
            return Result.error();
        }

    }


    @GetMapping(value = "/getName")
    public String testRedis() {
        //设置值到redis
        redisTemplate.opsForValue().set("name", "lucy");
        //从redis获取值
        String name = (String) redisTemplate.opsForValue().get("name");
        return name;
    }
}


