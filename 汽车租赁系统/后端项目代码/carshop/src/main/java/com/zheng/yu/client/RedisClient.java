package com.zheng.yu.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.exceptions.JedisException;
import java.io.IOException;

//创建redis 客户端工具类
@Component
public class RedisClient {
    private static final Logger LOGGER = LoggerFactory.getLogger(RedisClient.class);
    public <T> T invoke(JedisPool pool, RedisClientInvoker<T> clients) {
        T obj = null;
        Jedis jedis = null;
        boolean broken = false;
        try {
            jedis = pool.getResource();
            obj = clients.invoke(jedis);
        } catch (JedisException | IOException ex) {
            LOGGER.error(ex.getMessage(), ex);
        } finally {
            if (jedis != null) {
                if (jedis.isConnected())
                    jedis.close();
            }
        }
        return obj;
    }
}
