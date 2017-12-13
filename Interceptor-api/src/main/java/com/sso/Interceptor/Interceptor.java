/*
 * Copyright (c) 2016 Sohu TV. All rights reserved.
 */
package com.sso.Interceptor;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.sso.dao.redis.UrlDao;
import com.sso.dao.redis.UserStateDao;
import com.sso.dto.CheckOut;
import com.sso.util.HttpClientUtil;
import com.sso.util.InterceptorUtil;

/**
 * <P>
 * Description:
 * </p>
 *
 * @author wenbozhang
 * @version 1.0
 * @Date 2016年2月19日上午10:14:07
 */
public class Interceptor implements HandlerInterceptor {
    private final Logger logger = LoggerFactory.getLogger(Interceptor.class);
    @Autowired
    private UserStateDao UserStateDao;

    @Autowired
    private UrlDao UrlDao;

    @Override
    /**
     * preHandle方法是进行处理器拦截用的，顾名思义，该方法将在Controller处理之前进行调用，SpringMVC中的Interceptor拦截器是链式的
     * 回值为false，当preHandle的返回值为false的时候整个请求就结束了
     */
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session=request.getSession();
        String ticket=request.getParameter("ticket");
        String userid=request.getParameter("userid");

        CheckOut CheckOut=(com.sso.dto.CheckOut) session.getAttribute("login");
        boolean checkflag=((!"".equals(ticket)) && (null!=ticket) && (!"".equals(userid)) && (null!=userid));
        if(CheckOut==null){
            System.out.println("==========checkflag====="+checkflag);
            if(checkflag){
                System.out.println("==================1");
                logger.info("userid="+userid+" ticket="+ticket);
                Map map=new HashMap();
                map.put("ticket",ticket);
                map.put("userid",userid);
                map.put("appid",InterceptorUtil.getaAppId());
                HttpClientUtil client=new HttpClientUtil();
                String s=client.doPost("http://127.0.0.1:8080/user/check",map,"UTF-8");
                if(!"".equals(s) && null!=s){//验票成功
                    logger.info("s="+s);
                    CheckOut successTicket=InterceptorUtil.toCheckOut(s);
                    System.out.println("写入Session==================1");
                    session.setAttribute("login", successTicket);
                    boolean flag2=UrlDao.getUrl(successTicket.getUserId(),InterceptorUtil.getRequestUrl(request));//判断URL是否存在
                    if(flag2){//判断验票成功之后,返回的页面是否有权限
                        return true;
                    }else{
                        return false;
                    }
                }else{//验票不成功,重新登录
                    String url = "http://127.0.0.1:8080/user/index?appid="+InterceptorUtil.getaAppId()+"&requestUrl="+request.getRequestURI();
                    response.sendRedirect(url);
                    System.out.println("==================2");
                    return false;
                }
            }else{//第一次登录该系统,没有session,也没有ticket
                String url = "http://127.0.0.1:8080/user/index?appid="+InterceptorUtil.getaAppId()+"&requestUrl="+request.getRequestURI();
                response.sendRedirect(url);
                System.out.println("==================3子系统已经注销");
                return false;
            }

        }else{//session 不为空只检查一下用户是否有权限就行了
            long backUserid=CheckOut.getUserId();
            boolean flag1=UserStateDao.getUser(backUserid);//判断用户是否存在
            if(flag1){
                int state=CheckOut.getUserState();
                if(state!=1){//判断用户的角色，默认只要不是普通用户可以访问任何系统的任何页面
                    return true;
                }
                boolean flag2=UrlDao.getUrl(backUserid,InterceptorUtil.getRequestUrl(request));//判断URL是否存在
                if(flag2){
                    return true;
                }else{
                    return false;
                }
            }else{
                session.removeAttribute("login");
              /*  String url = "http://127.0.0.1:8080/user/index?appid="+InterceptorUtil.getaAppId();
                response.sendRedirect(url);*/
                return false;
            }
        }
    }

    /**
     * postHandle是进行处理器拦截用的，它的执行时间是在处理器进行处理之后，
     * 也就是在Controller的方法调用之后执行，但是它会在DispatcherServlet进行视图的渲染之前执行
     * ，也就是说在这个方法中你可以对ModelAndView进行操
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
            ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {// 关闭资源
    }

}
