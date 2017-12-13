/*
* Copyright (c) 2015 Sohu TV. All rights reserved.
*/
package com.sohu.service.test;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.sohu.dao.redis.PtokenDao;
import com.sohu.model.User;


/**
 * <P>
 * Description:
 * </p>
 * @author wenbozhang
 * @version 1.0
 * @Date 2015年12月21日下午2:04:20
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/spring-dao.xml"})
public class UserServiceTest {
    public static Session session;

    @Autowired
    HibernateTemplate template;

    @Autowired
    private SessionFactory sessionfactory;

    @Test
    public void test2(){
       User user= template.get(User.class, 1L);
       System.out.println(user.toString());
    }

    @Test
    public void test3(){
        PtokenDao PtokenDao=new PtokenDao();
        PtokenDao.setPtoken(1,"1");
        PtokenDao.setPtoken(1,"2");
        boolean f=PtokenDao.getPtoken(1, "1");
        System.out.println(f);
    }
    @Test
    public void test4(){
        Session session=sessionfactory.openSession();
        String hql="UPDATE User u SET u.userState=:state WHERE  u.userId=:id";
        Query queryupdate=session.createQuery(hql).setInteger("state",3).setLong("id", 7);
        queryupdate.executeUpdate();
       /* String sql="UPDATE tb_user u SET u.user_state=5 WHERE  u.user_id=7;";
        session.createSQLQuery(sql);*/
    }
}
