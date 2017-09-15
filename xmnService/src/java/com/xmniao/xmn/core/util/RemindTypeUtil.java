package com.xmniao.xmn.core.util;

/**
 * 
* 项目名称：xmnService   
* 类名称：RemindTypeUtil   
* 类描述：计时状态工具类   
* 创建人：liuzhihao   
* 创建时间：2016年6月3日 下午7:47:53   
* @version    
*
 */
public class RemindTypeUtil {
	
	public static final String remindType(String date){
		if(DateUtil.year(DateUtil.parse(date, DateUtil.defaultSimpleFormater)) != DateUtil.year()){
			return DateUtil.year()-DateUtil.year(DateUtil.parse(date))+"年前";
		}
		if(DateUtil.month(DateUtil.parse(date, DateUtil.defaultSimpleFormater)) != DateUtil.month()){
			return DateUtil.month()-DateUtil.month(DateUtil.parse(date, DateUtil.defaultSimpleFormater))+"月前";//result月之前
		}
		if(DateUtil.day(DateUtil.parse(date, DateUtil.defaultSimpleFormater)) != DateUtil.day() 
				&& (DateUtil.day()-DateUtil.day(DateUtil.parse(date, DateUtil.defaultSimpleFormater))) >7){
			return (DateUtil.day()-DateUtil.day(DateUtil.parse(date, DateUtil.defaultSimpleFormater)))/7+"周前";
		}
		if(DateUtil.day(DateUtil.parse(date, DateUtil.defaultSimpleFormater)) != DateUtil.day() 
				&& (DateUtil.day()-DateUtil.day(DateUtil.parse(date, DateUtil.defaultSimpleFormater))) > 1 
				&& (DateUtil.day()-DateUtil.day(DateUtil.parse(date, DateUtil.defaultSimpleFormater))) < 7){
			return (DateUtil.day()-DateUtil.day(DateUtil.parse(date, DateUtil.defaultSimpleFormater)))+"天前";//result天前
		}
		if(DateUtil.day(DateUtil.parse(date, DateUtil.defaultSimpleFormater)) != DateUtil.day() 
				&& (DateUtil.day()-DateUtil.day(DateUtil.parse(date, DateUtil.defaultSimpleFormater))) ==1){
			return "昨天";//昨天
		}
		if(DateUtil.hour(DateUtil.parse(date, DateUtil.defaultSimpleFormater)) != DateUtil.hour()){
			return (DateUtil.hour()-DateUtil.hour(DateUtil.parse(date, DateUtil.defaultSimpleFormater)))+"小时前";//result周之前
		}
			return "刚刚";
	}
	
	public static void main(String[] args) {
		String type = remindType("2016-06-03 10:30:20");
		System.err.println(type);
	}
}