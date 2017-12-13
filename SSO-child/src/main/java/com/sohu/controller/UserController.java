/*
 * Copyright (c) 2016 Sohu TV. All rights reserved.
 */
package com.sohu.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sso.dao.redis.PtokenDao;
import com.sso.dao.redis.UrlDao;
import com.sso.dao.redis.UserStateDao;
import com.sso.dto.CheckOut;

/**
 * <P>
 * Description:
 * </p>
 *
 * @author wenbozhang
 * @version 1.0
 * @Date 2016年3月2日下午7:24:26
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private PtokenDao PtokenDao;

    @Autowired
    private UserStateDao UserStateDao;

    @Autowired
    private UrlDao UrlDao;

    @RequestMapping(value = "/index", produces = "application/json;charset=UTF-8")
    public String userCenter() {
        return "index";
    }

    @RequestMapping(value = "logout")
    public String logoutAll(HttpServletRequest request) {
        HttpSession session = request.getSession();
        CheckOut checkOut = (CheckOut) session.getAttribute("login");
        if (checkOut == null)
            return "redirect:/user/index";
        long userid = checkOut.getUserId();
        String ptoken = checkOut.getPtoken();
        if (PtokenDao.getPtoken(userid, ptoken)) {
            PtokenDao.detelePtoken(userid);
            UserStateDao.deteleUser(userid);
            UrlDao.deteleTicket(userid);
            session.removeAttribute("login");
            String url = "http://127.0.0.1:8080/user/logout";
            return "redirect:" + url;
        } else {
            return "error";
        }
    }


    @RequestMapping(value="/1")
    public String page1(){
        return "1";
    }
    @RequestMapping(value="/2")
    public String page2(){
        return "2";
    }
    @RequestMapping(value="/3")
    public String page3(){
        return "3";
    }
}
