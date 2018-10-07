package org.rqing.demo.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public abstract class ObjectMapperUtil {
	
	private static final Logger logger = LoggerFactory.getLogger(ObjectMapperUtil.class);
	
	private static final ObjectMapper mapper;
	static {
		mapper = new ObjectMapper();
//		mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss","yyyy-MM-dd"));
		mapper.setDefaultPropertyInclusion(JsonInclude.Include.NON_NULL);
	}
	
	public static byte[] writeValueAsBytes(Object value) {
		try {
			return mapper.writeValueAsBytes(value);
		} catch (JsonProcessingException e) {
			logger.error("write value as bytes error",e);
		}
		return new byte[0];
	}

	public static String writeValueAsString(Object value) {
		try {
			return mapper.writeValueAsString(value);
		} catch (Exception e) {
			logger.error("write value as string error",e);
		}
		return null;
	}
	
	public static <T> T readValue(byte[] src ,Class<T> valueType) {
		try {
			return mapper.readValue(src, valueType);
		} catch (Exception e) {
			logger.error("read value error",e);
		}
		return null;
	}
	
	public static <T> T readValue(String src ,Class<T> valueType) {
		try {
			return mapper.readValue(src, valueType);
		} catch (Exception e) {
			logger.error("read value error",e);
		}
		return null;
	}
	
	public static <T> T readValue(String src ,TypeReference<T> valueTypeRef) {
		try {
			return mapper.readValue(src, valueTypeRef);
		} catch (Exception e) {
			logger.error("read value error",e);
		}
		return null;
	}
	
}
