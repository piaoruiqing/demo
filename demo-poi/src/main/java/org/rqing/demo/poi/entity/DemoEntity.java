package org.rqing.demo.poi.entity;

import java.util.Date;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * 
 * @description 
 * @author piaoruiqing
 * @date: 2018/11/18 13:02
 *
 * @since JDK 1.8
 */
@Getter
@Setter
@Builder
public class DemoEntity {

    private String name;
    private Integer age;
    private Integer no;
    
    private Long gmtCreate;
    private Date birthday;
}
