package com.xmniao.xmn.core.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
/**
 * 
* 项目名称：saasService   
* 类名称：TokenUtils   
* 类描述：判断Token有效时间类
* 创建人：liuzhihao   
* 创建时间：2016年3月29日 下午5:38:41   
* @version    
*
 */
public class TokenUtils {
	/***token默认有效期时间为一个月***/
	public static final int EXPIRY_DATE = 30*24*60;
	
	/***IOS系统***/
	public static final String IOS = "ios";
	
	/***ANDROID系统***/
	public static final String ANDROID = "android";
	
	/***WINDOW系统***/
	public static final String WP = "wp";
	
	/***
	 * 判断是否过期
	 * @param createDate  传入token创建时间
	 * @param expiredTime	传入有效时间 单位为分钟
	 * @return
	 * @throws ParseException 
	 */
	public static Boolean isExpired(String createDate,long expiredTime) throws ParseException{
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar cal = Calendar.getInstance();
		long currentTime = cal.getTimeInMillis();//当前系统的毫秒数
		cal.setTime(sf.parse(createDate));
		long createTime = cal.getTimeInMillis();//createDate的毫秒数
		long between = (createTime - currentTime)/1000; //计算多少秒
		long min = between/60;//计算多少分
		return min < expiredTime;
	}
	/***
	 * 根据系统名称及版本 返回对应的数值
	 * @param SystemVersion
	 * @return
	 */
	public static Integer getVtypeBySystemVersion(String SystemVersion){
		if(SystemVersion.toLowerCase().indexOf(IOS) != -1){
			return 1;
		}else if(SystemVersion.toLowerCase().indexOf(ANDROID) != -1){
			return 2;
		}else if(SystemVersion.toLowerCase().indexOf(WP) != -1){
			return 3;
		}
		return 4;
	}

}
