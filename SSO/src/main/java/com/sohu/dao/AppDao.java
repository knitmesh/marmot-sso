/*
* Copyright (c) 2016 Sohu TV. All rights reserved.
*/
package com.sohu.dao;

import java.util.List;

import com.sohu.model.App;
import com.sohu.model.Group;

/**
 * <P>
 * Description:
 * </p>
 * @author wenbozhang
 * @version 1.0
 * @Date 2016年2月29日下午8:20:27
 */
public interface AppDao {

    void applyApp(App app);

    List<App> getAppList(int state);

    void  changeAppState(long appid,int state);

    App getAppById(long appid);

    void insertAppPage(long appid,String groupUrls);

    List<Group> getGroupByAppid(long appid);

    void deleteGroup(long groupid);

    int addUserRight(long[] groups,long userid,long appid);

    void deleteUserGroup(long groupid);
}
