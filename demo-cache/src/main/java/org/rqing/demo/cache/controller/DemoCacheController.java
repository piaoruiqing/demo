package org.rqing.demo.cache.controller;

import java.util.Date;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.rqing.demo.cache.annotation.RedisCacheable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

/**
 * demo cache controller
 * 
 * @description 
 * @author piaoruiqing
 * @date: 2018/10/07 21:52
 *
 * @since JDK 1.8
 */
@RestController
@RequestMapping("/demo/cache")
@Slf4j
public class DemoCacheController {
    
	@RequestMapping(value = "/test", method = {RequestMethod.GET})
	@RedisCacheable(300*1000L)
	public Object test() {
		log.info("run");
		return new Entity();
	}
	
	@Autowired
	@Qualifier("protostuffRedisTemplate")
	private RedisTemplate<String, Object> protostuffRedisTemplate;
	@Autowired 
	@Qualifier("jacksonRedisTemplate")
	private RedisTemplate<String, Object> jacksonRedisTemplate;
	
	@RequestMapping(value = "/compare", method = {RequestMethod.GET})
	public Object compare(@RequestParam("type") Integer type, @RequestParam("count")Integer count) {
		
		RedisTemplate<String, Object> redisTemplate = null;
		switch (type) {
		case 1:
			redisTemplate = protostuffRedisTemplate;
			break;
		case 2:
			redisTemplate = jacksonRedisTemplate;
			break;
		default:
			redisTemplate = protostuffRedisTemplate;
			break;
		}
		
		long start = System.nanoTime();
		for (int index = 0 ; index < count; index++) {
			redisTemplate.opsForValue().set(String.valueOf(index), new Entity());
			Entity object = (Entity)redisTemplate.opsForValue().get(String.valueOf(index));
			System.out.println(object.toString());
		}
		long end = System.nanoTime();
		long cost = end - start;
		log.info("RedisTemplate cost: {}", cost);
		
		return "COST: " + cost;
	}
	
	@Setter
	@Getter
	@ToString
    public static class Entity{
        private String _string = "197E6F9E79FA4ACD83197A28A62836BD";
        private Long _long = Long.MAX_VALUE;
        private Integer _integer = Integer.MAX_VALUE;
        private Double _double = Double.MAX_VALUE;
        private Float _float = Float.MAX_VALUE;
        private Short _short = Short.MAX_VALUE;
        private Byte _byte = Byte.MAX_VALUE;
        private Character _char = 'a';
        private Date _date = new Date();
        private Date _date_2 = null;
//        private Byte[] _byte_array = new Byte[] {1};
        private byte[] __byte_array = new byte[16];
//        private Integer[] _integer_array = new Integer[16];
//        private Date[] _date_array = new Date[16];
//        private Byte[] _byte_array = null;
        private Integer[] _integer_array = new Integer[] {};
        private Integer[] _integer_array_2 = null;
        private int[] _int_array = new int[16];
        private int[] _int_array_2 = new int[] {0,1,2,3,4,5,6,7,8,9};
        private long __long = Long.MAX_VALUE;
        private int __integer = Integer.MAX_VALUE;
        private double __double = Double.MAX_VALUE;
        private float __float = Float.MAX_VALUE;
        private short __short = Short.MAX_VALUE;
        private byte __byte = Byte.MAX_VALUE;
        private char __char = 'a';
    }
	
}
