/*
* Copyright (c) 2016 Sohu TV. All rights reserved.
*/
package com.sohu.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sohu.dao.BaseDao;

/**
 * <P>
 * Description:
 * </p>
 * @author wenbozhang
 * @version 1.0
 * @Date 2016年2月19日上午11:15:49
 */
@Repository
public class BaseDaoImpl implements BaseDao{

    @Autowired
    private SessionFactory sessionfactory;

    @Override
    public Session getSession() {
        return sessionfactory.openSession();
    }

}
