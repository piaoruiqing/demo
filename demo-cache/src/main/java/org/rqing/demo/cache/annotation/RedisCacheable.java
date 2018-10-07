package org.rqing.demo.cache.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * cache annotation
 * @author piaoruiqing
 * @date 2018/09/17 15:41:03
 * @version
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface RedisCacheable {
	
	/**
	 * 缓存超时时间<br/>
	 * ms
	 * @author piaoruiqing
	 * @date 2018/09/17 15:41:35
	 * @return
	 */
	long timeout() default 10000L;
	
	// TODO 时间读取配置
	
}
