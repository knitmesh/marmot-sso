/**
* @Title: AppServiceImpl.java
* @Package com.sohu.service.impl
* @author wenbozhang
* @date 2016年2月29日 下午8:06:23
* @version V1.0
*/
/*
* Copyright (c) 2016 Sohu TV. All rights reserved.
*/
package com.sohu.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sohu.dao.AppDao;
import com.sohu.dao.UserDao;
import com.sohu.model.App;
import com.sohu.model.Group;
import com.sohu.model.User;
import com.sohu.service.AppService;

/**
 * @ClassName: AppServiceImpl
 * @author wenbozhang
 * @date 2016年2月29日 下午8:06:23
 *
 */
@Service
public class AppServiceImpl  implements AppService{
    @Autowired
    private AppDao appDao;
    @Autowired
    private UserDao userDao;

    @Override
    @Transactional
    public void applyApp(App app, long userid) {
        appDao.applyApp(app);
       /* userDao.updateUserState(userid,3);*/
    }

    @Override
    public List<App> getAppList(int state) {
        return appDao.getAppList(state);
    }

    @Override
    public void changeAppState(long appid,int state) {
        appDao.changeAppState(appid, state);
        App app=appDao.getAppById(appid);
        if(state==1){//请过审批之后,申请的用户会变成开发者的角色
            long userid=app.getOwnerId();
            userDao.updateUserState(userid, 3);
            userDao.updateUserAppid(userid, appid);
        }
    }

    @Override
    public App findAppState(long userid) {
        User user=userDao.getUserById(userid);
        App app=appDao.getAppById(user.getAppId());
        return app;
    }

    @Override
    public List<Group> getGroupByAppid(long appid) {
        return appDao.getGroupByAppid(appid);
    }

    @Override
    public void insertGroup(String groupUrl, long appid) {
        appDao.insertAppPage(appid, groupUrl);
    }

    @Override
    @Transactional
    public void deleteGroup(long groupid) {
        appDao.deleteGroup(groupid);
        appDao.deleteUserGroup(groupid);
    }

    @Override
    public boolean insertAdmin(String adminName,long appid) {
        User admin=userDao.findUserByName(adminName);
        System.out.println("==admin=="+admin);
        if(admin!=null){
            if(admin.getAppId()==0){
                admin.setUserState(2);
                admin.setAppId(appid);
                userDao.updateUser(admin);
                return true;
            }else{
                return false;
            }
        }else{
            return false;
        }
    }

    @Override
    public void deleteAdmin(long userid) {
       User user=userDao.getUserById(userid);
       user.setUserState(1);
       user.setAppId(0);
       userDao.updateUser(user);
    }

    @Override
    public int addUserRight(long[] groups, String name,long appid) {
       User user=userDao.findUserByName(name);
       if(user==null){
           return 1;
       }
       long userid=user.getUserId();
       return appDao.addUserRight(groups, userid,appid);
    }

}
