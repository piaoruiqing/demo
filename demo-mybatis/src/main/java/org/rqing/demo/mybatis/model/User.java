package org.rqing.demo.mybatis.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    
    private Long id;
    private Long gmtCreate;
    private Long gmtModify;
    private String name;
    private Integer age;
    private Byte type;

}