/*
 * Copyright (c) 2016 Sohu TV. All rights reserved.
 */
package com.sohu.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import com.sohu.dao.AppDao;
import com.sohu.model.App;
import com.sohu.model.Group;
import com.sohu.model.UserGroup;

/**
 * <P>
 * Description:
 * </p>
 *
 * @author wenbozhang
 * @version 1.0
 * @Date 2016年2月29日下午8:21:14
 */
@Repository
public class AppDaoImpl extends BaseDaoImpl implements AppDao {
    @Override
    public void applyApp(App app) {
        Session session = null;
        try {
            session = getSession();
            session.save(app);
        } finally {
            session.close();
        }
    }

    @Override
    public List<App> getAppList(int state) {
        Session session = null;
        String hql =null;
        Query query = null;
        try {
            session = getSession();
            if(10==state){
                hql="from App";
                query=session.createQuery(hql);
            }else{
                hql="from App a where a.appState=:state";
                query=session.createQuery(hql).setInteger("state",state);
            }
            List<App> list = query.list();
            return list;
        } finally {
            session.close();
        }
    }

    @Override
    public void changeAppState(long appid, int state) {
        Session session = null;
        try {
            session = getSession();
            String hql = "UPDATE App a SET a.appState=:state WHERE  a.appId=:id";
            Query queryupdate = session.createQuery(hql).setLong("id", appid).setInteger("state", state);
            queryupdate.executeUpdate();
        } finally {
            session.close();
        }
    }

    @Override
    public App getAppById(long appid) {
        Session session = getSession();
        App app = (App) session.get(App.class, appid);
        if (app == null) {
            return null;
        }
        session.close();
        return app;
    }

    @Override
    public void insertAppPage(long appid, String groupUrls) {
        Session session = getSession();
        Group group = new Group(groupUrls, appid);
        session.save(group);
        session.close();
    }

    @Override
    public List<Group> getGroupByAppid(long appid) {
        Session session = getSession();
       String hql="from Group g where g.appId=:id";
       Query query = session.createQuery(hql).setLong("id",appid);
       List<Group> list = query.list();
       return list;
    }

    @Override
    public void deleteGroup(long groupid) {
        Session session = null;
        try {
            session = getSession();
            String hql = "delete Group where groupId=:id";
            Query queryupdate = session.createQuery(hql).setLong("id", groupid);
            queryupdate.executeUpdate();
        } finally {
            session.close();
        }
    }

    @Override
    public int addUserRight(long[] groups, long userid,long appid) {
        Session session = getSession();
        Transaction tx = session.beginTransaction();
        try {
            for (long groupid : groups) {
                UserGroup UserGroup=new UserGroup(userid,groupid,appid);
                session.save(UserGroup);
            }
            return  2;
        }catch(Exception e){
            return 1;
        }finally{
            tx.commit();
            session.close();
        }
    }

    @Override
    public void deleteUserGroup(long groupid) {
        Session session = null;
        try {
            session = getSession();
            String hql = "delete UserGroup where groupId=:id";
            Query queryupdate = session.createQuery(hql).setLong("id", groupid);
            queryupdate.executeUpdate();
        } finally {
            session.close();
        }
    }

}
