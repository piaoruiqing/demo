package org.rqing.demo.mybatis;

import java.util.Random;
import java.util.UUID;

import org.rqing.demo.common.ResponseData;
import org.rqing.demo.mybatis.dao.UserMapper;
import org.rqing.demo.mybatis.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.ISelect;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@SpringBootApplication
@EnableAutoConfiguration
@RestController
@RequestMapping("/demo/mybatis")
public class DemoMyBatisApplication {
    
    @Autowired
    private UserMapper userMapper;
    
    @RequestMapping(value = "/page/lambda")
    public ResponseEntity<?> lambda(
            @RequestParam(value = "limit", defaultValue = "10") Integer limit,
            @RequestParam(value = "offset", defaultValue = "0") Integer offset){
        
        Page<User> page = PageHelper.offsetPage(offset, limit).doSelectPage(() -> userMapper.page(0L, Long.MAX_VALUE));
        return ResponseEntity.ok(ResponseData.success(new PageInfo<>(page)));
    }
    
    @RequestMapping(value = "/page/static_page_helper")
    public ResponseEntity<?> staticPageHelper(
        @RequestParam(value = "limit", defaultValue = "10") Integer limit,
        @RequestParam(value = "offset", defaultValue = "0") Integer offset){
        
        PageHelper.offsetPage(offset, limit);
        Page<User> page = userMapper.page(0L, Long.MAX_VALUE);
        return ResponseEntity.ok(ResponseData.success(new PageInfo<>(page)));
    }
    
    @RequestMapping(value = "/page/iselect")
    public ResponseEntity<?> iselect(
        @RequestParam(value = "limit", defaultValue = "10") Integer limit,
        @RequestParam(value = "offset", defaultValue = "0") Integer offset){
        
        Page<User> page = PageHelper.offsetPage(offset, limit).doSelectPage(new ISelect() {
            @Override
            public void doSelect() {
                userMapper.page(0L, Long.MAX_VALUE);
            }
        });
        return ResponseEntity.ok(ResponseData.success(new PageInfo<>(page)));
    }
    
    @RequestMapping(value = "/add")
    public ResponseEntity<?> add(){
        User user = User.builder().name(UUID.randomUUID().toString()).age(new Random().nextInt(100)).build();
        userMapper.insert(user);
        return ResponseEntity.ok(user);
    }

    public static void main(String[] args) {
        SpringApplication.run(DemoMyBatisApplication.class, args);
    }
}
