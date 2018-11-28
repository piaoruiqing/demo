package org.rqing.demo.mybatis.util;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * 
 * 
 * @description 
 * @author piaoruiqing
 * @date: 2018/11/28 19:18
 *
 * @since JDK 1.8
 */
public interface PageHelperMapper<T> extends Mapper<T>, MySqlMapper<T> {
    
}
