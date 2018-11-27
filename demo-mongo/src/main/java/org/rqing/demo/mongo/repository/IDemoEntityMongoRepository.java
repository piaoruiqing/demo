package org.rqing.demo.mongo.repository;

import org.rqing.demo.mongo.entity.DemoEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
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
public interface IDemoEntityMongoRepository extends MongoRepository<DemoEntity, String>, QuerydslPredicateExecutor<DemoEntity>{

}
