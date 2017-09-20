package com.xmniao.xmn.core.userData_statistics.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.alibaba.fastjson.JSON;
import com.xmniao.xmn.core.userData_statistics.entity.PUserDataContainer;
import com.xmniao.xmn.core.userData_statistics.entity.PUserDataResponse;
import com.xmniao.xmn.core.util.HttpUtil;
import com.xmniao.xmn.core.util.JsonUtil;
/**
 * 项目名称：XmnWeb
 * 
 * 类名称：AbstractServiceHandel
 * 
 * 类描述：用户统计接口公用处理类
 * 
 * 创建人： chen'heng
 * 
 * 创建时间：2014-12-23 10:33:43
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
public abstract class AbstractServiceHandel<T extends PUserDataResponse> {
	
	
	/**
	 * 存储容器
	 */
	private PUserDataContainer<T> container;
	
	/**
	 * 接口返回数据存储类
	 */
	private  PUserDataContainer<T>.ResponseData<T> responseData;
	
	/**
	 * 列表
	 */
	private List<T> datas;
	
	/**
	 * 转换目标class
	 */
	private Class<T> clzz;



	/**
	 * 请求地址
	 */
	private String url;
	/**
	 * 请求参数
	 */
	private String param;
	
	public List<T> getDatas() {
		return datas;
	}

	public void setDatas(List<T> datas) {
		this.datas = datas;
	}

	public PUserDataContainer<T> getContainer() {
		return container;
	}

	public void setContainer(PUserDataContainer<T> container) {
		this.container = container;
	}
	
	
	
	public PUserDataContainer<T>.ResponseData<T> getResponseData() {
		return responseData;
	}

	public void setResponseData(PUserDataContainer<T>.ResponseData<T> responseData) {
		this.responseData = responseData;
	}

	public  Class<T> getClzz() {
		return clzz;
	}

	public void setClzz(Class<T> clzz) {
		this.clzz = clzz;
	}
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getParam() {
		return param;
	}

	public void setParam(String param) {
		this.param = param;
	}


	
	/**
	 * 根据子类url与param 请求接口 并返回数据
	 * @throws Exception 
	 */
	protected void Reuqest() throws Exception{
		this.container =HttpUtil.getInstance().httpGetReuqst(getUrl(),getParam());
		this.responseData = this.container.getData();
		this.datas = this.responseData.getData();
		castJsonArrayToT();
	}
	
	/**
	 * 转换jsonarray成指定目标
	 */
	private void castJsonArrayToT(){
		for(int i=0;i<this.datas.size();i++){
			datas.set(i,(JSON.parseObject(JSON.toJSONString(datas.get(i),JsonUtil.vfilter), getClzz())));	
		}	
	}
	
	/**
	 * 合并list
	 * @param main 主list
	 * @param slave 从list
	 */
	protected void mergeList(List<T> main,List<T> slave){
		if(main==null || main.isEmpty()){
			if(slave!=null && !slave.isEmpty()){
				fill(slave);
				return;
			}
		}
		if(slave!=null && !slave.isEmpty()){
			Map<String, T> slaveMap= listToMap(slave);
			Map<String, T> map =new TreeMap<String, T>();
			for(T m : main){
				if(m!=null){
					String date = m.getDate();
					if(slaveMap.containsKey(date)){
						setValue(m,slaveMap.get(date));
					}
					map.put(date, m);
						
				}
				if(map.size()==10)break;
				
			}
	        List<T> listValue = new ArrayList<T>(map.size());  
	        List<String> listKey = new ArrayList<String>(map.keySet()); 
	        int len = listKey.size()-1;
	        for(int i=len;i>-1;i--){
	        	listValue.add(map.get(listKey.get(i))); 
	        }
			setDatas(listValue);
		}
		
	}
	
	
	/**
	 * 填充空数据
	 * @param fillData
	 */
	private void fill(List<T> fillData){
		List<T> datas = new ArrayList<T>(10);
		T data=null;
		int len  = fillData.size();
		for(int i=0;i<len;i++){
			T t = fillData.get(i);
			data = createFillData();
			data.setDate(t.getDate());
			setValue(data,t);
			datas.add(data);
			if(datas.size()==10)break;
		}
		setDatas(datas);
	}
	
	private Map<String, T> listToMap(List<T> list){
		Map<String, T> map =new HashMap<String, T>(list.size());
		for(T t: list){
			map.put(t.getDate(), t);
		}
		return map;
	}
	

	/**
	 * 设置属性 子类实现
	 * @param main
	 * @param slave
	 */
	protected abstract void setValue(T main,T slave);
	
	protected abstract T createFillData();

}
