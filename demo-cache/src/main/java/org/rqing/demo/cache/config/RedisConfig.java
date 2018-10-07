package org.rqing.demo.cache.config;

import org.rqing.demo.cache.support.GenericProtostuff2ByteRedisSerializer;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * redis conf
 * @author piaoruiqing
 * @date: 2018-09-29:08
 * 
 * @since JDK 1.8
 */
@Configuration
@AutoConfigureAfter(RedisAutoConfiguration.class)
public class RedisConfig {

    @Bean("protostuffRedisTemplate")
    public RedisTemplate<String, Object> protostuffRedisTemplate(LettuceConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new GenericProtostuff2ByteRedisSerializer());
        template.setConnectionFactory(redisConnectionFactory);
        return template;
    }
    
    @Bean("jacksonRedisTemplate")
    public RedisTemplate<String, Object> jacksonRedisTemplate(LettuceConnectionFactory redisConnectionFactory) {
    	RedisTemplate<String, Object> template = new RedisTemplate<>();
    	template.setKeySerializer(new StringRedisSerializer());
    	template.setValueSerializer(new GenericJackson2JsonRedisSerializer());
    	template.setConnectionFactory(redisConnectionFactory);
    	return template;
    }
    
}
