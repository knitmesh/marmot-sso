/*
* Copyright (c) 2016 Sohu TV. All rights reserved.
*/
package com.sohu.dao;

import org.hibernate.Session;

/**
 * <P>
 * Description:
 * </p>
 * @author wenbozhang
 * @version 1.0
 * @Date 2016年2月19日上午11:15:34
 */
public interface BaseDao {
    Session getSession();
}
