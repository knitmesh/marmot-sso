/*
 * Copyright (c) 2016 Sohu TV. All rights reserved.
 */
package com.sohu.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sohu.dto.AppEnum;
import com.sohu.dto.JsonDto;
import com.sohu.model.App;
import com.sohu.model.Group;
import com.sohu.model.User;
import com.sohu.service.AppService;
import com.sohu.service.UserService;

/**
 * <P>
 * Description:
 * </p>
 *
 * @author wenbozhang
 * @version 1.0
 * @Date 2016年2月25日下午11:56:18
 */
@Controller
@RequestMapping("/app")
public class AppController extends BaseController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private AppService appService;

    @Autowired
    private UserService UserService;

    @RequestMapping(value = "/login")
    public String appindex() {
        return "applogin";
    }

    @RequestMapping(value = "/loginCheck", produces = "application/json;charset=UTF-8")
    public String index(User user, Model model, HttpServletRequest request) {
        User findUser = UserService.userLogin(user);
        if (findUser == null) {
            return "applogin";
        }
        HttpSession session = request.getSession();
        session.setAttribute("login", findUser);
        int userState = findUser.getUserState();

        if (userState == 1) {// 跳转到申请成为开发人员的页面
            model.addAttribute("userid", findUser.getUserId());
            return "user/app_apply";
        }
        if (userState == 2) {// 已经成为管理人员,跳转到管理用户界面
            model.addAttribute("userid", findUser.getUserId());
            long appid=findUser.getAppId();
            List<Group> groups=appService.getGroupByAppid(appid);
            model.addAttribute("groups", groups);
            model.addAttribute("appid", appid);
            return "admin/manageUser";
        }
        if (userState == 3) {// 已经成为开发者,跳转到管理 管理员界面
            long appid=findUser.getAppId();
            model.addAttribute("userid", findUser.getUserId());
            model.addAttribute("appid",appid);
            List<Group> groups=appService.getGroupByAppid(appid);
            List<User> lists=UserService.findUser(appid, 2);

            model.addAttribute("groups", groups);
            model.addAttribute("admins", lists);
            return "develop/manageAdmin";
        }
        if (userState == 4) {// state==4，也就是sso的超级管理员，对应用进行审批
            List<App> apps = appService.getAppList(0);
            model.addAttribute("apps", apps);
            model.addAttribute("userid", findUser.getUserId());
            return "sso_admin/index";
        }

        return "applogin";
    }

    @RequestMapping(value = "/apply", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public JsonDto apply(App app, long userid) {
        logger.info("{userid}", userid);
        logger.info("{app}", app);
        app.setCreateTime(new Date());
        app.setModifyTime(new Date());
        app.setOwnerId(userid);
        appService.applyApp(app, userid);
        return new JsonDto(true,"申请已经成功提交");
    }

    //对申请的应用进行审批
    @RequestMapping(value = "/changeState", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public JsonDto changeState(long appid,int state) {
        appService.changeAppState(appid, state);
        return new JsonDto(true,"修改成功");
    }


    @RequestMapping(value = "/insertGroup", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public JsonDto insertGroup(String url,long appid) {
        appService.insertGroup(url, appid);
        return new JsonDto(true, "插入成功");
    }

    @RequestMapping(value = "/deleteGroup", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public JsonDto deleteGroup(long groupid) {
        appService.deleteGroup(groupid);
        return new JsonDto(true, "删除成功");
    }
    /*@RequestMapping(value = "/findAdmin", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public JsonDto findAdmin(String name) {
        User user=UserService.findUserByName(name);
        if(user==null){
            return new JsonDto(false,"没有找到用户");
        }
        return new JsonDto(true,user);
    }*/
    @RequestMapping(value = "/insertAdmin", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public JsonDto insertAdmin(String userName,long appid) {
        System.out.println("name=="+userName+"   appid=="+appid);
        boolean flag=appService.insertAdmin(userName, appid);
        if(flag){
            return new JsonDto(true,"添加成功");
        }else{
            return new JsonDto(false,"添加失败");
        }
    }
    @RequestMapping(value = "/deleteAdmin", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public JsonDto deleteAdmin(long userid) {
        appService.deleteAdmin(userid);
        return new JsonDto(true, "删除成功");
    }


    @RequestMapping(value = "/addRight", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public JsonDto addRight(long[] groupids,String userName,long appid) {
        for (long l : groupids) {
            System.out.println("groupids==="+l+"   userName"+userName);
        }
        int n=appService.addUserRight(groupids, userName,appid);
        if(n==2){
            return new JsonDto(true, AppEnum.getState(n).getInfo());
        }else{
            return new JsonDto(false, AppEnum.getState(n).getInfo());
        }
    }

   /* @RequestMapping(value="/test")
    public String OutPut(HttpServletRequest request){
        String url=request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getRequestURI();
        System.out.println(url);
        return "test/MyJsp";
    }*/
}
