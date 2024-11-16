package com.javaweb.utils;

import java.util.Map;

public class MapUtil {
	public static <T> T getObject(Map<String,Object> request,String key,Class<T> tClass) {
		Object obj=request.getOrDefault(key, null);
		if(obj != null) {
			if(tClass.getTypeName().equals("java.lang.Integer") ) {
				obj = (obj != "") ? Integer.valueOf(obj.toString()) : null;
			}
			else if(tClass.getTypeName().equals("java.lang.String")) {
				obj = (obj != "") ? obj.toString() : null;
			}
			return tClass.cast(obj);
		}
		return null;
	}
}
