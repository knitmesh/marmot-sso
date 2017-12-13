/*
* Copyright (c) 2016 Sohu TV. All rights reserved.
*/
package com.sso.dto;




/**
 * <P>
 * Description:
 * </p>
 * @author wenbozhang
 * @version 1.0
 * @Date 2016年3月2日下午12:11:24
 */
public class IntercepData {
    private CheckOut checkOut;
    private String[] urls;

    public CheckOut getCheckOut() {
        return checkOut;
    }
    public void setCheckOut(CheckOut checkOut) {
        this.checkOut = checkOut;
    }

    public String[] getUrls() {
        return urls;
    }
    public void setUrls(String[] urls) {
        this.urls = urls;
    }

    public IntercepData(CheckOut checkOut, String[] urls) {
        super();
        this.checkOut = checkOut;
        this.urls = urls;
    }
}
