/*
 * File Name:Entity.java is created on 2018年10月7日下午1:53:52 by piaoruiqing
 *
 * Copyright (c) 2018, xiaoyujiaoiyu technology All Rights Reserved.
 * 
 */
package org.rqing.demo.mongo.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.Data;

/**
 * 
 * @description 
 * @author piaoruiqing
 * @date: 2018/10/07 13:53
 *
 * @since JDK 1.8
 */
@Data
@Document(collection = "entity")
public class Entity {

    @Id
    @Field(value = "_id")
    private String id;
    
    @Field(value = "no")
    private Integer no;
    
    @Field(value = "name")
    private String name;
}
