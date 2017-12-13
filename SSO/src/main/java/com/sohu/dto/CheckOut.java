/*
* Copyright (c) 2016 Sohu TV. All rights reserved.
*/
package com.sohu.dto;
/**
 * <P>
 * Description:用于进行子系统的校验,以及注销校验
 * </p>
 * @author wenbozhang
 * @version 1.0
 * @Date 2016年2月19日上午10:58:53
 */
public class CheckOut {
    private Long userId; //用于表示唯一用户
    private String ticket;  //用于登录验证的票据
    private String ptoken;  //用于注销验证的票据
    /**
     * 代表登录当前系统用户的类型  1：普通用户 2 管理员 3 开发者 4 SSO超管
     */
    private int userState;


    public int getUserState() {
        return userState;
    }
    public void setUserState(int userState) {
        this.userState = userState;
    }
    public Long getUserId() {
        return userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }
    public String getTicket() {
        return ticket;
    }
    public void setTicket(String ticket) {
        this.ticket = ticket;
    }
    public String getPtoken() {
        return ptoken;
    }
    public void setPtoken(String ptoken) {
        this.ptoken = ptoken;
    }
    public CheckOut(){};
    public CheckOut(Long userId, String ticket, String ptoken) {
        this.userId = userId;
        this.ticket = ticket;
        this.ptoken = ptoken;
    }
    @Override
    public String toString() {
        return "CheckOut [userId=" + userId + ", ticket=" + ticket + ", ptoken=" + ptoken + "]";
    }
}
