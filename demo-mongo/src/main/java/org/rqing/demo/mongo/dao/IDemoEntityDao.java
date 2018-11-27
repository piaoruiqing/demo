package org.rqing.demo.mongo.dao;

import org.rqing.demo.mongo.entity.DemoEntity;

import com.mongodb.client.result.UpdateResult;

/**
 * 
 * @description 
 * @author piaoruiqing
 * @date: 2018/10/07 13:53
 *
 * @since JDK 1.8
 */
public interface IDemoEntityDao {
    
    /**
     * find by id
     * @author piaoruiqing
     * @date: 2018/10/07 13:56
     * 
     * @param id
     * @return
     */
    DemoEntity findById(String id);

    /**
     * save
     * @author piaoruiqing
     * @date: 2018/10/07 13:56
     * 
     * @param entity
     */
    void save(DemoEntity entity);
    
    /**
     * update
     * @author piaoruiqing
     * @date: 2018/10/07 13:58
     * 
     * @param entity
     * @return
     */
    UpdateResult update(DemoEntity entity);
    
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
