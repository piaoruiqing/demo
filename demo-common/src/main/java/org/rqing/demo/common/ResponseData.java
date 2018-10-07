package org.rqing.demo.common;

import java.util.HashMap;
import java.util.Map;

public class ResponseData {
	
	public final static String STATUS_SUCCESS = "0";
	public final static String STATUS_FAILURE = "-1";
	
	private String status;
	private String msg;
	private Object data;
	
	private ResponseData() {}
	
	private ResponseData(String status,Object data) {
		this.status = status;
		this.data = data;
	}
	private ResponseData(String status,String msg,Object data) {
		this.status = status;
		this.msg = msg;
		this.data = data;
	}
	
	public static ResponseData success() {
		return new ResponseData(STATUS_SUCCESS,"suc",null);
	}
	public static ResponseData success(Object data) {
		return new ResponseData(STATUS_SUCCESS,data);
	}
	public static ResponseData failure() {
		return new ResponseData(STATUS_FAILURE,"fail",null);
	}
	public static ResponseData failure(String msg) {
		return new ResponseData(STATUS_FAILURE, msg,null);
	}
	public static ResponseData failure(String status,String msg) {
		if(STATUS_SUCCESS.equals(status)) {
			throw new RuntimeException("failure status must not be \"0\"");
		}
		return new ResponseData(status, msg,null);
	}

	public String getStatus() {
		return status;
	}
	public String getMsg() {
		return msg;
	}
	public Object getData() {
		return data;
	}
	
	public Map<String, Object> convertToMap() {
		Map<String, Object> map = new HashMap<>();
		map.put("status", this.status);
		map.put("msg", this.msg);
		map.put("data", this.data);
		return map;
	}
	
}
