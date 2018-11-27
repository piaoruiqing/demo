package org.rqing.demo.common.util;

/**
 * string utils
 * 
 * @description 
 * @author piaoruiqing
 * @date: 2018/10/07 21:44
 *
 * @since JDK 1.8
 */
public class StringUtils extends org.apache.commons.lang3.StringUtils {

	/**
	 * Converts the first character in argument to lowercase using case
	 * @date 2018-04-01 21:20:00
	 * @author Ruiqing.Piao
	 * @param str
	 * @return
	 */
	public static String toLowerCaseFirstOne(String str) {
		if(null == str || str.length() <= 0) {
			return str;
		} else if (Character.isLowerCase(str.charAt(0))) {
			return str;
		} else {
			return new StringBuilder().append(Character.toLowerCase(str.charAt(0))).append(str.substring(1)).toString();
		}
	}
	
	/**
	 * Converts the first character in argument to uppercase using case
	 * @date 2018-04-01 21:20:03
	 * @author Ruiqing.Piao
	 * @param str
	 * @return
	 */
	public static String toUpperCaseFirstOne(String str) {
		if(null == str || str.length() <= 0) {
			return str;
		} else if (Character.isUpperCase(str.charAt(0))) {
			return str;
		} else {
			return new StringBuilder().append(Character.toUpperCase(str.charAt(0))).append(str.substring(1)).toString();
		}
	}
	
}
