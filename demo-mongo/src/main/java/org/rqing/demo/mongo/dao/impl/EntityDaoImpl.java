/*
 * File Name:EntityDaoImpl.java is created on 2018年10月7日下午1:56:03 by piaoruiqing
 *
 * Copyright (c) 2018, xiaoyujiaoiyu technology All Rights Reserved.
 * 
 */
package org.rqing.demo.mongo.dao.impl;

import org.rqing.demo.mongo.dao.IEntityDao;
import org.rqing.demo.mongo.entity.Entity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.mongodb.client.result.UpdateResult;

/**
 * 
 * @description 
 * @author piaoruiqing
 * @date: 2018/10/07 13:56
 *
 * @since JDK 1.8
 */
@Repository
public class EntityDaoImpl implements IEntityDao {
    
    @Autowired
    private MongoTemplate mongoTemplate;

    /* (non-Javadoc)
     * @see org.rqing.demo.mongo.dao.IEntityDao#findById(java.lang.String)
     */
    @Override
    public Entity findById(String id) {
        Query query = new Query(Criteria.where("_id").is(id));
        Entity entity = mongoTemplate.findOne(query, Entity.class);
        return entity;
    }

    /* (non-Javadoc)
     * @see org.rqing.demo.mongo.dao.IEntityDao#save(org.rqing.demo.mongo.entity.Entity)
     */
    @Override
    public void save(Entity entity) {
        mongoTemplate.save(entity);
    }

    /* (non-Javadoc)
     * @see org.rqing.demo.mongo.dao.IEntityDao#update(org.rqing.demo.mongo.entity.Entity)
     */
    @Override
    public UpdateResult update(Entity entity) {
        Query query = new Query(Criteria.where("_id").is(entity.getId()));
        Update update = new Update().set("name", entity.getName())
                                    .set("no", entity.getNo());
        return mongoTemplate.updateFirst(query, update, Entity.class);
    }

    /* (non-Javadoc)
     * @see org.rqing.demo.mongo.dao.IEntityDao#delete(java.lang.String)
     */
    @Override
    public int delete(String id) {
        Query query = new Query(Criteria.where("_id").is(id));
        mongoTemplate.remove(query, Entity.class);
        return 0;
    }

}
