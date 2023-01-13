package com.zheng.yu.client;

import redis.clients.jedis.Jedis;
import java.io.IOException;
//创建redis 客户端接口
public interface RedisClientInvoker<T> {
    T invoke(Jedis jedis) throws IOException;
}

