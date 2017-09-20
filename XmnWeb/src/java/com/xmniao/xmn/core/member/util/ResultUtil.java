package com.xmniao.xmn.core.member.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xmniao.xmn.core.member.entity.ResponseContainer;
import com.xmniao.xmn.core.thrift.service.Result;
import com.xmniao.xmn.core.util.JsonUtil;

public class ResultUtil {
	
	/**
	 * 设置数据容器
	 * @param resulet
	 * @return
	 */
	public static <T> ResponseContainer setContainer(Result resulet,Class<T> type){
		ResponseContainer container = null;
		if(null!=resulet){
			container = new ResponseContainer();
			container.setTotal(resulet.getCount());
			List<T> list = setResponseValue(resulet.getData(),type);
			container.setData(list);
		}
		return container;
		
	}
	
	/**
	 * 设置返回数据
	 * @param value
	 * @return
	 */
	public static <T> List<T>  setResponseValue(List<Map<String, String>> value,Class<T> type){
		List<T> list = null;
		if(null!=value&&!value.isEmpty()){
			try{
				list = new ArrayList<T>(value.size());
				for(Map<String, String> map:value){
					String obj = JSONObject.toJSONString(map,JsonUtil.vfilter);
					list.add(JSON.parseObject(obj,type));
				}
			}catch(Exception e){
				list.clear();
			}
		}else{
			list = new ArrayList<>(0);
		}
		return list;
	}

}
