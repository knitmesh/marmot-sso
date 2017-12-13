/*
 * Copyright (c) 2016 Sohu TV. All rights reserved.
 */
package com.sohu.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * <P>
 * Description:
 * </p>
 *
 * @author wenbozhang
 * @version 1.0
 * @Date 2016年3月1日下午5:55:49
 */
@Entity
@Table(name="tb_group")
public class Group {

    @Id
    @Column(name = "group_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long groupId;

    @Column(name="group_url")
    private String groupUrl;

    @Column(name="app_id")
    private long appId;


    public long getGroupId() {
        return groupId;
    }

    public void setGroupId(long groupId) {
        this.groupId = groupId;
    }


    public String getGroupUrl() {
        return groupUrl;
    }

    public void setGroupUrl(String groupUrl) {
        this.groupUrl = groupUrl;
    }

    public long getAppId() {
        return appId;
    }

    public void setAppId(long appId) {
        this.appId = appId;
    }
    public Group(){}
    public Group( String groupUrl, long appId) {
        super();
        this.groupUrl = groupUrl;
        this.appId = appId;
    }

    @Override
    public String toString() {
        return "Group [groupId=" + groupId + ", groupUrl=" + groupUrl + ", appId=" + appId + "]";
    }
}
