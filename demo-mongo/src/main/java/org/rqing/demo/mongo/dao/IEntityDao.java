/*
 * File Name:IEntityDao.java is created on 2018年10月7日下午1:53:10 by piaoruiqing
 *
 * Copyright (c) 2018, xiaoyujiaoiyu technology All Rights Reserved.
 * 
 */
package org.rqing.demo.mongo.dao;

import org.rqing.demo.mongo.entity.Entity;

import com.mongodb.client.result.UpdateResult;

/**
 * 
 * @description 
 * @author piaoruiqing
 * @date: 2018/10/07 13:53
 *
 * @since JDK 1.8
 */
public interface IEntityDao {
    
    /**
     * find by id
     * @author piaoruiqing
     * @date: 2018/10/07 13:56
     * 
     * @param id
     * @return
     */
    Entity findById(String id);

    /**
     * save
     * @author piaoruiqing
     * @date: 2018/10/07 13:56
     * 
     * @param entity
     */
    void save(Entity entity);
    
    /**
     * update
     * @author piaoruiqing
     * @date: 2018/10/07 13:58
     * 
     * @param entity
     * @return
     */
    UpdateResult update(Entity entity);
    
    /**
     * delete by id
     * @author piaoruiqing
     * @date: 2018/10/07 13:59
     * 
     * @param id
     * @return
     */
    int delete(String id);
    
}
