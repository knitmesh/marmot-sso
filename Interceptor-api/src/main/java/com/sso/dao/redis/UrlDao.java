/*
* Copyright (c) 2016 Sohu TV. All rights reserved.
*/
package com.sso.dao.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import redis.clients.jedis.Jedis;

/**
 * <P>
 * Description:
 * </p>
 * @author wenbozhang
 * @version 1.0
 * @Date 2016年3月2日下午5:14:22
 */
@Repository
public class UrlDao {
    @Autowired
    private JedisPoolFactory jedisPool;

    public void setUrl(long userid, String[] url) {
        Jedis jedis = null;
        try {
            String key = "user:url:"+ userid;
            jedis = JedisPoolFactory.getStaticJedisPool().getResource();
            jedis.sadd(key, url);
        } finally {
            jedis.close();
        }

    }


    public boolean getUrl(long userid,String url) {
        Jedis jedis = null;
        try {
            String key = "user:url:"+ userid;
            jedis = JedisPoolFactory.getStaticJedisPool().getResource();
            return jedis.sismember(key, url);
        } finally {
            jedis.close();
        }
    }

    public void deteleTicket(long userid){
        Jedis jedis = null;
        try {
            String key = "user:url:"+ userid;
            jedis = JedisPoolFactory.getStaticJedisPool().getResource();
            jedis.del(key);
        } finally {
            jedis.close();
        }
    }
}
