package com.xmn.saas.utils;

import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializeFilter;
import com.xmn.saas.filter.ComplexPropertyPreFilter;

public class JsonUtils {
	//获取fastjson多级过滤器
	public static ComplexPropertyPreFilter setIncludeProperty(Map<Class<?>, String[]> includes){
		 ComplexPropertyPreFilter filter = new ComplexPropertyPreFilter();
		 filter.setIncludes(includes);
		 return filter;
		
	}
	
	public static String toJSONString(Object object,Map<Class<?>, String[]> includes,String dateFormat){
		String json = JSON.toJSONString(object, 
																	SerializeConfig.globalInstance, 
																	includes != null ? new SerializeFilter[] {setIncludeProperty(includes)} : null, 
																	dateFormat, 
																	JSON.DEFAULT_GENERATE_FEATURE);
		return json;
	}
	
	public static String toJSONString(Object object){
		return JSON.toJSONString(object);
	}
	
	public static String toJSONString(Object object,String dateFormat){
		return JSON.toJSONStringWithDateFormat(object,dateFormat);
	}

}
