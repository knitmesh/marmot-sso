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
 * @author wenbozhang
 * @version 1.0
 * @Date 2016年3月1日下午8:00:32
 */
@Entity
@Table(name="user_group")
public class UserGroup {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="user_id")
    private long userId;

    @Column(name="group_id")
    private long  groupId;

    @Column(name="app_id")
    private long  appId;

    public long getAppId() {
        return appId;
    }

    public void setAppId(long appId) {
        this.appId = appId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getGroupId() {
        return groupId;
    }

    public void setGroupId(long groupId) {
        this.groupId = groupId;
    }
    public UserGroup(){}

    public UserGroup(long userId, long groupId, long appId) {
        super();
        this.userId = userId;
        this.groupId = groupId;
        this.appId = appId;
    }

}
