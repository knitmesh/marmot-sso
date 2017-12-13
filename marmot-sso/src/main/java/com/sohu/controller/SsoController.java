/*
 * Copyright (c) 2016 Sohu TV. All rights reserved.
 */
package com.sohu.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sohu.dao.redis.PtokenDao;
import com.sohu.dao.redis.TicketDao;
import com.sohu.dao.redis.UrlDao;
import com.sohu.dao.redis.UserStateDao;
import com.sohu.dto.CheckOut;
import com.sohu.model.App;
import com.sohu.model.User;
import com.sohu.service.UserService;
import com.sohu.util.DesUtil;

/**
 * <P>
 * Description:
 * </p>
 *
 * @author wenbozhang
 * @version 1.0
 * @Date 2016年2月19日上午10:30:11
 */
@Controller
@RequestMapping("/user")
public class SsoController extends BaseController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserService userService;

    @Autowired
    private UserStateDao userStateDao;

    @Autowired
    private TicketDao ticketDao;

    @Autowired
    private PtokenDao PtokenDao;

    @Autowired
    private UrlDao UrlDao;

    // 过滤器定位的地方,如果没有Cookie就转到登录页面,如果有验证ticket之登录,没有就进行登录
    @RequestMapping(value = "/index", produces = "application/json;charset=UTF-8")
    public String index(HttpServletRequest request, Long appid, Model model,String requestUrl) throws Exception {// 用于单点多系统登录
        Cookie cookieUserid = getCookieByName(request, "userid");
        if (cookieUserid == null) {
            model.addAttribute("appid", appid);
            model.addAttribute("requestUrl", requestUrl);
            return "login";
        }
        DesUtil des=new DesUtil();
        String desid=des.decrypt(cookieUserid.getValue());//对数据进行解密
        long userid = Integer.parseInt(desid);
        if ("".equals(userid)) {
            model.addAttribute("appid", appid);
            model.addAttribute("requestUrl", requestUrl);
            return "login";
        }
        if (!userStateDao.getUser(userid)) {
            model.addAttribute("appid", appid);
            model.addAttribute("requestUrl", requestUrl);
            return "login";
        }// SSO存在Cookies之后,然后在进行Chect ticket
        App app = userService.getAppById(appid);
        if (app == null) {
            model.addAttribute("appid", appid);
            model.addAttribute("requestUrl", requestUrl);
            return "login";
        }
        CheckOut checkOut = userService.getCheckOut(userid);
        logger.info("{checkOut}", checkOut);
        ticketDao.setTicket(userid, checkOut.getTicket());
        String url =app.getAppInfo()+requestUrl+"?ticket=" + checkOut.getTicket() + "&userid="
                + checkOut.getUserId();
        return "redirect:" + url;
    }

    // 登录页面提交账号密码的地方
    @RequestMapping(value = "/login", produces = "application/json;charset=UTF-8")
    public String userLogin(User user, Long appid, HttpServletResponse response, HttpServletRequest request,String requestUrl) throws Exception {
        Cookie cookieUserid = getCookieByName(request, "userid");
        if (cookieUserid!= null) {
            return "user/repeatLogin";
        }
        User findUser = userService.userLogin(user);
        if (user == null) {
            return "login";
        }

        long userid = findUser.getUserId();
        if (userStateDao.getUser(userid)) {
            return "user/repeatLogin";
        }

        userStateDao.setUser(userid);
        App app = userService.getAppById(appid);
        CheckOut checkOut = userService.getCheckOut(userid);
        ticketDao.setTicket(userid, checkOut.getTicket());

        String sid=Long.toString(userid);
        DesUtil des=new DesUtil();
        String desId=des.encrypt(sid);//用Des进行加密
        Cookie cookie = new Cookie("userid",desId);
        cookie.setPath("/");
        cookie.setMaxAge(60*60);
        response.addCookie(cookie);// 由于httpClient不能加入Cookies,所以只能放在这里

        String url =app.getAppInfo()+requestUrl+"?ticket=" + checkOut.getTicket() + "&userid="
                + checkOut.getUserId();
        System.out.println("======url============="+url);
        return "redirect:" + url;
    }

    // 经过SSOcheck后,回写到子系统Cookies
    @RequestMapping(value = "/check", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public CheckOut userCheck(String ticket, Long userid,Long appid, HttpServletResponse response){
        boolean flag = ticketDao.getTicket(userid, ticket);
        logger.info("flag=" + flag + "   ticket=" + ticket+"  "+appid);
        if (flag) {
            ticketDao.deteleTicket(userid);// 判断ticket成功后,删除redis的ticket

            CheckOut checkOut = userService.getCheckOut(userid);
            PtokenDao.setPtoken(userid, checkOut.getPtoken());// 产生注销的ptoken
            int userState=checkOut.getUserState();
            String[] urls=userService.findGroupsById(userid,appid);   //  通过拦截器向子系统返回数据
            if(urls!=null && userState==1){
                UrlDao.setUrl(userid,urls);
                logger.info(checkOut.toString()+"=============");
            }
            return checkOut;
        } else {
            return null;
        }
    }

    @RequestMapping(value = "logout", produces = "application/json;charset=UTF-8")
    public String userLogout(HttpServletResponse response) {// 测试在70端口下,注销的时候,将cookies删除
        System.out.println("=======重定向到SSO=======");
        Cookie cookie1 = new Cookie("userid", null);
        cookie1.setPath("/");
        cookie1.setMaxAge(0);
        response.addCookie(cookie1);
        return "logout";
    }
}
