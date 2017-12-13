/*
* Copyright (c) 2016 Sohu TV. All rights reserved.
*/
package com.sso.util;

import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

import com.google.gson.Gson;
import com.sso.dto.CheckOut;



/**
 * <P>
 * Description:
 * </p>
 * @author wenbozhang
 * @version 1.0
 * @Date 2016年2月19日上午9:59:20
 */
public class InterceptorUtil {
    public static String getaAppId(){
        ResourceBundle resource = ResourceBundle.getBundle("app");
        return resource.getString("appid");
    }

    public static String toJson(Object t){
        Gson gson=new Gson();
        return gson.toJson(t);
    }

    public  static CheckOut toCheckOut(String s){
        Gson gson=new Gson();
        return gson.fromJson(s,CheckOut.class);
    }
    public static String getRequestUrl(HttpServletRequest request){
        String url=request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getRequestURI();
        System.out.println("=====url====="+url);
        return url;
    }
}
