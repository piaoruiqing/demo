package org.rqing.demo.mongo.dao.impl;

import org.rqing.demo.mongo.dao.IDemoEntityDao;
import org.rqing.demo.mongo.entity.DemoEntity;
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
public class DemoEntityDaoImpl implements IDemoEntityDao {
    
    @Autowired
    private MongoTemplate mongoTemplate;

    /* (non-Javadoc)
     * @see org.rqing.demo.mongo.dao.IEntityDao#findById(java.lang.String)
     */
    @Override
    public DemoEntity findById(String id) {
        Query query = new Query(Criteria.where("_id").is(id));
        DemoEntity entity = mongoTemplate.findOne(query, DemoEntity.class);
        return entity;
    }

    /* (non-Javadoc)
     * @see org.rqing.demo.mongo.dao.IEntityDao#save(org.rqing.demo.mongo.entity.Entity)
     */
    @Override
    public void save(DemoEntity entity) {
        mongoTemplate.save(entity);
    }

    /* (non-Javadoc)
     * @see org.rqing.demo.mongo.dao.IEntityDao#update(org.rqing.demo.mongo.entity.Entity)
     */
    @Override
    public UpdateResult update(DemoEntity entity) {
        Query query = new Query(Criteria.where("_id").is(entity.getId()));
        Update update = new Update().set("name", entity.getName())
                                    .set("no", entity.getNo());
        return mongoTemplate.updateFirst(query, update, DemoEntity.class);
    }

    /* (non-Javadoc)
     * @see org.rqing.demo.mongo.dao.IEntityDao#delete(java.lang.String)
     */
    @Override
    public int delete(String id) {
        Query query = new Query(Criteria.where("_id").is(id));
        mongoTemplate.remove(query, DemoEntity.class);
        return 0;
    }

}
