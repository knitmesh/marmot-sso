/*
* Copyright (c) 2016 Sohu TV. All rights reserved.
*/
package com.sohu.service;

import java.util.List;

import com.sohu.model.App;
import com.sohu.model.Group;

/**
 * <P>
 * Description:
 * </p>
 * @author wenbozhang
 * @version 1.0
 * @Date 2016年2月29日下午8:06:13
 */
public interface AppService {
    /**
     *
    * @Title: applyApp
    * @Description: (完成app的申请,默认申请者是开发者,且app的默认状态是未审批)
    * @param @param app
    * @param @param userid    设定文件
    * @return void    返回类型
    * @throws
     */
    void applyApp(App app,long userid);
    /**
     *
    * @Title: getAppList
    * @Description: (获取app列表,默认传10代表是全部app信息)
    *               0代表是没有审批的   1代表是审批过的
    * @param @return    设定文件
    * @return List<App>    返回类型
    * @throws
     */
    List<App> getAppList(int state);
    /**
     *
    * @Title: changeAppState
    * @Description: (更新App的状态，同时会将app申请者的角色由用户,改为开发者)
    * @param @param appid
    * @param @param state    设定文件
    * @return void    返回类型
    * @throws
     */
    void  changeAppState(long appid,int state);
    /**
     *
    * @Title: findAppState
    * @Description: (查看App申请的结果)
    * @param @param userid
    * @param @return    设定文件
    * @return App    返回类型
    * @throws
     */
    App findAppState(long userid);
    /**
     *
    * @Title: getGroupByAppid
    * @Description: (根据appid,返回group的列表)
    * @param @param appid
    * @param @return    设定文件
    * @return List<Group>    返回类型
    * @throws
     */
    List<Group> getGroupByAppid(long appid);
     /**
      *
     * @Title: insertGroup
     * @Description: (这里用一句话描述这个方法的作用)
     * @param @param ugroupUrl    设定文件
     * @return void    返回类型
     * @throws
      */
    void insertGroup(String groupUrl,long appid);
    /**
     *
    * @Title: deleteGroup
    * @Description: (删除某个子项目的页面)
    * @param @param groupid    设定文件
    * @return void    返回类型
    * @throws
     */
    void deleteGroup(long groupid);

    /**
     *
    * @Title: insertAdmin
    * @Description: (插入项目管理员)
    * @param @param adminName
    * @param @param appid
    * @param @return    设定文件
    * @return boolean    返回类型
    * @throws
     */
    boolean insertAdmin(String adminName,long appid);
    /**
     *
    * @Title: deleteAdmin
    * @Description: (删除项目对应的管理员)
    * @param @param userid    设定文件
    * @return void    返回类型
    * @throws
     */
    void deleteAdmin(long userid);

    int addUserRight(long[] groups,String name,long appid);
}
