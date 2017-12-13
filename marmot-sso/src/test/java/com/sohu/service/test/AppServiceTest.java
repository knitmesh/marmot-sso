/*
* Copyright (c) 2016 Sohu TV. All rights reserved.
*/
package com.sohu.service.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.sohu.dao.UserDao;
import com.sohu.model.App;
import com.sohu.model.Group;
import com.sohu.model.User;
import com.sohu.service.AppService;
import com.sohu.service.UserService;

/**
 * <P>
 * Description:
 * </p>
 * @author wenbozhang
 * @version 1.0
 * @Date 2016年3月1日上午10:34:35
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/spring-mvc.xml"})
public class AppServiceTest {
    private final Logger logger= LoggerFactory.getLogger(this.getClass());

    @Autowired
    private AppService appService;

    @Autowired
    private UserDao UserDao;

    @Autowired
    private UserService UserService;

    @Test
    public void changeAppStateTest(){
        appService.changeAppState(3,0);
    }

    @Test
    public void getAppListTest() {
        List<App> apps=appService.getAppList(0);
        System.out.println("apps.size()="+apps.size());
        for (App app : apps) {
            logger.info(app.toString());
        }
    }

    @Test
    public void insertAppPageTest(){
     /*  User user= UserDao.findUserByName("3");
       System.out.println(user.toString());*/
       /* appService.insertAppPage(2,"127.0.0.1/page/1","3");*/
    }

    @Test
    public void updateUserAppidTest(){
        UserDao.updateUserAppid(3, 1);
    }

    @Test
    public void getGroupByAppidTest(){
        List<Group> groups=appService.getGroupByAppid(1);
        for (Group group : groups) {
            logger.info(group.toString());
        }
    }

    @Test
    public void deleteGroupTest(){
        appService.deleteGroup(20);
    }
    @Test
    public void findUserTest(){
        List<User> lists=UserService.findUser(15, 2);
        for (User user : lists) {
            logger.info(user.toString());
        }
    }
    @Test
    public void insertAdminTest(){
        boolean flag=appService.insertAdmin("6", 15);
        System.out.println(flag);
    }
    @Test
    public void findGroupsByIdTest(){
        String[] urls=UserService.findGroupsById(4, 16);
        for (String string : urls) {
            System.out.println(string);
        }
    }
}
