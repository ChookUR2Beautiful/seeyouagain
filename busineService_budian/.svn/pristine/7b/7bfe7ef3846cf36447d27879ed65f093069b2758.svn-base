package com.xmniao.common;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;


public class MapUtil {
	
	/**
	 * 将Map<String,Object> 转换为Map<String,String>
	 * @param map	Map<String,Object>
	 * @return
	 */
	public static Map<String,String> formatMapStr(Map<String,Object> map){
		return formatMapStr(map, "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 将Map<String,Object> 转换为Map<String,String>
	 * @param map Map<String,Object>
	 * @param format  格式化时间
	 * @return
	 */
	public static Map<String,String> formatMapStr(Map<String,Object> map, String format){
		/*String  formatF = "%.2f";
		String  formatD = "yyyy-mm-dd HH:mm:ss";
		if(format != null && !"".equals(format.trim())){
			for(String f :format.trim().split(",")){
				if(f.contains("%") && f.contains("f")){
					formatF = f;
				}else if(f.contains("%") || f.contains("f")){
					formatD = f;
				}
			}
		}	*/	
		Map<String,String> result = new HashMap<String, String>();
		Object value = null;
		for(String key : map.keySet()){
			value = map.get(key); 
			if(value instanceof Date){
				result.put(key, DateUtil.dateFormat((Date)value, format));
			}else{
				if(value == null){
					result.put(key, "");					
				}else{
					result.put(key, value.toString());
				}
			}
		}		
		return result;
	}
	
	/**
	 * 将Map<String,Object> 转换为JSONObject
	 * @param map
	 * @return
	 */
	public static JSONObject formatJson(Map<String,Object> map){
		return formatJson(map, "yyyy-mm-dd HH:mm:ss");
	}

	/**
	 * 将Map<String,Object> 转换为JSONObject
	 * @param map Map<String,Object>
	 * @param format  格式化时间
	 * @return
	 */
	public static JSONObject formatJson(Map<String,Object> map, String format){
		JSONObject result = new JSONObject();
		
		Object value = null;
		for(String key : map.keySet()){
			value = map.get(key); 
			if(value instanceof Date){
				result.put(key, DateUtil.dateFormat((Date)value, format));
			}else{
				if(value == null){
					result.put(key, "");					
				}else{
					result.put(key, value);
				}
			}
			
		}		
		return result;
	}
	
	public static void main(String[] args){
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("t1", 1l);
		map.put("t2", 1.2d);
		map.put("t3", 1);
		map.put("t4", "");
		map.put("t5", new Date());
		Long l = null;
		Double d= null;
		Integer i= null;
		Date de= null;
		String s= null;
		map.put("t6", l);
		map.put("t7", d);
		map.put("t8", i);
		map.put("t9", de);
		map.put("t10", s);
		
		System.out.println(MapUtil.formatMapStr(map));
	}
}
