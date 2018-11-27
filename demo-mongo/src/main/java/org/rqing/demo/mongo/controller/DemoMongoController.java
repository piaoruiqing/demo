package org.rqing.demo.mongo.controller;

import java.util.Arrays;
import java.util.Random;
import static org.rqing.demo.mongo.entity.QDemoEntity.DEMO_ENTITY;
import org.rqing.demo.mongo.entity.DemoEntity;
import org.rqing.demo.mongo.repository.IDemoEntityMongoRepository;
import org.rqing.demo.mongo.support.OffsetBasedPageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;

/**
 * 
 * @description 
 * @author piaoruiqing
 * @date: 2018/10/07 14:04
 *
 * @since JDK 1.8
 */
@RestController
@RequestMapping("/demo/mongo")
public class DemoMongoController {
    
    @Autowired
    private IDemoEntityMongoRepository entityMongoRepository;
    
    @RequestMapping(value = "/entity/", method = {RequestMethod.GET})
    public ResponseEntity<?> page(@RequestParam(value = "offset", defaultValue = "0")Long offset, 
                                  @RequestParam(value = "limit", defaultValue = "10")Integer limit){
        Pageable pageable = new OffsetBasedPageRequest(offset, limit);
        BooleanBuilder predicate = new BooleanBuilder();
        Predicate contains1 = DEMO_ENTITY.list.contains(1);
        Predicate contains2 = DEMO_ENTITY.list.any().notIn(2,3,4);
        BooleanBuilder or = predicate.or(contains1).or(contains2);
        Page<DemoEntity> page = entityMongoRepository.findAll(or, pageable);
        return ResponseEntity.ok(page);
    }
    
    @RequestMapping(value = "/entity/{id}", method = {RequestMethod.GET})
    public ResponseEntity<?> find(@PathVariable("id")String id) {
        DemoEntity result = entityMongoRepository.findById(id).orElse(null);
        return ResponseEntity.ok(result);
    }
    
    @RequestMapping(value = "/entity/", method = {RequestMethod.POST})
    public ResponseEntity<?> save() {
        DemoEntity entity = new DemoEntity();
        entity.setName("小明");
        entity.setNo(new Random().nextInt());
        entity.setList(Arrays.asList(0,1,5));
        entity = entityMongoRepository.save(entity);
        return ResponseEntity.ok(entity);
    }
    
}
