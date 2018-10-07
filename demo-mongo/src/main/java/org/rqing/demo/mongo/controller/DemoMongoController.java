package org.rqing.demo.mongo.controller;

import java.util.Random;

import org.rqing.demo.mongo.entity.Entity;
import org.rqing.demo.mongo.repository.IEntityMongoRepository;
import org.rqing.demo.mongo.support.OffsetBasedPageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    private IEntityMongoRepository entityMongoRepository;
    
    @RequestMapping(value = "/entity", method = {RequestMethod.GET})
    public ResponseEntity<?> page(@RequestParam(value = "offset", defaultValue = "0")Long offset, 
                                  @RequestParam(value = "limit", defaultValue = "10")Integer limit){
        Example<Entity> example = Example.of(new Entity());
        Pageable pageable = new OffsetBasedPageRequest(offset, limit);
        Page<Entity> page = entityMongoRepository.findAll(example, pageable);
        return ResponseEntity.ok(page);
    }
    
    @RequestMapping(value = "/entity/{id}", method = {RequestMethod.GET})
    public ResponseEntity<?> find(@PathVariable("id")String id) {
        Entity result = entityMongoRepository.findById(id).orElse(null);
        return ResponseEntity.ok(result);
    }
    
    @RequestMapping(value = "/save", method = {RequestMethod.POST})
    public ResponseEntity<?> save() {
        Entity entity = new Entity();
        entity.setName("小明");
        entity.setNo(new Random().nextInt());
        entity = entityMongoRepository.save(entity);
        return ResponseEntity.ok(entity);
    }
    
}
