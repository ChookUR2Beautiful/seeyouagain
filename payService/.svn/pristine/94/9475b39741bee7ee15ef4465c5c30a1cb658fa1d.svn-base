package com.xmniao.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapUtil {
	/*
	 * Map<String,Object>转Map<String,String>
	 */
	public static Map<String,String> Object2String(Map<String,Object> map){
		
		if(map ==null){
			return null;
		}
		Map<String, String> temp = new HashMap<String, String>();
		for (Object key : map.keySet()) {
			temp.put((String) key, map.get(key)==null?"":map.get(key).toString());
		}
		return temp;
	}
	
	public static Map<String,Object> String2Object(Map<String,String> map){
		
		if(map ==null){
			return null;
		}
		Map<String, Object> temp = new HashMap<String, Object>();
		for (Object key : map.keySet()) {
			temp.put((String) key, map.get(key));
		}
		return temp;
	}

	public static List<Map<String, String>> ListObject2String(List<Map<String, Object>> list) {
		List<Map<String,String>> result=new ArrayList<>();
		for (Map<String, Object> map : list) {
			result.add(Object2String(map));
		}
		return result;
	}

}
