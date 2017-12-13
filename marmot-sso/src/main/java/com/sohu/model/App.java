/*
* Copyright (c) 2016 Sohu TV. All rights reserved.
*/
package com.sohu.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * <P>
 * Description:
 * </p>
 * @author wenbozhang
 * @version 1.0
 * @Date 2016年2月19日上午10:41:26
 */
@Entity
@Table(name="tb_app")
public class App {

    @Id
    @Column(name="app_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long appId;

    @Column(name="app_name")
    private String appName;

    @Column(name="app_info")
    private String appInfo;

    @Column(name="app_phone")
    private String appPhone;

    @Column(name="app_state")
    private int appState;

    @Column(name="create_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;

    @Column(name="modify_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifyTime;

    @Column(name="owner_id")
    private Long ownerId;

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(long ownerId) {
        this.ownerId = ownerId;
    }

    public int getAppState() {
        return appState;
    }

    public void setAppState(int appState) {
        this.appState = appState;
    }

    public String getAppPhone() {
        return appPhone;
    }

    public void setAppPhone(String appPhone) {
        this.appPhone = appPhone;
    }

    public Long getAppId() {
        return appId;
    }

    public void setAppId(Long appId) {
        this.appId = appId;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }


    public String getAppInfo() {
        return appInfo;
    }

    public void setAppInfo(String appInfo) {
        this.appInfo = appInfo;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    @Override
    public String toString() {
        return "App [appId=" + appId + ", appName=" + appName + ", appInfo=" + appInfo + ", appPhone=" + appPhone
                + ", appState=" + appState + ", createTime=" + createTime + ", modifyTime=" + modifyTime + "]";
    }
}
