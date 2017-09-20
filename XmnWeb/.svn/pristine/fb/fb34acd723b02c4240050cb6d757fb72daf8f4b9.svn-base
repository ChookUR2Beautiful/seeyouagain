/**   
 * 文件名：JsonUtil.java   
 *    
 * 日期：2014-1-11   
 * 
 * Copyright (c) 广州闪购软件服务有限公司-版权所有     
 */
package com.xmniao.xmn.core.util;

import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.SerializeWriter;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.ValueFilter;

/**
 * 项目名称：Assistant
 * 
 * 类名称：JsonUtil
 * 
 * 类描述： JSON转换工具
 * 
 * 创建人： zhou'sheng
 * 
 * 创建时间：2014-1-11 下午2:20:41
 * 
 * Copyright (c) 广州闪购软件服务有限公司-版权所有
 */
public class JsonUtil {

	/**
	 * 
	 * toJSONString(JSON格式转换)
	 * 
	 * @param object
	 * @param features
	 * @return
	 * 
	 * @author：zhou'sheng
	 */
	public static String toJSONString(Object object, SerializerFeature... features) {
		SerializeWriter out = new SerializeWriter();
		String s;
		JSONSerializer serializer = new JSONSerializer(out);
		SerializerFeature arr$[] = features;
		int len$ = arr$.length;
		for (int i$ = 0; i$ < len$; i$++) {
			SerializerFeature feature = arr$[i$];
			serializer.config(feature, true);
		}

		serializer.getValueFilters().add(new ValueFilter() {
			public Object process(Object obj, String s, Object value) {
				if (null != value) {
					if (value instanceof java.util.Date) {
						return String.format("%1$tF %1tT", value);
					}
					return value;
				} else {
					return "";
				}
			}
		});
		serializer.write(object);
		s = out.toString();
		out.close();
		return s;
	}
	
	
	/**
	 * fast value 为null时返回空
	 */
	public static ValueFilter vfilter = new ValueFilter() {
	    @Override
	    public Object process(Object obj, String s, Object v) {
	    if(v==null)
	        return "";
	    return v;
	    }
	};

}
