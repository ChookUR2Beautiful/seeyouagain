package com.xmniao.xmn.core.util.holder;
/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：DynamicDataSourceHolder
 * 
 * 类描述： 根据设置key的值指定spring使用读库还是写库
 * 
 * 创建人： chen'heng
 * 
 * 创建时间：2015-02-10 10:00:17
 * 
 */
public class DynamicDataSourceHolder {
	
	private static final ThreadLocal<String> dataSourceHolder = new ThreadLocal<String>();
	
	public static void remove(){
		dataSourceHolder.remove();
	}
	public static void putDataSource(String key){
		dataSourceHolder.set(key);
	}
	
	public static String getDataSource(){
		return dataSourceHolder.get();
	}

}
