/*
 * Copyright (c) 2016 Sohu TV. All rights reserved.
 */
package com.sohu.test;

import java.util.ResourceBundle;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.sso.dao.redis.UrlDao;

/**
 * <P>
 * Description:
 * </p>
 *
 * @author wenbozhang
 * @version 1.0
 * @Date 2016年2月25日下午2:45:27
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/spring-dao.xml"})
public class Demo1 {
    @Autowired
    private UrlDao UrlDao;
    @Test
    public void test()  {
        ResourceBundle resource = ResourceBundle.getBundle("app");
        String appid = resource.getString("appid");
        System.out.println(appid);
    }
    @Test
    public void UrlDaoTest(){
        UrlDao.deteleTicket(4);
    }
}
