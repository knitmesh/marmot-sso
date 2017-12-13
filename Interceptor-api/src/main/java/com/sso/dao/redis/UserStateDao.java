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
 * @Date 2016年2月25日上午11:26:45
 */
@Repository
public class UserStateDao {
    @Autowired
    private JedisPoolFactory jedisPool;

    public void setUser(long userid) {
        Jedis jedis = null;
        try {
            String key = "user:state:" + userid;
            jedis = JedisPoolFactory.getStaticJedisPool().getResource();
            jedis.setnx(key, "1");
        } finally {
            jedis.close();
        }

    }

    public boolean getUser(long userid) {
        Jedis jedis = null;
        try {
            String key = "user:state:" + userid;
            jedis = JedisPoolFactory.getStaticJedisPool().getResource();
            String state=jedis.get(key);
            if("".equals(state)||state==null){
                return false;
            }else{
                return true;
            }
            
        } finally {
            jedis.close();
        }
    }

    public void deteleUser(long userid){
        Jedis jedis = null;
        try {
            String key = "user:state:" + userid;
            jedis = JedisPoolFactory.getStaticJedisPool().getResource();
            jedis.del(key);
        } finally {
            jedis.close();
        }
    }
}
