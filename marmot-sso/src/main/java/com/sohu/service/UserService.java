/*
* Copyright (c) 2016 Sohu TV. All rights reserved.
*/
package com.sohu.service;

import java.util.List;

import com.sohu.dto.CheckOut;
import com.sohu.model.App;
import com.sohu.model.User;

/**
 * <P>
 * Description:
 * </p>
 * @author wenbozhang
 * @version 1.0
 * @Date 2016年2月19日上午10:50:22
 */
public interface UserService {
    /**
     *
    * @Title: userLogin
    * @Description: (用户的账号密码登录)
    * @param @param user
    * @param @return    设定文件
    * @return User    返回类型
    * @throws
     */
    User userLogin(User user);
     /**
      *
     * @Title: getUserById
     * @Description: (根据userid返回user)
     * @param @param userid
     * @param @return    设定文件
     * @return User    返回类型
     * @throws
      */
    User getUserById(Long userid);
    /**
     *
    * @Title: getAppById   根据appid,获取app信息
    * @param @param appid
    * @param @return    设定文件
    * @return App    返回类型
    * @throws
     */
    App getAppById(Long appid);
    /**
     *
    * @Title: getCheckOut    更加用户的id，生成ticket,ptoken
    * @param @param userid
    * @param @return    设定文件
    * @return CheckOut    返回类型
    * @throws
     */
    CheckOut getCheckOut(long userid);
     /**
      *
     * @Title: findUserByName
     * @Description: (根据userName找User)
     * @param @param userName
     * @param @return    设定文件
     * @return User    返回类型
     * @throws
      */
    User findUserByName(String userName);
    /**
     *
    * @Title: updateUser
    * @Description: (更新User对象)
    * @param @param u    设定文件
    * @return void    返回类型
    * @throws
     */
    void updateUser(User u);
    /**
     *
    * @Title: findUser
    * @Description: (返回某个项目特定角色的用户)
    *               其实只有 开发者 和 管理员两种角色
    * @param @param appid
    * @param @param state
    * @param @return    设定文件
    * @return List<User>    返回类型
    * @throws
     */
    List<User> findUser(long appid,int state);

    String[] findGroupsById(long userid,long appid);

}
