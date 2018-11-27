package org.rqing.demo.mongo.entity;

import java.util.List;

import javax.persistence.Entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 
 * @description 
 * @author piaoruiqing
 * @date: 2018/10/07 13:53
 *
 * @since JDK 1.8
 */
@Entity
@Document(collection = "demo_entity")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DemoEntity {

    @Id
    @Field(value = "_id")
    private String id;
    
    @Field(value = "no")
    private Integer no;
    
    @Field(value = "name")
    private String name;
    
    @Field(value = "list")
    private List<Integer> list;
    
    private InnerEntity inner;
    
    @Setter
    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class InnerEntity {
        
        @Field("inner_name")
        private String innerName;
        
        @Field("inner_list")
        private List<String> innerList;
    }
}
