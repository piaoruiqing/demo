/*
 * File Name:IEntityMongoRepository.java is created on 2018年10月7日下午3:00:06 by piaoruiqing
 *
 * Copyright (c) 2018, xiaoyujiaoiyu technology All Rights Reserved.
 * 
 */
package org.rqing.demo.mongo.repository;

import org.rqing.demo.mongo.entity.Entity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * 
 * @description 
 * @author piaoruiqing
 * @date: 2018/10/07 15:00
 *
 * @since JDK 1.8
 */
@Repository
public interface IEntityMongoRepository extends MongoRepository<Entity, String> {

}
