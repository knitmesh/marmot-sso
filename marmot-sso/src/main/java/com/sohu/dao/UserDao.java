/*
* Copyright (c) 2016 Sohu TV. All rights reserved.
*/
package com.sohu.dao;

import java.util.List;

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
 * @Date 2016年2月19日上午10:53:35
 */
public interface UserDao {
    User userLogin(User user);

    User getUserById(Long userid);

    App getAppById(Long appid);

    void updateUserState(long userid,int state);

    User findUserByName(String userName);

    void updateUser(User u);

    void updateUserAppid(long userid,long appid);

    List<User> findUser(long appid,int state);


    List<UserGroup> findGroupsById(long userid,long appid);

    Group getGroupById(long groupid);
}
