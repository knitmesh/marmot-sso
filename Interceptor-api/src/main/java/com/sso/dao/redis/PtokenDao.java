/*
* Copyright (c) 2016 Sohu TV. All rights reserved.
*/
package com.sso.dao.redis;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import redis.clients.jedis.Jedis;

/**
 * <P>
 * Description:
 * </p>
 * @author wenbozhang
 * @version 1.0
 * @Date 2016年2月25日上午11:34:15
 */
@Repository
public class PtokenDao {
    @Autowired
    private JedisPoolFactory jedisPool;

    public void setPtoken(long userid, String ptoken) {
        Jedis jedis = null;
        try {
            String key = "user:ptoken:" + userid;
            jedis = JedisPoolFactory.getStaticJedisPool().getResource();
            jedis.sadd(key, ptoken);
        } finally {
            jedis.close();
        }

    }

    public boolean getPtoken(long userid,String ptoken) {
        Jedis jedis = null;
        try {
            String key = "user:ptoken:" + userid;
            jedis = JedisPoolFactory.getStaticJedisPool().getResource();
            Set<String> set=jedis.smembers(key);
            if(set.size()==0){
                return false;
            }
            List<String> list=new ArrayList<String>();
            list.addAll(set);
            for (String s : list) {
                if(s.equals(ptoken)){
                    return true;
                }
            }
            return false;
        } finally {
            jedis.close();
        }
    }

    public void detelePtoken(long userid){
        Jedis jedis = null;
        try {
            String key = "user:ptoken:" + userid;
            jedis = JedisPoolFactory.getStaticJedisPool().getResource();
            jedis.del(key);
        } finally {
            jedis.close();
        }
    }
}
