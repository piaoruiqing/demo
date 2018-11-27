package org.rqing.demo.cache.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.concurrent.TimeUnit;

import org.springframework.core.annotation.AliasFor;

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
	 * cache time out <br/>
	 * ms
	 * @author piaoruiqing
	 * @date 2018/09/17 15:41:35
	 * @return
	 */
    @AliasFor("timeout")
	long value() default 10000L;
	
    /**
     * cache time out <br/>
     * ms
     * @author piaoruiqing
     * @date 2018/09/17 15:41:35
     * @return
     */
	@AliasFor("value")
	long timeout() default 10000L;
	
	/**
	 * time unit of cache time out
	 * @author piaoruiqing
	 * @date: 2018/10/07 21:55
	 * 
	 * @return
	 */
	TimeUnit timeUnit() default TimeUnit.MILLISECONDS;
	
	// TODO read from config
	
}
