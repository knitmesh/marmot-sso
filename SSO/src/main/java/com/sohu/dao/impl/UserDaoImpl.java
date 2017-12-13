/*
* Copyright (c) 2016 Sohu TV. All rights reserved.
*/
package com.sohu.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.sohu.dao.UserDao;
import com.sohu.model.App;
import com.sohu.model.Group;
import com.sohu.model.User;
import com.sohu.model.UserGroup;

/**
 * <P>
 * Description:
 * </p>
 * @author wenbozhang
 * @version 1.0
 * @Date 2016年2月19日上午10:54:25
 */
@Repository
public class UserDaoImpl extends BaseDaoImpl implements UserDao{
    @Autowired
    private HibernateTemplate hibernateTemplate;
    @Override
    public User userLogin(User user) {
        Session session=getSession();
        String hql="from User u where u.userName=:userName and u.userPassword=:userPassword";
        try {
            Query query=session.createQuery(hql);
            query.setString("userName", user.getUserName());
            query.setString("userPassword",user.getUserPassword());
            List<User> list=query.list();
            if(list.size()==1){
                return list.get(0);
            }else{
                return null;
            }
        }finally{
            session.close();
        }
    }

    @Override
    public User getUserById(Long userid) {
        Session session=getSession();
        User user=(User) session.get(User.class, userid);
        session.close();
        return user;
    }

    @Override
    public App getAppById(Long appid) {
        Session session=getSession();
        App app=(App) session.get(App.class, appid);
        session.close();
        return app;
    }

    @Override
    public void updateUserState(long userid, int state) {
        Session session=getSession();
        String hql="UPDATE User u SET u.userState=:state WHERE  u.userId=:id";
        Query queryupdate=session.createQuery(hql).setInteger("state",state).setLong("id", userid);
        queryupdate.executeUpdate();
        session.close();
    }

    @Override
    public User findUserByName(String userName) {
        Session session=getSession();
        String hql="from User u where u.userName=:name";
        Query query=session.createQuery(hql);
        query.setString("name", userName);
        List<User> list=query.list();
        if(list.size()==1){
            return list.get(0);
        }else{
            return null;
        }
    }

    @Override
    public void updateUser(User u) {
        Session session=getSession();
        Transaction tx = session.beginTransaction();
        try {
            session.update(u);
        } finally{
            tx.commit();
            session.close();
        }
    }

    @Override
    public void updateUserAppid(long userid, long appId) {
        Session session=getSession();
        String hql="UPDATE User u SET u.appId=:appId WHERE  u.userId=:id";
        Query queryupdate=session.createQuery(hql).setLong("appId",appId).setLong("id", userid);
        queryupdate.executeUpdate();
        session.close();
    }

    @Override
    public List<User> findUser(long appid, int state) {
        Session session=getSession();
        String hql="from User u where u.userState=:state and u.appId=:id";
        Query query=session.createQuery(hql);
        query.setInteger("state", state);
        query.setLong("id", appid);
        List<User> list=query.list();
        return list;
    }

    @Override
    public List<UserGroup> findGroupsById(long userid,long appId) {
        Session session=getSession();
        String hql="from UserGroup u where  u.userId=:id and u.appId=:appId";
        Query query=session.createQuery(hql);
        query.setLong("id", userid);
        query.setLong("appId", appId);
        List<UserGroup> list=query.list();
        return list;
    }
    @Override
    public Group getGroupById(long groupid) {
        Session session = getSession();
        Group group = (Group) session.get(Group.class, groupid);
        if (group == null) {
            return null;
        }
        session.close();
        return group;
    }

}
