/*
* Copyright (c) 2016 Sohu TV. All rights reserved.
*/
package com.sohu.util;

import java.util.ResourceBundle;

import com.google.gson.Gson;



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

    public  static Object toObject(String s,Class t){
        Gson gson=new Gson();
        return gson.fromJson(s,t);
    }
}
