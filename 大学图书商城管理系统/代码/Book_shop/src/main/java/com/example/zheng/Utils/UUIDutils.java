package com.example.zheng.Utils;


import java.util.UUID;

//用来生成唯一id
public class UUIDutils {

    public static String getUUID(){
        String uuid = UUID.randomUUID().toString();
        return uuid;

    }
}
