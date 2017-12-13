/*
 * Copyright (c) 2016 Sohu TV. All rights reserved.
 */
package com.sohu.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

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

    @Override
    /**
     * preHandle方法是进行处理器拦截用的，顾名思义，该方法将在Controller处理之前进行调用，SpringMVC中的Interceptor拦截器是链式的
     * 回值为false，当preHandle的返回值为false的时候整个请求就结束了
     */
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        return true;
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
