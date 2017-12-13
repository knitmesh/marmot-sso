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
 * @Date 2016年2月25日下午11:15:07
 */
@Entity
@Table(name="tb_url")
public class Url {
    @Id
    @Column(name="url_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long urlId;



    @Column(name="url_group")
    private long urlGroup;

    @Column(name="url_content")
    private String urlContent;

    public long getUrlId() {
        return urlId;
    }

    public void setUrlId(long urlId) {
        this.urlId = urlId;
    }

    public long getUrlGroup() {
        return urlGroup;
    }

    public void setUrlGroup(long urlGroup) {
        this.urlGroup = urlGroup;
    }

    public String getUrlContent() {
        return urlContent;
    }

    public void setUrlContent(String urlContent) {
        this.urlContent = urlContent;
    }

    @Override
    public String toString() {
        return "Url [urlId=" + urlId + ", urlGroup=" + urlGroup + ", urlContent=" + urlContent + "]";
    }

}
