package com.sohu.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import com.sohu.dao.UserDao;
import com.sohu.dto.CheckOut;
import com.sohu.model.App;
import com.sohu.model.User;
import com.sohu.model.UserGroup;
import com.sohu.service.UserService;

/*
 * Copyright (c) 2016 Sohu TV. All rights reserved.
 */
/**
 * <P>
 * Description:
 * </p>
 * @author wenbozhang
 * @version 1.0
 * @Date 2016年2月19日上午10:50:56
 */
@Service
public class UserServiceImpl implements UserService {

    private static final String MD5_1 = "EWQ312312&*^7";

    private static final String MD5_2= "EW12&*^7**^TN##1";
    @Autowired
    private UserDao UserDao;


    @Override
    public User userLogin(User user) {
        User findUser=UserDao.userLogin(user);
        if(findUser==null)
            return null;
        return findUser;
    }

    public String get_Tiekct_Md5(long userid) {
        String md5 = userid + MD5_1+new Date();
        return new String(DigestUtils.md5DigestAsHex(md5.getBytes()));
    }
    public String get_Ptoken_tMd5(long userid) {
        String md5 = userid + MD5_2+new Date();
        return new String(DigestUtils.md5DigestAsHex(md5.getBytes()));
    }

    @Override
    public User getUserById(Long userid) {
        return UserDao.getUserById(userid);
    }

    @Override
    public App getAppById(Long appid) {
        return UserDao.getAppById(appid);
    }
    @Override
    public CheckOut getCheckOut(long userid) {
        CheckOut checkOut=new CheckOut();
        int state=getUserById(userid).getUserState();
        String ticket=get_Tiekct_Md5(userid);
        String ptoken=get_Ptoken_tMd5(userid);
        checkOut.setTicket(ticket);
        checkOut.setPtoken(ptoken);
        checkOut.setUserId(userid);
        checkOut.setUserState(state);
        return checkOut;
    }

    @Override
    public User findUserByName(String userName) {
        return UserDao.findUserByName(userName);
    }

    @Override
    public void updateUser(User u) {
        UserDao.updateUser(u);
    }

    @Override
    public List<User> findUser(long appid, int state) {
        return UserDao.findUser(appid, state);
    }

    @Override
    public String[] findGroupsById(long userid,long appid) {
        List<UserGroup> groups=UserDao.findGroupsById(userid,appid);
        int size=groups.size();
        if(size==0){
            return null;
        }
        String[] urls=new String[size];
        for (int i = 0; i < groups.size(); i++) {
            long groupid=groups.get(i).getGroupId();
            String url=UserDao.getGroupById(groupid).getGroupUrl();
            urls[i]=url;
        }
        return urls;
    }
}
