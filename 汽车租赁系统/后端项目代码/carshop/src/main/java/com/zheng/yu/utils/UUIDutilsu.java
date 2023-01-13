package com.zheng.yu.utils;


import java.util.UUID;

//用来生成唯一id
public class UUIDutilsu {

    public static String getUUID(){
        String uuid = UUID.randomUUID().toString();
        return uuid;

    }
}
