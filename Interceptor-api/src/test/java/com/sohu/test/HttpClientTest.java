/*
* Copyright (c) 2016 Sohu TV. All rights reserved.
*/
package com.sohu.test;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.sso.dto.CheckOut;
import com.sso.util.HttpClientUtil;
import com.sso.util.InterceptorUtil;


/**
 * <P>
 * Description:
 * </p>
 * @author wenbozhang
 * @version 1.0
 * @Date 2016年2月29日上午10:39:31
 */
public class HttpClientTest {

    @Test
    public void test1(){
        HttpClientUtil client=new HttpClientUtil();
        Map map=new HashMap();
        map.put("name", "张文博");
        String result=client.doPost("http://127.0.0.1:8080/manager/index", map,"UTF-8");
        System.out.println(result);
    }
    @Test
    public void test2(){
        Map map=new HashMap();
        map.put("ticket","cf767e6c86026a75f9b30b3d1090c58f");
        map.put("userid","1");
        HttpClientUtil client=new HttpClientUtil();
        String s=client.doPost("http://127.0.0.1:8080/user/check",map,"UTF-8");
        System.out.println(s);
    }
    @Test
    public void test3(){
        CheckOut checkOut=new CheckOut(1L,"11","111");
        String json=InterceptorUtil.toJson(checkOut);
        System.out.println(json);

        CheckOut cc=InterceptorUtil.toCheckOut(json);
        System.out.println(cc);
    }
}
