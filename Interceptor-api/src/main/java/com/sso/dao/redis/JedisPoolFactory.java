package com.sso.dao.redis;

import java.util.ResourceBundle;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import redis.clients.jedis.JedisPool;

@Repository
public class JedisPoolFactory {

    private final static Logger  logger = LoggerFactory.getLogger(JedisPoolFactory.class);

    private static String REDIS_HOST;
    private static int REDIS_PORT;
    private static int JEDIS_POOL_TIME_OUT;

    /**
     * 加载JedisPool-Conf.properties配置文件中的信息
     */
    static {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("JedisPool-Conf");
        REDIS_HOST = resourceBundle.getString("redis.host");
        REDIS_PORT = Integer.valueOf(resourceBundle.getString("redis.port"));
        JEDIS_POOL_TIME_OUT = Integer.valueOf(resourceBundle.getString("jedis.pooltimeout"));
    }

    /**
     * 静态工厂方法创建JedisPool
     * @return
     */
    public static JedisPool getStaticJedisPool() {
        JedisPool jedisPool = null;
        GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
        poolConfig.setMaxTotal(GenericObjectPoolConfig.DEFAULT_MAX_TOTAL * 5);
        poolConfig.setMaxIdle(GenericObjectPoolConfig.DEFAULT_MAX_IDLE * 3);
        poolConfig.setMinIdle(GenericObjectPoolConfig.DEFAULT_MIN_IDLE * 2);
        poolConfig.setJmxEnabled(true);
        poolConfig.setMaxWaitMillis(3000);
        jedisPool =  new JedisPool(poolConfig, REDIS_HOST, REDIS_PORT, JEDIS_POOL_TIME_OUT);
        logger.info("jedisPool={}",jedisPool);
        return jedisPool;
    }
}
