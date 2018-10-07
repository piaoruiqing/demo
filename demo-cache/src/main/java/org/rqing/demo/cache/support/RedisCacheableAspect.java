package org.rqing.demo.cache.support;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.rqing.demo.cache.annotation.RedisCacheable;
import org.rqing.demo.common.util.ObjectMapperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 * 缓存切面<br/>
 * {@link org.rqing.demo.annotation.RedisCacheable}
 * 
 * @description 
 * @author piaoruiqing
 * @date: 2018/10/05 22:58
 *
 * @since JDK 1.8
 */
@Aspect
@Component
@Slf4j
public class RedisCacheableAspect {
	
	@Autowired
	@Qualifier("protostuffRedisTemplate")
	private RedisTemplate<String, Object> redisTemplate;
	
	
	/**
	 * {@link RedisCacheable}切面
	 * @author piaoruiqing
	 *
	 */
	@Pointcut(value = "execution(* *(..)) && @annotation(org.rqing.demo.cache.annotation.RedisCacheable)")
	public void redisCacheable() {}
	
	@Around(value = "redisCacheable()")
	public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
		Signature signature = proceedingJoinPoint.getSignature();
		Object[] args = proceedingJoinPoint.getArgs();
		
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(proceedingJoinPoint.getTarget().toString()).append(signature.getName());
		for (Object arg : args) {
			stringBuilder.append("_");
			if (arg instanceof Number || arg instanceof String || arg instanceof Boolean || arg instanceof Character) {
				stringBuilder.append(arg);
			} else {
				stringBuilder.append(ObjectMapperUtil.writeValueAsString(arg));
			}
		}
		final String key = stringBuilder.toString();
		Object cache = redisTemplate.opsForValue().get(key);
		// 缓存命中
		if (null != cache) {
			return cache;
		}

		// 缓存未命中
		Object target = proceedingJoinPoint.getTarget();
		Class<?>[] parameterTypes = ((MethodSignature)proceedingJoinPoint.getSignature()).getMethod().getParameterTypes();
		Method method = null;
		try {
			method = target.getClass().getMethod(signature.getName(), parameterTypes);
		} catch (NoSuchMethodException | SecurityException e) {
			log.error("缓存切面解析注解异常", e);
			return proceedingJoinPoint.proceed();
		}
		RedisCacheable redisCachePageable = method.getAnnotation(RedisCacheable.class);

		long timeout = redisCachePageable.timeout();
		
		Object result = proceedingJoinPoint.proceed();
		
		redisTemplate.opsForValue().set(key, result, timeout, TimeUnit.MILLISECONDS);
		
		return result;
	}
	
}
