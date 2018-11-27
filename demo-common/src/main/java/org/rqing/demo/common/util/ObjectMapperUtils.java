package org.rqing.demo.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * jackson object mapper utils
 * 
 * @description 
 * @author piaoruiqing
 * @date: 2018/10/07 21:43
 *
 * @since JDK 1.8
 */
public abstract class ObjectMapperUtils {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ObjectMapperUtils.class);
	
	private static final ObjectMapper MAPPER;
	static {
		MAPPER = new ObjectMapper();
//		mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss","yyyy-MM-dd"));
		MAPPER.setDefaultPropertyInclusion(JsonInclude.Include.NON_NULL);
	}
	
	public static byte[] writeValueAsBytes(Object value) {
		try {
			return MAPPER.writeValueAsBytes(value);
		} catch (JsonProcessingException e) {
			LOGGER.error("write value as bytes error",e);
		}
		return new byte[0];
	}

	public static String writeValueAsString(Object value) {
		try {
			return MAPPER.writeValueAsString(value);
		} catch (Exception e) {
			LOGGER.error("write value as string error",e);
		}
		return null;
	}
	
	public static <T> T readValue(byte[] src ,Class<T> valueType) {
		try {
			return MAPPER.readValue(src, valueType);
		} catch (Exception e) {
			LOGGER.error("read value error",e);
		}
		return null;
	}
	
	public static <T> T readValue(String src ,Class<T> valueType) {
		try {
			return MAPPER.readValue(src, valueType);
		} catch (Exception e) {
			LOGGER.error("read value error",e);
		}
		return null;
	}
	
	public static <T> T readValue(String src ,TypeReference<T> valueTypeRef) {
		try {
			return MAPPER.readValue(src, valueTypeRef);
		} catch (Exception e) {
			LOGGER.error("read value error",e);
		}
		return null;
	}
	
}
