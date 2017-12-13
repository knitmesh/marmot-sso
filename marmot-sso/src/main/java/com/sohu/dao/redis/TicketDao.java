/*
 * Copyright (c) 2016 Sohu TV. All rights reserved.
 */
package com.sohu.dao.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import redis.clients.jedis.Jedis;

/**
 * <P>
 * Description:
 * </p>
 *
 * @author wenbozhang
 * @version 1.0
 * @Date 2016年2月25日上午10:50:27
 */
@Repository
public class TicketDao {
    @Autowired
    private JedisPoolFactory jedisPool;

    public void setTicket(long userid, String ticket) {
        Jedis jedis = null;
        try {
            String key = "user:ticket:" + userid;
            jedis = JedisPoolFactory.getStaticJedisPool().getResource();
            int timeout = 60 * 2;
            jedis.setex(key, timeout,ticket);
        } finally {
            jedis.close();
        }

    }

    public boolean getTicket(long userid,String ticket) {
        Jedis jedis = null;
        try {
            String key = "user:ticket:" + userid;
            jedis = JedisPoolFactory.getStaticJedisPool().getResource();
            String JedisTicket=jedis.get(key);
            if(ticket.equals(JedisTicket)){
                return true;
            }else{
                return false;
            }
        } finally {
            jedis.close();
        }
    }

    public void deteleTicket(long userid){
        Jedis jedis = null;
        try {
            String key = "user:ticket:" + userid;
            jedis = JedisPoolFactory.getStaticJedisPool().getResource();
            jedis.del(key);
        } finally {
            jedis.close();
        }
    }
}
