package org.rqing.demo.cache;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * 
 * 
 * @description 
 * @author piaoruiqing
 * @date: 2018/10/01 13:21
 *
 * @since JDK 1.8
 */
@SpringBootApplication
@EnableAspectJAutoProxy
public class DemoCacheApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoCacheApplication.class, args);
	}
}
